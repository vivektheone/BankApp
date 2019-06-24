package query.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static query.AccountView.accounts;

public class AccountQuery {

    private final Logger LOG = LoggerFactory.getLogger(AccountQuery.class);

    public void printAccountsDetail() {
        accounts.forEach((k,v) -> LOG.info("Account {}, customer {}, balance {}", v.getName(), v.getNumber(), Math.floor(v.getBalance()*100)/100));
    }
}
