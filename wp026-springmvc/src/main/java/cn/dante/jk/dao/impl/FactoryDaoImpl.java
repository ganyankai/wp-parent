package cn.dante.jk.dao.impl;

import java.util.Map;

import cn.dante.jk.dao.FactoryDao;
import cn.dante.jk.domain.Factory;
import org.springframework.stereotype.Repository;


/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Repository
public class FactoryDaoImpl extends BaseDaoImpl<Factory> implements FactoryDao {
	public FactoryDaoImpl() {
		this.setNs("cn.dante.jk.mapper.FactoryMapper.");			//设置命名空间
	}

	public void changeState(Map<String, Object> map) {
//		this.getSqlSession().update(this.getNs() + "changeState", map);
	}

}
