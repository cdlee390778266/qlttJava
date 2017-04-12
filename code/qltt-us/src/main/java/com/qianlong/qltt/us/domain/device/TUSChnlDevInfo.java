package com.qianlong.qltt.us.domain.device;

public class TUSChnlDevInfo extends TUSChnlDevInfoKey {
    private String fsDevno;

    private Integer fiDevtype;

    private Integer fiSwitch;

    public String getFsDevno() {
        return fsDevno;
    }

    public void setFsDevno(String fsDevno) {
        this.fsDevno = fsDevno;
    }

    public Integer getFiDevtype() {
        return fiDevtype;
    }

    public void setFiDevtype(Integer fiDevtype) {
        this.fiDevtype = fiDevtype;
    }

    public Integer getFiSwitch() {
        return fiSwitch;
    }

    public void setFiSwitch(Integer fiSwitch) {
        this.fiSwitch = fiSwitch;
    }
}