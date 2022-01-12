package com.hussain.safesubmit.services;

public interface EntryPointService<T> {

    String passData(T data, String dataType);

}
