package com.qianlong.qlttms.domain.hottopic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 主题关联公众表
 */
@Entity
@Table(name="hottopic_publishtarget")
public class PublishTargetEntity extends IdEntity{
	
	private String articleid;//热门主题ID
	
	private String accountid;//发布公众号ID
	
	private String accountname;//公众号名称
	
	@Column(name="articleid")
	public String getArticleid() {
		return articleid;
	}

	public void setArticleid(String articleid) {
		this.articleid = articleid;
	}
	
	@Column(name="accountid")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	@Transient
	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	
}
