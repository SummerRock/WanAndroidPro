package com.common.observer;

import java.util.ArrayList;
import java.util.List;

// 主题（被观察者）
interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}

public class ConcreteSubject<T> implements Subject {
    private final List<Observer<T>> observers = new ArrayList<>();
    private T state;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }

    public void setState(T state) {
        this.state = state;
        notifyObservers();
    }
}
