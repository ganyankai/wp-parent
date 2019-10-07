package cn.dante.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.dante.jk.domain.Factory;

public interface FactoryService {
	public List<Factory> find(Map paraMap);
	public Factory get(Serializable id);
	public void insert(Factory factory);
	public void update(Factory factory);
	public void deleteById(Serializable id);
	public void delete(Serializable[] ids);

	public void changeState(Map<String, Object> map);
}

