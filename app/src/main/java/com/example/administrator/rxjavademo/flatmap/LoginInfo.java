package com.example.administrator.rxjavademo.flatmap;

/**
 * Created by Administrator on 2018/5/14.
 */

public class LoginInfo {
    private String userId;
    private String psw;
    private int clientId;
    private int reponseCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getReponseCode() {
        return reponseCode;
    }

    public void setReponseCode(int reponseCode) {
        this.reponseCode = reponseCode;
    }
}
