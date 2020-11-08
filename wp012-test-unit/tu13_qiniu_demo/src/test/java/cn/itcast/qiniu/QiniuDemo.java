package cn.itcast.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class QiniuDemo {
    /**
     * 将图片上传到七牛云
     */
    @Test
    public void testUpload01(){
        /**
         * 将图片上传到七牛云服务
         *      1.更新用户图片信息（用户id=key）
         *      2.访问图片
         *          存储空间分配的：http://pkbivgfrm.bkt.clouddn.com
         *          上传的文件名
         *          更新图片之后：访问的时候，再请求连接添加上时间戳
         *
         */

        //构造一个带指定Zone对象的配置类

        //指定上传文件服务器地址：
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        //上传管理器
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "WFzeaLCOkcLsjUnaWiyU3kdwvehps9IeldM9HRrY";
        String secretKey = "IUd1ETEfn9S3p2LmmnqsDrkY3QbQn3D9zdz8SLVD";
        String bucket = "ihrm-bucket-dante";

        //图片路径
        String localFilePath = "C:\\Users\\admin\\Desktop\\test\\t02\\003.png";

        //存入到存储空间的文件名
        String key = "test1";

        //身份认证
        Auth auth = Auth.create(accessKey, secretKey);
        //指定覆盖上传
        String upToken = auth.uploadToken(bucket,key);

//        String upToken = auth.uploadToken(bucket);
        try {
            //上传
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }


    @Test
    public void testUpload02() throws Exception{
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        //...其他参数参考类注释
        //...生成上传凭证，然后准备上传
        String accessKey = "WFzeaLCOkcLsjUnaWiyU3kdwvehps9IeldM9HRrY";
        String secretKey = "3RWpTjB5Jxg3QosUFr4mxbHXJ5JR2m6AHQqYsSlr";
        String bucket = "ihrm-bucket-dante";
    //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "E:\\003_javaext\\022_itcast_hrm\\09-图片上传及Jasper\\01-文件上传与PDF报表入门\\代码\\ihrm_parent.zip";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "testExcel";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
//        String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), bucket).toString();
        String localTempDir = Paths.get(System.getProperty("java.io.tmpdir"), bucket).toString();
        try {
            //设置断点续传文件进度保存目录
            FileRecorder fileRecorder = new FileRecorder(localTempDir);
            UploadManager uploadManager = new UploadManager(cfg, fileRecorder);
            try {
                Response response = uploadManager.put(localFilePath, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
