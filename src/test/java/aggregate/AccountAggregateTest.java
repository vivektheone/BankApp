package aggregate;

import command.CreateAccountCommand;
import command.DepositAmountCommand;
import command.WithdrawAmountCommand;
import event.AccountCreatedEvent;
import event.AmountDepositEvent;
import event.AmountWithdrawEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AccountAggregateTest {

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

}