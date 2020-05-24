package cn.dante.jk.service.impl;

import cn.dante.jk.dao.SysCodeDao;
import cn.dante.jk.domain.SysCode;
import cn.dante.jk.service.SysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Service
public class SysCodeServiceImpl implements SysCodeService {
	@Autowired
	SysCodeDao sysCodeDao;
	
	public List<SysCode> find(Map paraMap) {
		return sysCodeDao.find(paraMap);
	}
	

}
