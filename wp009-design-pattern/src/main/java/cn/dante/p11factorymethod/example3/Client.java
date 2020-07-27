package cn.dante.p11factorymethod.example3;

public class Client {
	public static void main(String[] args) {
		//创建需要使用的Creator对象
//		ExportOperate operate = new ExportDBOperate();
		ExportOperate operate = new ExportXmlFileOperate();
		//调用输出数据的功能方法
		operate.export("测试数据");

//		ABC abc = new ABC();
		ABC abc = new ExportOperate().createABC("cc");
		abc.test();
	}
}
