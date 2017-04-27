package com.qianlong.qltt.us.domain;

import java.sql.Timestamp;

public class TUSSysApp extends TUSSysAppKey {
    private String fsApptoken;

    private String fsAppsecret;

    private Timestamp ftRegtime;

    private Timestamp ftUpdtime;

    private Integer fiStatus;

    public String getFsApptoken() {
        return fsApptoken;
    }

    public void setFsApptoken(String fsApptoken) {
        this.fsApptoken = fsApptoken;
    }

    public String getFsAppsecret() {
        return fsAppsecret;
    }

    public void setFsAppsecret(String fsAppsecret) {
        this.fsAppsecret = fsAppsecret;
    }

    public Timestamp getFtRegtime() {
        return ftRegtime;
    }

    public void setFtRegtime(Timestamp ftRegtime) {
        this.ftRegtime = ftRegtime;
    }

    public Timestamp getFtUpdtime() {
        return ftUpdtime;
    }

    public void setFtUpdtime(Timestamp ftUpdtime) {
        this.ftUpdtime = ftUpdtime;
    }

    public Integer getFiStatus() {
        return fiStatus;
    }

    public void setFiStatus(Integer fiStatus) {
        this.fiStatus = fiStatus;
    }
}