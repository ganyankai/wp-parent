package cn.dante.p02proxy;

public class DBQuery implements IDBQuery{
    public DBQuery(){
        try {
            Thread.sleep(1000); //可能包含数据库连接等操作
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public String request() {
        return "request str";
    }
}
