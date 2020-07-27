package cn.dante.p08simplefactory.example5;

/**
 * 客户端，使用Api接口
 */
public class Client {
	public static void main(String[] args) {
		//通过简单工厂来获取接口对象
		Api api = Factory.createApi();
		api.operation("正在使用简单工厂");
	}
}
