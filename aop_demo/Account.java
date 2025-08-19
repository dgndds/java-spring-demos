package com.xen0n.aop_demo;

public class Account {
    private String name;
    private int level;

    public Account() {
    }

    public Account(int level, String name) {
        this.level = level;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
