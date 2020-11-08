package cn.dante.p12abstractfactory.example6.business;

import cn.dante.p12abstractfactory.example6.dao.DAOFactory;
import cn.dante.p12abstractfactory.example6.dao.MyDaoFactory;
import cn.dante.p12abstractfactory.example6.dao.impl.OrderDetailDAO;
import cn.dante.p12abstractfactory.example6.dao.impl.OrderMainDAO;
import cn.dante.p12abstractfactory.example6.dao.impl.XmlDAOFactory;

public class BusinessObject {
	public static void main(String[] args) {
		//创建DAO的抽象工厂
//		DAOFactory df = new RdbDAOFactory();
//		DAOFactory df = new XmlDAOFactory();

		DAOFactory df = MyDaoFactory.createDAOFactory(1);
		//通过抽象工厂来获取需要的DAO接口
		OrderMainDAO mainDAO = df.createOrderMainDAO();
		OrderDetailDAO detailDAO = df.createOrderDetailDAO();
		//调用DAO来完成数据存储的功能
		mainDAO.saveOrderMain();
		detailDAO.saveOrderDetail();
	}
}
