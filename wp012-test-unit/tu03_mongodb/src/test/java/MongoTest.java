import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoTest {
    public static void main(String[] args) {
        //连接mongo服务器
        MongoClient client=new MongoClient("127.0.0.1");//创建连接
        //得到要操作的数据库
        MongoDatabase spitdb = client.getDatabase("spitdb");//打开数据库

        MongoCollection<Document> spit = spitdb.getCollection("spit");//获取集合

        //封装查询条件 查询用户id为1013
//        BasicDBObject bson = new BasicDBObject("userid","1013");

        //查询访问量大于1000的
        BasicDBObject bson = new BasicDBObject("visits",new BasicDBObject("$gt",1000));
        FindIterable<Document> documents = spit.find();//查询记录获取文档集合
//        FindIterable<Document> documents = spit.find(bson);//查询记录获取文档集合

        for(Document document:documents){ //
            System.out.println("内容："+ document.getString("content"));
            System.out.println("用户ID:"+document.getString("userid"));
            System.out.println("浏览量："+document.getInteger("visits"));
        }
        client.close();//关闭连接
    }
}
