package com.app.moneyapi.dto.error;

public class Erros {

    private String msgUsuario;
    private String msgErro;

    public Erros(String msgUsuario, String msgErro) {
        this.msgUsuario = msgUsuario;
        this.msgErro = msgErro;
    }

    public String getMsgUsuario() {
        return msgUsuario;
    }

    public String getMsgErro() {
        return msgErro;
    }
}
