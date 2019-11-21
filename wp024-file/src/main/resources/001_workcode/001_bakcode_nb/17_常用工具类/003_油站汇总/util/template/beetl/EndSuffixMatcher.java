package com.catt.common.util.template.beetl;

import org.beetl.core.resource.Matcher;

/**
 * <pre>
 *
 * @author : Zhang zhongtao
 * @version : Ver 1.0
 * </pre>
 */
public class EndSuffixMatcher implements Matcher {
    private String suffix;

    private String[] suffixs;

    public EndSuffixMatcher() {
    }

    public EndSuffixMatcher(String suffix) {
        suffixs = suffix.split(",");
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        suffixs = suffix.split(",");
        this.suffix = suffix;
    }

    @Override
    public String match(String key) {

        for (String s : suffixs) {
            if (key.contains(s)) {
                return key;
            }
        }

        return null;
    }
}
