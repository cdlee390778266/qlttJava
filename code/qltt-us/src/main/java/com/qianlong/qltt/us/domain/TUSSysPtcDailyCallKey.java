package com.qianlong.qltt.us.domain;

import java.sql.Timestamp;

public class TUSSysPtcDailyCallKey {
    private String fsAppid;

    private String fsPtlno;

    private Timestamp ftPtllastcalltime;

    public String getFsAppid() {
        return fsAppid;
    }

    public void setFsAppid(String fsAppid) {
        this.fsAppid = fsAppid;
    }

    public String getFsPtlno() {
        return fsPtlno;
    }

    public void setFsPtlno(String fsPtlno) {
        this.fsPtlno = fsPtlno;
    }

    public Timestamp getFtPtllastcalltime() {
        return ftPtllastcalltime;
    }

    public void setFtPtllastcalltime(Timestamp ftPtllastcalltime) {
        this.ftPtllastcalltime = ftPtllastcalltime;
    }
}