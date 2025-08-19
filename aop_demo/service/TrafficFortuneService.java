package com.xen0n.aop_demo.service;

public interface TrafficFortuneService {
    String getTrafficFortune();

    String getTrafficFortune(boolean tripwire) throws Exception;
}
