package com.catt.common.util.template.beetl;

import org.beetl.core.GroupTemplate;
import org.beetl.core.Resource;
import org.beetl.core.engine.FastRuntimeEngine;
import org.beetl.core.engine.StatementParser;
import org.beetl.core.statement.Program;
import org.beetl.core.statement.Statement;
import org.beetl.core.statement.VarRef;

import java.io.Reader;
import java.util.Map;

/**
 * <pre>
 * 扩展默认模板引擎，提供模板使用POJO属性的能力
 * @author : Zhang zhongtao
 * @version : Ver 1.0
 * </pre>
 */
public class ExtFastRuntimeEngine extends FastRuntimeEngine {
    public Program createProgram(Resource resource, Reader reader, Map<Integer, String> textMap, String cr,
                                 GroupTemplate gt) {
        Program program = super.createProgram(resource, reader, textMap, cr, gt);
        modifyStatemetn(resource, program, gt);

        return program;
    }

    private void modifyStatemetn(Resource resource, Program program, GroupTemplate gt) {
        Statement[] sts = program.metaData.statements;
        StatementParser parser = new StatementParser(sts, gt, resource.getId());
        //使用自定义的模板监听器
        parser.addListener(VarRef.class, new ListenerImpl());

        parser.parse();
    }
}
