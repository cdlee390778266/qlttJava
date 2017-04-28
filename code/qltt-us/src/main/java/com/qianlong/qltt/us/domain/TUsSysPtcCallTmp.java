package com.qianlong.qltt.us.domain;

import java.sql.Timestamp;

public class TUsSysPtcCallTmp extends TUsSysPtcCallTmpKey {
    private String fsRequrl;

    private Timestamp ftPtllastcalltime;

    private Integer fiPtlcallnum;

    private Integer fiPtlmaxcallnum;

    public String getFsRequrl() {
        return fsRequrl;
    }

    public void setFsRequrl(String fsRequrl) {
        this.fsRequrl = fsRequrl;
    }

    public Timestamp getFtPtllastcalltime() {
        return ftPtllastcalltime;
    }

    public void setFtPtllastcalltime(Timestamp ftPtllastcalltime) {
        this.ftPtllastcalltime = ftPtllastcalltime;
    }

    public Integer getFiPtlcallnum() {
        return fiPtlcallnum;
    }

    public void setFiPtlcallnum(Integer fiPtlcallnum) {
        this.fiPtlcallnum = fiPtlcallnum;
    }

    public Integer getFiPtlmaxcallnum() {
        return fiPtlmaxcallnum;
    }

    public void setFiPtlmaxcallnum(Integer fiPtlmaxcallnum) {
        this.fiPtlmaxcallnum = fiPtlmaxcallnum;
    }
}