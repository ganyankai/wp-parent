package cn.dante.p03xiangyuan;


public class Main {
    public static void main(String[] args) {
        ReportManagerFactory factory = new ReportManagerFactory();
        IReportManager a = factory.getEmployeeReportManager("A");
        IReportManager a2 = factory.getEmployeeReportManager("A");
        IReportManager b = factory.getEmployeeReportManager("B");
        System.out.println(a==a2);
        System.out.println(a==b);
        System.out.println(a.createReport());
    }
}
