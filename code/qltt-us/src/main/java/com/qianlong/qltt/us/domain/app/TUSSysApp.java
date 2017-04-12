package com.qianlong.qltt.us.domain.app;

import java.util.Date;

public class TUSSysApp extends TUSSysAppKey {
    private String fsApptoken;

    private String fsAppsecret;

    private Date ftRegtime;

    private Date ftUpdtime;

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

    public Date getFtRegtime() {
        return ftRegtime;
    }

    public void setFtRegtime(Date ftRegtime) {
        this.ftRegtime = ftRegtime;
    }

    public Date getFtUpdtime() {
        return ftUpdtime;
    }

    public void setFtUpdtime(Date ftUpdtime) {
        this.ftUpdtime = ftUpdtime;
    }

    public Integer getFiStatus() {
        return fiStatus;
    }

    public void setFiStatus(Integer fiStatus) {
        this.fiStatus = fiStatus;
    }
}