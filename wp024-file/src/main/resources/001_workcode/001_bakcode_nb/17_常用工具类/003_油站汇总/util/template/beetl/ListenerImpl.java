package com.catt.common.util.template.beetl;

import org.beetl.core.Event;
import org.beetl.core.Listener;
import org.beetl.core.statement.VarRef;

import java.util.Stack;

/**
 * <pre>
 *  扩展默认监听器，使用 自定义ExtVarRef的对象
 * @author : Zhang zhongtao
 * @version : Ver 1.0
 * </pre>
 */
public class ListenerImpl implements Listener {

    @Override
    public Object onEvent(Event event) {
        Stack stack = (Stack) event.getEventTaget();
        Object o = stack.peek();
        if (o instanceof VarRef) {
            VarRef ref = (VarRef) o;
            return new com.catt.common.util.template.beetl.ExtVarRef(ref);
        } else {
            return null;
        }
    }
}
