package com.common.observer;

// 观察者
public interface Observer<T> {
    void update(T state);
}
