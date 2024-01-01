package com.example.monitor;

import com.common.observer.ConcreteSubject;

public class MemObserver extends ConcreteSubject<MemInfo> {

    private static class SingletonHolder {
        private static final MemObserver INSTANCE = new MemObserver();
    }

    public static MemObserver getInstance() {
        return MemObserver.SingletonHolder.INSTANCE;
    }
}
