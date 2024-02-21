package com.example.myapplication.base.favorite;

public class CollectChangeEvent {

    public final int id;
    public final boolean status;

    public CollectChangeEvent(int id, boolean status) {
        this.id = id;
        this.status = status;
    }
}
