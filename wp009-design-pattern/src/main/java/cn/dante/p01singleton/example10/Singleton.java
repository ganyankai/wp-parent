package cn.dante.p01singleton.example10;

public class Singleton {
	/**
	 * 对保存实例的变量添加volatile的修饰,当其他线程修改了这个变量,当前线程马上就能获取到,
	 *  也就是多个线程共享该内存空间
	 */
	private volatile static Singleton instance = null;
	private Singleton(){
		
	}
	public static  Singleton getInstance(){
		//先检查实例是否存在，如果不存在才进入下面的同步块
		if(instance == null){
			//同步块，线程安全的创建实例
			synchronized(Singleton.class){
				//再次检查实例是否存在，如果不存在才真的创建实例
				if(instance == null){		//第二重检查
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}

