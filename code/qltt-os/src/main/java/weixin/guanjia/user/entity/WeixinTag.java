package weixin.guanjia.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jeewx.api.wxuser.tag.model.WxTag;

@Entity
@Table(name="weixin_tag")
@SuppressWarnings("serial")
public class WeixinTag extends WxTag implements java.io.Serializable{
	
	/**微信账号*/
	private String accountid;
	
	/**标签编号*/
	private Integer id;
	
	/**标签名称*/
	private String name;
	
	/**标签状态*/
	private Integer status;
	
	/**创建人名称*/
	private String create_name;
	
	/**创建人编号*/
	private String create_by;
	
	/**创建时间*/
	private Date create_date;
	
	/**更新人名称*/
	private String update_name;
	
	/**更新人编号*/
	private String update_by;
	
	/**更新时间*/
	private Date update_date;
	
	@Id
	@Column(name ="ACCOUNTID",nullable=false,length=100)
	public String getAccountid() {
		return accountid;
	}
	
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
	@Id
	@Column(name ="ID",nullable=false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name ="NAME",nullable=false,length=20)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name ="STATUS",nullable=false)
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name ="CREATE_NAME",nullable=false,length=50)
	public String getCreate_name() {
		return create_name;
	}
	
	public void setCreate_name(String create_name) {
		this.create_name = create_name;
	}
	
	@Column(name ="CREATE_BY",nullable=false,length=50)
	public String getCreate_by() {
		return create_by;
	}
	
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	
	@Column(name ="CREATE_DATE",nullable=false)
	public Date getCreate_date() {
		return create_date;
	}
	
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	@Column(name ="UPDATE_NAME",nullable=false,length=50)
	public String getUpdate_name() {
		return update_name;
	}
	
	public void setUpdate_name(String update_name) {
		this.update_name = update_name;
	}
	
	@Column(name ="UPDATE_BY",nullable=false,length=50)
	public String getUpdate_by() {
		return update_by;
	}
	
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	
	@Column(name ="UPDATE_DATE",nullable=false)
	public Date getUpdate_date() {
		return update_date;
	}
	
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	@Override
	public String toString() {
		return "WeixinTag [accountid=" + accountid + ", id=" + id + ", name=" + name + ", status=" + status
				+ ", create_name=" + create_name + ", create_by=" + create_by + ", create_date=" + create_date
				+ ", update_name=" + update_name + ", update_by=" + update_by + ", update_date=" + update_date + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountid == null) ? 0 : accountid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeixinTag other = (WeixinTag) obj;
		if (accountid == null) {
			if (other.accountid != null)
				return false;
		} else if (!accountid.equals(other.accountid))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
