package cn.dante.p03xiangyuan;

public class FinancialReportManager implements IReportManager{
    protected  String tenantId = null;

    public FinancialReportManager(String tenantId){
        this.tenantId = tenantId;
    }

    public String createReport() {
        return "this is a financial report ";
    }


}
