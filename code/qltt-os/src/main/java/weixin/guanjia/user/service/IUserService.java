package weixin.guanjia.user.service;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public interface IUserService extends CommonService{
	
	@Override
 	public <T> void delete(T entity);
	
 	@Override
 	public <T> Serializable save(T entity);
 	
 	@Override
 	public <T> void saveOrUpdate(T entity);
}
