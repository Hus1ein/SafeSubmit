package com.hussain.safesubmit.services;

public interface OnCompleteListener {

    void onSuccessListener(String id);

    void onFailedListener(String id, String errorMessage);

}
