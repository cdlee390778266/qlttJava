package com.qianlong.qltt.us.domain.app;

import java.util.Date;

public class TUSSysPltDailyCallKey {
    private String fsAppid;

    private String fsPtlno;

    private Date ftPtllastcalltime;

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

    public Date getFtPtllastcalltime() {
        return ftPtllastcalltime;
    }

    public void setFtPtllastcalltime(Date ftPtllastcalltime) {
        this.ftPtllastcalltime = ftPtllastcalltime;
    }
}