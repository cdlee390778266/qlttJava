package com.qianlong.qltt.zbas.domain;

public class Ttadefprotacprm extends TtadefprotacprmKey {
    private Integer fiMaxdelay;

    private String fsDetail;

    private String fsTacprmname;

    public Integer getFiMaxdelay() {
        return fiMaxdelay;
    }

    public void setFiMaxdelay(Integer fiMaxdelay) {
        this.fiMaxdelay = fiMaxdelay;
    }

    public String getFsDetail() {
        return fsDetail;
    }

    public void setFsDetail(String fsDetail) {
        this.fsDetail = fsDetail;
    }

    public String getFsTacprmname() {
        return fsTacprmname;
    }

    public void setFsTacprmname(String fsTacprmname) {
        this.fsTacprmname = fsTacprmname;
    }

	@Override
	public String toString() {
		return "Ttadefprotacprm [fiMaxdelay=" + fiMaxdelay + ", fsDetail=" + fsDetail + ", fsTacprmname=" + fsTacprmname
				+ "]";
	}
}