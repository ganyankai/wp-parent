package cn.dante.p11factorymethod.example3;

public class ExportXmlFileOperate extends ExportOperate{

    protected ExportFileApi factoryMethod() {
        return new ExportXmlFile();
    }
}
