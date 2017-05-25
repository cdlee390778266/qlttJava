package weixin.guanjia.message.entity;

import java.sql.Clob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;

@Entity
@Table(name="WEIXIN_SEND_TEMPLATE_MESSAGE")
public class SendMessage {
	@Id
	@Column(name="msgid")
	private String msgid;
	@Column(name="template_id")
	private String templateId;
	@Column(name="open_id")
	private String openId;
	@Column(name="ttacct")
	private String ttacct;
	@Column(name="svcchnl")
	private Integer svcchnl;
	@Column(name="devtype")
	private Integer devtype;
	@Column(name="content")
	private Clob content;
	@Column(name="createtime")
	private Timestamp createtime;
	@Column(name="send_status")
	private String sendStatus;
	@Column(name="accountid")
	private String accountid;
	
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Clob getContent() {
		return content;
	}
	public void setContent(Clob content) {
		this.content = content;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	public String getTtacct() {
		return ttacct;
	}
	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}
	public Integer getSvcchnl() {
		return svcchnl;
	}
	public void setSvcchnl(Integer svcchnl) {
		this.svcchnl = svcchnl;
	}
	public Integer getDevtype() {
		return devtype;
	}
	public void setDevtype(Integer devtype) {
		this.devtype = devtype;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
}
