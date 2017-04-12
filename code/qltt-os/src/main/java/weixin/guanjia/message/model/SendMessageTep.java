package weixin.guanjia.message.model;

import java.sql.Clob;
import java.sql.Timestamp;

public class SendMessageTep implements Comparable<SendMessageTep>{

	private String id;

	private String templateId;

	private String openId;

	private String ttacct;

	private Integer svcchnl;

	private Integer devtype;

	private Clob content;

	private Timestamp createtime;
	
	private Integer weight;

	private Integer sendStatus;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public Integer getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(Integer sendStatus) {
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
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	@Override
	public int compareTo(SendMessageTep o) {
		return this.weight.intValue() > o.weight.intValue() ? 1 : this.weight.intValue() < o.weight.intValue() ? -1 : 0;
	}
}
