package com.qianlong.qlttms.domain.hottopic;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;









import com.qianlong.qlttms.utils.DateUtils;


/**
 * 热名主题发布
 */
@Table(name="hottopic_article")
@Entity
public class ArticleEntity extends IdEntity {
	@Column(name="title")
	private String title;//标题
	@Column(name="author")
	private String author;//作者
	@Column(name="publishtime")
	private Date publishtime;//发布时间
	private String publishtimestr;//发布时间字符串，前端显示
	@Column(name="publishimg")
	private String publishimg;//标题图
	@Column(name="content")
	private String content;//发布内容
	private String contenttext;//发布内容文本，如果超过50个字符就截取前五十个
	@Column(name="exturl")
	private String exturl;//外图链接
	@Column(name="istop")
	private String istop;//	是否置顶
	@Column(name="iseffect")
	private String iseffect;//是否发布
	@Column(name="clicktimes")
	private Integer clicktimes;//查看次数
	@Column(name="publishuserid")
	private String publishuserid;//发布人ID
	@Column(name="publishusername")
	private String publishusername;//发布人名称
	
	private Set<PublishBlockEntity> blockItems = new HashSet<PublishBlockEntity>(); 
	
	@OneToMany(mappedBy="articleid",cascade = CascadeType.ALL, fetch = FetchType.LAZY)  
    @OrderBy(value = "id ASC")/**指明加载OrderItem 时按id 的升序排序*/ 
	public Set<PublishBlockEntity> getBlockItems() {
		return blockItems;
	}
	public void setBlockItems(Set<PublishBlockEntity> blockItems) {
		this.blockItems = blockItems;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
	
	@Transient
	public String getPublishtimestr() {
		return publishtime == null? "":DateUtils.formatDate(publishtime, "yyyy-MM-dd HH:mm:ss");
	}
	
	public void setPublishtimestr(String publishtimestr) {
		this.publishtimestr = publishtimestr;
	}
	public String getPublishimg() {
		return publishimg;
	}
	public void setPublishimg(String publishimg) {
		this.publishimg = publishimg;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Transient
	public String getContenttext() {
		if(StringUtils.isEmpty(content)){
			return "";
		}
		String text = com.qianlong.qlttms.utils.HtmlUtil.html2Text(content);
		if(StringUtils.isEmpty(text)){
			return "";
		}
		//截取五十个字符串
		return text.length() >=52 ? (text.substring(0, 52)+"...") : text;
	}
	public void setContenttext(String contenttext) {
		this.contenttext = contenttext;
	}
	public String getExturl() {
		return exturl;
	}
	public void setExturl(String exturl) {
		this.exturl = exturl;
	}
	public String getIstop() {
		return istop;
	}
	public void setIstop(String istop) {
		this.istop = istop;
	}
	public String getIseffect() {
		return iseffect;
	}
	public void setIseffect(String iseffect) {
		this.iseffect = iseffect;
	}
	public Integer getClicktimes() {
		return clicktimes;
	}
	public void setClicktimes(Integer clicktimes) {
		this.clicktimes = clicktimes;
	}
	public String getPublishuserid() {
		return publishuserid;
	}
	public void setPublishuserid(String publishuserid) {
		this.publishuserid = publishuserid;
	}
	public String getPublishusername() {
		return publishusername;
	}
	public void setPublishusername(String publishusername) {
		this.publishusername = publishusername;
	}
}
