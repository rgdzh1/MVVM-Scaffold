package com.student.drop.hefanjiami.utils;

import java.io.Serializable;

public abstract class ResPonse<T> implements Serializable {
    public void doError(T t) {
    }

    public abstract void doSuccess(T t);
}
