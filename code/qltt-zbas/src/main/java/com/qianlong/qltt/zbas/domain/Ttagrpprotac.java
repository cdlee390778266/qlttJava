package com.qianlong.qltt.zbas.domain;

import java.util.List;

public class Ttagrpprotac extends TtagrpprotacKey {
    private String fsName;

    private String fsDetail;

    private Integer fiLevel;

    private String fsPtacgroup;
    
    private String fsPtacgroupName;
    
    private List<Ttagrpprotac> children;
    
    private List<Ttagrpprotacmem> member;

	public String getFsName() {
		return fsName;
	}

	public void setFsName(String fsName) {
		this.fsName = fsName;
	}

	public String getFsDetail() {
		return fsDetail;
	}

	public void setFsDetail(String fsDetail) {
		this.fsDetail = fsDetail;
	}

	public Integer getFiLevel() {
		return fiLevel;
	}

	public void setFiLevel(Integer fiLevel) {
		this.fiLevel = fiLevel;
	}

	public String getFsPtacgroup() {
		return fsPtacgroup;
	}

	public void setFsPtacgroup(String fsPtacgroup) {
		this.fsPtacgroup = fsPtacgroup;
	}

	public String getFsPtacgroupName() {
		return fsPtacgroupName;
	}

	public void setFsPtacgroupName(String fsPtacgroupName) {
		this.fsPtacgroupName = fsPtacgroupName;
	}

	public List<Ttagrpprotac> getChildren() {
		return children;
	}

	public void setChildren(List<Ttagrpprotac> children) {
		this.children = children;
	}

	public List<Ttagrpprotacmem> getMember() {
		return member;
	}

	public void setMember(List<Ttagrpprotacmem> member) {
		this.member = member;
	}
}