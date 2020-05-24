package cn.dante.jk.service;

import cn.dante.jk.domain.Factory;
import cn.dante.jk.domain.SysCode;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
public interface SysCodeService {

	public List<SysCode> find(Map paraMap);

}

