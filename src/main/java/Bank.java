import aggregate.AccountAggregate;
import handler.AccountEventHandler;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.routes.BankRoutes;
import spark.Route;

import static spark.Spark.*;

public class Bank {

    private static Logger LOGGER = LoggerFactory.getLogger("Bank Logger");


    public static void main(String[] args) {

        get("/health", (req, res) -> "Bank is up!");

        EventStore eventStore = EmbeddedEventStore.builder().storageEngine(new InMemoryEventStorageEngine()).build();
        Configuration configuration = getConfiguration(eventStore);
        configuration.start();

        BankRoutes routes = new BankRoutes(configuration);
        Route createAccount = routes.createAccount();
        post("/accounts", createAccount);

        Route updateAccount = routes.depositInAccount();
        put("/accounts", updateAccount);

        Route transferFromAccount = routes.transfer();
        put("/transfer", transferFromAccount);

        Route getAccounts = routes.fetch();
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

