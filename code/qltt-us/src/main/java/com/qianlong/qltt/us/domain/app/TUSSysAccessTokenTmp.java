package com.qianlong.qltt.us.domain.app;

import java.util.Date;

public class TUSSysAccessTokenTmp extends TUSSysAccessTokenTmpKey {
    private String fsAccesstoken;

    private Date ftTimestamp;

    public String getFsAccesstoken() {
        return fsAccesstoken;
    }

    public void setFsAccesstoken(String fsAccesstoken) {
        this.fsAccesstoken = fsAccesstoken;
    }

    public Date getFtTimestamp() {
        return ftTimestamp;
    }

    public void setFtTimestamp(Date ftTimestamp) {
        this.ftTimestamp = ftTimestamp;
    }
}