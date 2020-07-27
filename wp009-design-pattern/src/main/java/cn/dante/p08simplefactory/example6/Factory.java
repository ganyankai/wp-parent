package cn.dante.p08simplefactory.example6;

/**
 * 工厂类，用来创造Api对象
 */
public class Factory {
	private static int count = 0;

	private Factory(){

	}

	/**
	 * 具体的创造Api对象的方法
	 * @return 创造好的Api对象
	 */
	public static Api createApi(){
		//应该根据某些条件去选择究竟创建哪一个具体的实现对象
		//这些条件可以从外部传入，也可以从其它途径获取
		//如果只有一个实现，可以省略条件，因为没有选择的必要

		//示意使用条件
		Api api = null;
		if(count<3){
			api = new ImplA();
			count ++;
		}else {
			api = new ImplB();
			count ++;
		}
		return api;
	}
}