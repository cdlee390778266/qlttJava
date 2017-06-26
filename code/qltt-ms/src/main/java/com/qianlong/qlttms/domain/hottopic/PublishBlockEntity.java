package com.qianlong.qlttms.domain.hottopic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="hottopic_publishblock")
public class PublishBlockEntity extends IdEntity{
	
	@Column(name="articleid")
	private  String articleid;//热门主题ID
	
	@Column(name="blockname")
	private String blockname;//版块名称（来源于版块管理）

	public String getArticleid() {
		return articleid;
	}

	public void setArticleid(String articleid) {
		this.articleid = articleid;
	}

	public String getBlockname() {
		return blockname;
	}

	public void setBlockname(String blockname) {
		this.blockname = blockname;
	}
}
