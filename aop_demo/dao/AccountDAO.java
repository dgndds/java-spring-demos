package com.xen0n.aop_demo.dao;

import com.xen0n.aop_demo.Account;

import java.util.List;

public interface AccountDAO {

    void addAccount();

    void addAccount(Account account);

    String addCredit();

    String getName() ;

    void setName(String name);

    List<Account> getAccounts();

    List<Account> getAccounts(boolean tripwire) throws Exception;
}
