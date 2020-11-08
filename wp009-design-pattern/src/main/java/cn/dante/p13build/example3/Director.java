package cn.dante.p13build.example3;

import java.util.Collection;
import java.util.Map;

/**
 * 指导者，指导使用构建器的接口来构建输出的文件的对象
 */
public class Director {
    private Builder builder;

    public Director(Builder builder){
        this.builder = builder;
    }

    public void construct(ExportHeaderModel ehm, Map<String,Collection<ExportDataModel>> mapData, ExportFooterModel efm){
        builder.buildHeader(ehm);
        builder.buildBody(mapData);
        builder.buildFooter(efm);
    }

}
