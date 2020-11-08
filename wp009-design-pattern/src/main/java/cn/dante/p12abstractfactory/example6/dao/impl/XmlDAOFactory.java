package cn.dante.p12abstractfactory.example6.dao.impl;

import cn.dante.p12abstractfactory.example6.dao.DAOFactory;

public class XmlDAOFactory implements DAOFactory {
	public OrderDetailDAO createOrderDetailDAO() {
		return new XmlDetailDAOImpl();
	}
	public OrderMainDAO createOrderMainDAO() {
		return new XmlMainDAOImpl();
	}
}