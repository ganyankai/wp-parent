package cn.dante.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.dante.jk.domain.Factory;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
public interface FactoryService {

	public List<Factory> find(Map paraMap);
	public Factory get(Serializable id);
	public void insert(Factory factory);
	public void update(Factory factory);
	public void deleteById(Serializable id);
	public void delete(Serializable[] ids);
	
	public void changeState(Map<String,Object> map);
	public void start(Serializable[] ids);
	public void stop(Serializable[] ids);
}

