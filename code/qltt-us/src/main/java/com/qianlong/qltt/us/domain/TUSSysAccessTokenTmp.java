package com.qianlong.qltt.us.domain;

import java.sql.Timestamp;

public class TUSSysAccessTokenTmp extends TUSSysAccessTokenTmpKey {
    private String fsAccesstoken;

    private Timestamp ftTimestamp;

    public String getFsAccesstoken() {
        return fsAccesstoken;
    }

    public void setFsAccesstoken(String fsAccesstoken) {
        this.fsAccesstoken = fsAccesstoken;
    }

    public Timestamp getFtTimestamp() {
        return ftTimestamp;
    }

    public void setFtTimestamp(Timestamp ftTimestamp) {
        this.ftTimestamp = ftTimestamp;
    }
}