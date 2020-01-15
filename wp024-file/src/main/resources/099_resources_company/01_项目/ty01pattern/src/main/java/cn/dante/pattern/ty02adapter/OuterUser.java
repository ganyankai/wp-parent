package cn.dante.pattern.ty02adapter;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
//那怎么把外系统的用户信息包装成我们公司的人员信息呢？
public class OuterUser implements IOuterUser {
    /*
     * 用户的基本信息
     */
    public Map getUserBaseInfo() {
        HashMap baseInfoMap = new HashMap();
        baseInfoMap.put("userName", "这个员工叫混世魔王....");
        baseInfoMap.put("mobileNumber", "这个员工电话是....");
        return baseInfoMap;
    }

    public Map getUserOfficeInfo() {
        HashMap homeInfo = new HashMap();
        homeInfo.put("homeTelNumbner", "员工的家庭电话是....");
        homeInfo.put("homeAddress", "员工的家庭地址是....");
        return homeInfo;
    }

    public Map getUserHomeInfo() {
        HashMap officeInfo = new HashMap();
        officeInfo.put("jobPosition","这个人的职位是BOSS...");
        officeInfo.put("officeTelNumber", "员工的办公电话是....");
        return officeInfo;
    }

}