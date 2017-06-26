package com.qianlong.qlttms.domain.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tfdrcodetable")
public class NameTable  implements Serializable {
	
	private String fsMarketID;
	
	private String fsKindID;
	
	private String fsPeriodID;
	
	private String fsCode;
	
	private String fsName;
	
	private int fiStop;
	
	private int fiPriceLimit;
	
	private double fdParValue;
	
	private String fsMKind;
	
	private String lastUpdate;
	
	private String fsChkCode;

	@Id
	@Column(name ="fs_MarketID",nullable=false)
	public String getFsMarketID() {
		return fsMarketID;
	}

	public void setFsMarketID(String fsMarketID) {
		this.fsMarketID = fsMarketID;
	}

	@Column(name ="fs_KindID",nullable=false)
	public String getFsKindID() {
		return fsKindID;
	}

	public void setFsKindID(String fsKindID) {
		this.fsKindID = fsKindID;
	}

	@Column(name ="fs_PeriodID",nullable=false)
	public String getFsPeriodID() {
		return fsPeriodID;
	}

	public void setFsPeriodID(String fsPeriodID) {
		this.fsPeriodID = fsPeriodID;
	}

	@Id
	@Column(name ="fs_Code",nullable=false)
	public String getFsCode() {
		return fsCode;
	}

	public void setFsCode(String fsCode) {
		this.fsCode = fsCode;
	}

	@Column(name ="fs_Name",nullable=false)
	public String getFsName() {
		return fsName;
	}

	public void setFsName(String fsName) {
		this.fsName = fsName;
	}

	@Column(name ="fi_Stop",nullable=false)
	public int getFiStop() {
		return fiStop;
	}

	public void setFiStop(int fiStop) {
		this.fiStop = fiStop;
	}

	@Column(name ="fi_PriceLimit",nullable=false)
	public int getFiPriceLimit() {
		return fiPriceLimit;
	}

	public void setFiPriceLimit(int fiPriceLimit) {
		this.fiPriceLimit = fiPriceLimit;
	}

	@Column(name ="fd_ParValue",nullable=false)
	public double getFdParValue() {
		return fdParValue;
	}

	public void setFdParValue(double fdParValue) {
		this.fdParValue = fdParValue;
	}

	@Column(name ="fs_MKind",nullable=false)
	public String getFsMKind() {
		return fsMKind;
	}

	public void setFsMKind(String fsMKind) {
		this.fsMKind = fsMKind;
	}

	@Column(name ="fs_LastUpdate",nullable=false)
	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Column(name ="fs_ChkCode",nullable=false)
	public String getFsChkCode() {
		return fsChkCode;
	}

	public void setFsChkCode(String fsChkCode) {
		this.fsChkCode = fsChkCode;
	}

}
