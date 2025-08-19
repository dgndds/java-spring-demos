package com.xen0n.aop_demo.dao;

import com.xen0n.aop_demo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private String name;

    @Override
    public void addAccount() {
        System.out.println(getClass() + ": Adding account to DB\n");
    }

    @Override
    public void addAccount(Account account) {
        System.out.println(getClass() + ": Adding account to DB\n");
    }

    @Override
    public String addCredit() {
        System.out.println(getClass() + ": Adding account to DB 2\n");
        return "";
    }

    public String getName() {
        System.out.println(getClass() + ": Getting account name from DB\n");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": Setting account name to DB\n");
        this.name = name;
    }

    @Override
    public List<Account> getAccounts() {
        try {
            return getAccounts(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> getAccounts(boolean tripwire) throws Exception {

        if (tripwire) {
            throw new Exception("Error: Failed to retrieve accounts");
        }

        List<Account> accounts = new ArrayList<Account>();

        accounts.add(new Account(1, "John Doe"));
        accounts.add(new Account(2, "Jane Doe"));
        accounts.add(new Account(3, "Jack Doe"));

        return accounts;
    }
}
