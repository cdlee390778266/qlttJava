package weixin.guanjia.user.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.jeewx.api.wxuser.tag.model.WxTag;

@Entity
@Table(name="weixin_tag")
public class WeixinTag extends WxTag{
	private String accountid;
	private Integer id;
	private String name;
	private Integer status;
	private String create_name;
	private String create_by;
	private Date create_date;
	private String update_name;
	private String update_by;
	private Date update_date;
	
	public String getAccountid() {
		return accountid;
	}
	
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getCreate_name() {
		return create_name;
	}
	
	public void setCreate_name(String create_name) {
		this.create_name = create_name;
	}
	
	public String getCreate_by() {
		return create_by;
	}
	
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	
	public Date getCreate_date() {
		return create_date;
	}
	
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	public String getUpdate_name() {
		return update_name;
	}
	
	public void setUpdate_name(String update_name) {
		this.update_name = update_name;
	}
	
	public String getUpdate_by() {
		return update_by;
	}
	
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	
	public Date getUpdate_date() {
		return update_date;
	}
	
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
}
