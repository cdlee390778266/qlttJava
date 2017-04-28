package com.qianlong.qltt.us.domain;

import java.sql.Timestamp;

public class TUSAcctCNReg extends TUSAcctCNRegKey {
    private String fsCn;

    private Timestamp ftRegtime;

    private Timestamp ftUpdtime;

    private Integer fiStatus;

    public String getFsCn() {
        return fsCn;
    }

    public void setFsCn(String fsCn) {
        this.fsCn = fsCn;
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