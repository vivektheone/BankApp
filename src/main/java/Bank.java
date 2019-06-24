import aggregate.AccountAggregate;
import com.google.gson.Gson;
import command.CreateAccountCommand;
import command.DepositAmountCommand;
import command.WithdrawAmountCommand;
import handler.AccountEventHandler;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.controller.AccountQuery;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

public class Bank {

    private static Logger LOGGER = LoggerFactory.getLogger("Bank Logger");

    private static Gson gson = new Gson();

    public static void main(String[] args) {

        get("/health", (req, res) -> "Bank is up!");

        EventStore eventStore = EmbeddedEventStore.builder().storageEngine(new InMemoryEventStorageEngine()).build();
        Configuration configuration = getConfiguration(eventStore);
        configuration.start();

        Route createAccount = new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                LOGGER.info("Account create event --> " + request.body());
                CreateAccountCommand createCommand =
                        gson.fromJson(request.body(), CreateAccountCommand.class);
                configuration.commandGateway().send(createCommand);
                return response;
            }
        };
        post("/accounts", createAccount);


        Route updateAccount = new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                LOGGER.info("Account update event --> " + request.body());
                DepositAmountCommand depositCommand = gson.fromJson(request.body(), DepositAmountCommand.class);
                if(depositCommand.getAmount() > 0)
                    configuration.commandGateway().send(depositCommand);
                else {
                    WithdrawAmountCommand withDrawCommand = gson.fromJson(request.body(), WithdrawAmountCommand.class);
                    configuration.commandGateway().send(withDrawCommand);
                }

                return response;
            }
        };
        put("/accounts", updateAccount);

        Route getAccounts = new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                AccountQuery query = new AccountQuery();
                query.printAccountsDetail();
                return "Watch console...";
            }
        } ;
        get("/accounts", getAccounts);
    }

    private static Configuration getConfiguration(EventStore eventStore) {
        return DefaultConfigurer.defaultConfiguration()
                .configureEventStore(configuration1 -> eventStore)
                .configureAggregate(AccountAggregate.class)
                .eventProcessing(ep -> ep
                        .registerEventHandler(cf -> new AccountEventHandler())
                )
                .buildConfiguration();
    }
}

