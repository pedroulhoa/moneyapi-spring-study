package com.app.moneyapi.dto.error;

public class Erros {

    private String userMsg;
    private String errorMsg;

    public Erros(String userMsg, String errorMsg) {
        this.userMsg = userMsg;
        this.errorMsg = errorMsg;
    }

    public String getUserMsg() {
        return userMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
