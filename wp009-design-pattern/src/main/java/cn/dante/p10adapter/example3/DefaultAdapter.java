package cn.dante.p10adapter.example3;

import java.util.List;

public class DefaultAdapter implements LogDbOperateApi{
    public void createLog(LogModel lm) {
        System.out.println("createLog db======");
    }

    public void updateLog(LogModel lm) {
        System.out.println("updateLog db======");
    }

    public void removeLog(LogModel lm) {

    }

    public List<LogModel> getAllLog() {
        return null;
    }
}
