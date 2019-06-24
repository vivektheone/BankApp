package query;

import model.Account;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountView {

    public static Map<String, Account> accounts = Collections.synchronizedMap(new HashMap<>());
}