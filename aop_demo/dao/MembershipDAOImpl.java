package com.xen0n.aop_demo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO {

    @Override
    public void addAccount() {
        System.out.println(getClass() + ": Adding Membership to DB\n");
    }

    @Override
    public void addMember() {
        System.out.println(getClass() + ": Adding Member to DB\n");
    }
}
