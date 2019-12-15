package cn.dante.p03xiangyuan;

public class EmployeeReportManager implements IReportManager{
    protected  String tenantId = null;

    public EmployeeReportManager(String tenantId){
        this.tenantId = tenantId;
    }

    public String createReport() {
        return "this is a employee report ";
    }

}
