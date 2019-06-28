package query.routes;

import com.google.gson.Gson;
import command.CreateAccountCommand;
import command.DepositAmountCommand;
import command.TransferCommand;
import command.WithdrawAmountCommand;
import org.axonframework.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.controller.AccountQuery;
import spark.Request;
import spark.Response;
import spark.Route;

public class BankRoutes {

    private Configuration configuration;

    private static Logger LOGGER = LoggerFactory.getLogger("Bank Router");

    private static Gson gson = new Gson();

    public BankRoutes(Configuration configuration) {
        this.configuration = configuration;
    }



    public Route createAccount() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                LOGGER.info("Account create event --> " + request.body());
                CreateAccountCommand createCommand =
                        gson.fromJson(request.body(), CreateAccountCommand.class);
                configuration.commandGateway().send(createCommand);
                return response;
            }
        };
    }

    public Route depositInAccount() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                LOGGER.info("Account update event --> " + request.body());
                DepositAmountCommand depositCommand = gson.fromJson(request.body(), DepositAmountCommand.class);
                if (depositCommand.getAmount() > 0)
                    configuration.commandGateway().send(depositCommand);
                else {
                    WithdrawAmountCommand withDrawCommand = gson.fromJson(request.body(), WithdrawAmountCommand.class);
                    configuration.commandGateway().send(withDrawCommand);
                }

                return response;
            }
        };
    }

    public Route transfer() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                LOGGER.info("Account Transfer event --> " + request.body());
                TransferCommand transferCommand =
                        gson.fromJson(request.body(), TransferCommand.class);
                configuration.commandGateway().send(transferCommand);
                return response;
            }
        };
    }


    public Route fetch() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                AccountQuery query = new AccountQuery();
                query.printAccountsDetail();
                return "Watch console...";
            }
        } ;
    }
}