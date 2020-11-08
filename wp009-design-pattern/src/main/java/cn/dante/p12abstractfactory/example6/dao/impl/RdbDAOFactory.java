package cn.dante.p12abstractfactory.example6.dao.impl;

import cn.dante.p12abstractfactory.example6.dao.DAOFactory;

public class RdbDAOFactory implements DAOFactory {
	public OrderDetailDAO createOrderDetailDAO() {
		return new RdbDetailDAOImpl();
	}
	public OrderMainDAO createOrderMainDAO() {
		return new RdbMainDAOImpl();
	}
}
