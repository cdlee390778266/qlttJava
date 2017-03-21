package weixin.guanjia.user.service;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import weixin.guanjia.user.entity.WeixinTag;

public interface IUserTagService extends CommonService{
	
	@Override
 	public <T> void delete(T entity);
	
 	@Override
 	public <T> Serializable save(T entity);
 	
 	@Override
 	public <T> void saveOrUpdate(T entity);

 	/**
 	 *  新增一个标签
 	 */
	public String addWeixinTag(WeixinTag weixinTag);

	/**
	 * 删除一个标签
	 */
	public String delWeixinTag(WeixinTag weixinTag);
	
	/**
	 * 编辑一个标签
	 */
	public String updateWeixinTag(WeixinTag weixinTag);
}
