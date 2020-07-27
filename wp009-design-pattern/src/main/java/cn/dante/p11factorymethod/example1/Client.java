package cn.dante.p11factorymethod.example1;

public class Client {
	public static void main(String[] args) {
		ExportOperate operate = new ExportOperate();
		operate.export(2, "测试数据");
	}
}
