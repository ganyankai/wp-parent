package cn.dante.p03xiangyuan;

public interface IReportManager {
    //所有的报表生成类将作为享元对象在一个公司中共享
    public String createReport();

}
