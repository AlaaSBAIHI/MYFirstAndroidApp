package com.example.redi.MyFirstAndroidApp.models.functional;

/**
 * Created by ReDI on 1/12/2017.
 */


public interface Consumer<T> {
    void apply(T t);
}

