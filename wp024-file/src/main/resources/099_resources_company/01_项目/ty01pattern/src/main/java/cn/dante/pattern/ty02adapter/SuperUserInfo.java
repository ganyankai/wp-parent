package cn.dante.pattern.ty02adapter;

public class SuperUserInfo extends SuperUser implements IUserInfo{
    private String fly = super.fly();

    public String getUserName() {
        return null;
    }

    public String getHomeAddress() {
        return null;
    }

    public String getMobileNumber() {
        return null;
    }

    public String getOfficeTelNumber() {
        return null;
    }

    public String getJobPosition() {
        return null;
    }

    public String getHomeTelNumber() {
        return fly;
    }
}
