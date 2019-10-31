package cn.dante.jk.dao;

import java.util.Map;

import cn.dante.jk.domain.Factory;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
public interface FactoryDao extends BaseDao<Factory> {
	public void updateState(Map<String,Object> map);
}
