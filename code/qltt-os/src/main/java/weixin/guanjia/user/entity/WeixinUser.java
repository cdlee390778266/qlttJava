package weixin.guanjia.user.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="weixin_user")
@SuppressWarnings("serial")
public class WeixinUser implements java.io.Serializable{
	/**微信号*/
	private String accountid;

	/**用户是否订阅*/
	private Integer subscribe;
	
	/**用户的标识*/
	private String openid;
	
	/**用户的昵称*/
	private String nickname;
	
	private byte[] nicknamebytes;
	
	/**性别*/
	private Integer sex;
	
	/**用户所在城市*/
	private String city;
	
	/**用户所在国家*/
	private String country;
	
	/**用户所在省份*/
	private String province;
	
	/**用户的语言zh_CN*/
	private String language;
	
	/**用户头像*/
	private String headimgurl;
	
	/**用户关注时间*/
	private Integer subscribe_time;
	
	/**公众号*/
	private String unionid;
	
	/**备注*/
	private String remark;
	
	/**分组*/
	private Integer groupid;
	
	/**是否被拉黑*/
	private Integer isblack;
	
	/**标签*/
	private List<Integer> tagid_list = new ArrayList<Integer>();

	@Id
	@Column(name ="ACCOUNTID",nullable=false,length=100)
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	@Column(name ="SUBSCRIBE",nullable=false)
	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}
	
	@Id
	@Column(name ="OPENID",nullable=false,length=30)
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
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

	@Column(name ="SEX",nullable=false)
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	@Column(name ="CITY",nullable=true,length=255)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name ="COUNTRY",nullable=true,length=255)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name ="PROVINCE",nullable=true,length=255)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name ="LANGUAGE",nullable=false,length=16)
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name ="HEADIMGURL",nullable=true,length=255)
	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	@Column(name ="SUBSCRIBE_TIME",nullable=false)
	public Integer getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(Integer subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	@Column(name ="UNIONID",nullable=true,length=30)
	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	@Column(name ="REMARK",nullable=true,length=255)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name ="GROUPID",nullable=false)
	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	@Transient
	public List<Integer> getTagid_list() {
		return tagid_list;
	}

	public void setTagid_list(List<Integer> tagid_list) {
		this.tagid_list = tagid_list;
	}
	
	
	@Column(name ="ISBLACK",nullable=true)
	public Integer getIsblack() {
		return isblack;
	}

	public void setIsblack(Integer isblack) {
		this.isblack = isblack;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountid == null) ? 0 : accountid.hashCode());
		result = prime * result + ((openid == null) ? 0 : openid.hashCode());
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
		WeixinUser other = (WeixinUser) obj;
		if (accountid == null) {
			if (other.accountid != null)
				return false;
		} else if (!accountid.equals(other.accountid))
			return false;
		if (openid == null) {
			if (other.openid != null)
				return false;
		} else if (!openid.equals(other.openid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WeixinUser [accountid=" + accountid + ", subscribe=" + subscribe + ", openid=" + openid + ", nickname="
				+ nickname + ", sex=" + sex + ", city=" + city + ", country=" + country + ", province=" + province
				+ ", language=" + language + ", headimgurl=" + headimgurl + ", subscribe_time=" + subscribe_time
				+ ", unionid=" + unionid + ", remark=" + remark + ", groupid=" + groupid + ", tagid_list=" + tagid_list+"]";
	}

	public static void main(String[] args) {
		/*String json = "{\"subscribe\": 1,\"openid\": \"otvxTs4dckWG7imySrJd6jSi0CWE\",\"nickname\": \"iWithery\", \"sex\": 1, \"language\": \"zh_CN\", \"city\":\"Jieyang\",\"province\": \"Guangdong\",\"country\": \"China\",  \"headimgurl\": \"http://wx.qlogo.cn/mmopen/xbIQx1GRqdvyqkMMhEaGOX802l1CyqMJNgUzKP8MeAeHFicRDSnZH7FY4XB7p8XHXIf6uJA2SCunTPicGKezDC4saKISzRj3nz/0\",\"subscribe_time\": 1434093047, \"unionid\": \"oR5GjjgEhCMJFyzaVZdrxZ2zRRF4\",\"remark\": \"\", \"groupid\": 0, \"tagid_list\":[128,2]}";
		WeixinUser weixinUser = JSONUtil.toBean(json, WeixinUser.class);
		System.out.println(weixinUser);*/
		
		WeixinUser user1 = new WeixinUser();
		user1.setAccountid("Accountid");
		user1.setOpenid("Openid");
		
		WeixinUser user2= new WeixinUser();
		user2.setAccountid("Accountid");
		user2.setOpenid("1111");
		
		List<WeixinUser> list = new ArrayList<WeixinUser>();
		list.add(user1);
		
		int index = list.indexOf(user2);
		
		System.out.println(index);
	}
}
