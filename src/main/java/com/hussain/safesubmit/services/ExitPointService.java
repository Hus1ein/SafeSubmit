package com.hussain.safesubmit.services;

public interface ExitPointService<T> {

    void sendDataToTarget(String id, T data);

}
