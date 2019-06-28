package aggregate;

import command.CreateAccountCommand;
import command.DepositAmountCommand;
import command.TransferCommand;
import command.WithdrawAmountCommand;
import event.AccountCreatedEvent;
import event.AmountDepositEvent;
import event.AmountTransferEvent;
import event.AmountWithdrawEvent;
import handler.AccountEventHandler;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.AccountView;

import java.util.ArrayList;
import java.util.List;

public class AccountAggregateTest {

    private final Logger LOG = LoggerFactory.getLogger(AccountAggregateTest.class);

    private static FixtureConfiguration<AccountAggregate> fixture;

    @BeforeAll
    public static void setUp() throws Exception {
        fixture = new AggregateTestFixture<AccountAggregate>(AccountAggregate.class);
    }

    @Test
    public void verifyAccountCreatedEventTriggered() {
        String id = "123";
        String customerName = "testCustomer";
        fixture.given()
                .when(new CreateAccountCommand(id, customerName))
                .expectEvents(new AccountCreatedEvent(id, customerName));
    }

    @Test
    public void verifyDepositEventTriggered() {
        String id = "123";
        fixture.given(new AccountCreatedEvent(id, "testCustomer"))
                .when(new DepositAmountCommand(id, 1000.0))
                .expectEvents(new AmountDepositEvent(id, 1000.0));
    }

    @Test
    public void verifyAmountWithdrawEventTriggered() {
        String id = "123";
        fixture.given(new AccountCreatedEvent(id, "testCustomer"))
                .when(new WithdrawAmountCommand(id, 1000.0))
                .expectEvents(new AmountWithdrawEvent(id, 1000.0));
    }


    @Test
    public void verifyAmountTransferEventTriggered() {
        String from = "123";
        String to = "124";

        List events = new ArrayList<>();
        events.add(new AccountCreatedEvent(from, "foo"));
        events.add(new AccountCreatedEvent(to, "bar"));

        fixture.given(events)
                .when(new TransferCommand(to, from, 100.0))
                .expectEvents(new AmountTransferEvent(to, from,100.0));
    }

}