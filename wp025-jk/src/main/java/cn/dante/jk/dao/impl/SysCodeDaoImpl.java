package cn.dante.jk.dao.impl;

import cn.dante.jk.dao.FactoryDao;
import cn.dante.jk.dao.SysCodeDao;
import cn.dante.jk.domain.Factory;
import cn.dante.jk.domain.SysCode;
import org.springframework.stereotype.Repository;

import java.util.Map;


/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Repository
public class SysCodeDaoImpl extends BaseDaoImpl<SysCode> implements SysCodeDao {
	public SysCodeDaoImpl() {
		this.setNs("cn.dante.jk.mapper.SysCodeMapper.");			//设置命名空间
	}

}
