package com.qianlong.qltt.us.domain.acct;

import java.util.Date;

public class TUSAcctCNReg extends TUSAcctCNRegKey {
    private String fsCn;

    private Date ftRegtime;

    private Date ftUpdtime;

    private Integer fiStatus;

    public String getFsCn() {
        return fsCn;
    }

    public void setFsCn(String fsCn) {
        this.fsCn = fsCn;
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