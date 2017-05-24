package weixin.guanjia.message.entity;

import java.sql.Clob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;

@Entity
@Table(name="WEIXIN_MESSAGE_TEMPLATE")
public class MessageTemplate {
	@Id
	@Column(name="template_id")
	private String templateId;
	@Column(name="title")
	private String title;
	@Column(name="primary_industry_id")
	private Integer primaryIndustryId;
	@Column(name="primary_industry_name")
	private String primaryIndustryName;
	@Column(name="secondary_industry_id")
	private Integer secondaryIndustryId;
	@Column(name="secondary_industry_name")
	private String secondaryIndustryName;
	@Column(name="createtime")
	private Timestamp createtime;
	@Column(name="updatetime")
	private Timestamp updatetime;
	@Column(name="detail_content")
	private Clob detailContent;
	@Column(name="ex_content")
	private Clob exContent;
	@Column(name="status")
	private Integer status;
	@Column(name="accountid")
	private String accountid;
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPrimaryIndustryId() {
		return primaryIndustryId;
	}
	public void setPrimaryIndustryId(Integer primaryIndustryId) {
		this.primaryIndustryId = primaryIndustryId;
	}
	public String getPrimaryIndustryName() {
		return primaryIndustryName;
	}
	public void setPrimaryIndustryName(String primaryIndustryName) {
		this.primaryIndustryName = primaryIndustryName;
	}
	public Integer getSecondaryIndustryId() {
		return secondaryIndustryId;
	}
	public void setSecondaryIndustryId(Integer secondaryIndustryId) {
		this.secondaryIndustryId = secondaryIndustryId;
	}
	public String getSecondaryIndustryName() {
		return secondaryIndustryName;
	}
	public void setSecondaryIndustryName(String secondaryIndustryName) {
		this.secondaryIndustryName = secondaryIndustryName;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	public Clob getDetailContent() {
		return detailContent;
	}
	public void setDetailContent(Clob detailContent) {
		this.detailContent = detailContent;
	}
	public Clob getExContent() {
		return exContent;
	}
	public void setExContent(Clob exContent) {
		this.exContent = exContent;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
}
