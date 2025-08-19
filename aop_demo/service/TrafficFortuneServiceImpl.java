package com.xen0n.aop_demo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService {
    @Override
    public String getTrafficFortune() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "Traffic is stuck for hours now.";
    }

    @Override
    public String getTrafficFortune(boolean tripwire) throws Exception {
        if(tripwire) {
            throw new Exception("Failed to retrieve traffic fortune");
        }

        return getTrafficFortune();
    }
}
