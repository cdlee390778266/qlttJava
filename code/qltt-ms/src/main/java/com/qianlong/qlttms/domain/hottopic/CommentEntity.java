package com.qianlong.qlttms.domain.hottopic;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.qianlong.qlttms.utils.DateUtils;


/**
 * 主题关联公众表
 */
@Entity
@Table(name="hottopic_comment")
public class CommentEntity extends IdEntity {
	@Column(name="articleid")
	private String articleid;//热门主题ID
	
	@Column(name="accountid")
	private String accountid;//微信公众号ID
	
	@Transient
	private String accountname;
	
	@Column(name="openid")
	private String openid;//微信粉丝OPENID
	
	private String nickname;
	
	@Transient
	private byte[] nicknamebytes;
	
	@Column(name="portrait")
	private String portrait;
	
	@Column(name="commenttime")
	private Timestamp commenttime;//评论时间
	
	@Transient
	private String commenttimeStr;
	
	@Column(name="content")
	private String content;//评论内容
	
	@Column(name="likenum")
	private Integer likenum;//点赞数

	public String getArticleid() {
		return articleid;
	}

	public void setArticleid(String articleid) {
		this.articleid = articleid;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Timestamp getCommenttime() {
		return commenttime;
	}

	public void setCommenttime(Timestamp commenttime) {
		this.commenttime = commenttime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getLikenum() {
		return likenum;
	}

	public void setLikenum(Integer likenum) {
		this.likenum = likenum;
	}

	@Transient
	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	@Transient
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
		this.nicknamebytes = (nickname == null?null:nickname.getBytes());
	}

	@Column(name ="NICKNAME",nullable=false)
	public byte[] getNicknamebytes() {
		return nicknamebytes;
	}

	public void setNicknamebytes(byte[] nicknamebytes) {
		this.nicknamebytes = nicknamebytes;
		this.nickname = (nicknamebytes == null?null:new String(nicknamebytes));
	}

	@Transient
	public String getCommenttimeStr() {
		return commenttime == null? "":DateUtils.formatDate(commenttime, "yyyy-MM-dd HH:mm:ss");
	}

	public void setCommenttimeStr(String commenttimeStr) {
		this.commenttimeStr = commenttimeStr;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	
	
}
