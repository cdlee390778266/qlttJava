package com.qianlong.qltt.us.domain;

public class TUsAttnStock extends TUsAttnStockKey {
    private String fsStockcode;

    private String fsStockname;

    public String getFsStockcode() {
        return fsStockcode;
    }

    public void setFsStockcode(String fsStockcode) {
        this.fsStockcode = fsStockcode;
    }

    public String getFsStockname() {
        return fsStockname;
    }

    public void setFsStockname(String fsStockname) {
        this.fsStockname = fsStockname;
    }
}