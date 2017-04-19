package weixin.guanjia.message.model;

import java.sql.Clob;
import java.sql.Timestamp;

public class SendMessageTep implements Comparable<SendMessageTep>{

	private String id;

	
	private String templateId;//模板id

	private String openId;//设备id

	private String ttacct;//推推账号

	private Integer svcchnl;//渠道标识

	private Integer devtype;//设备类型
	
	private String title1;//标题
	
	private String keyword1;//
	
	private String keyword2;
	
	private Clob content;//内容

	private Timestamp createtime;//创建时间
	
	private Integer weight;//权重

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
	
	public String getTitle1() {
		return title1;
	}
	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}
	public String getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	@Override
	public int compareTo(SendMessageTep o) {
		return this.weight.intValue() > o.weight.intValue() ? 1 : this.weight.intValue() < o.weight.intValue() ? -1 : 0;
	}
}
