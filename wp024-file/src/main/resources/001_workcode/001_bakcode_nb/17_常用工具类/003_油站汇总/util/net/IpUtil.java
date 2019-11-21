package com.catt.common.util.net;

import com.catt.common.util.collections.MapUtil;
import com.catt.common.util.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * IP工具类
 *
 * @author 纪建宏
 */
public class IpUtil {
    /**
     * IP信息键-省编码
     */
    public static final String KEY_PROVINCE_CODE = "PROVINCE_CODE";
    /**
     * IP信息键-省名称
     */
    public static final String KEY_PROVINCE_NAME = "PROVINCE_NAME";
    /**
     * IP信息键-市编码
     */
    public static final String KEY_CITY_CODE = "CITY_CODE";
    /**
     * IP信息键-市名称
     */
    public static final String KEY_CITY_NAME = "CITY_NAME";

    private static final Logger LOG = LoggerFactory.getLogger(com.catt.common.util.net.IpUtil.class);

    /**
     * 获取指定IP的相关信息
     *
     * @param ip IP地址
     * @return IP相关信息，失败为null
     */
    public static Map<String, Object> getIpInfo(String ip) {
        try {
            String ipInfoStr = HttpUtil
                    .get("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            if (com.catt.common.util.lang.StringUtil.isNotBlank(ipInfoStr)) {
                Map<String, Object> m = JsonUtils.toObject(ipInfoStr, Map.class);
                if (m != null && "0".equals(MapUtil.getString(m, "code"))) {
                    Map data = (Map) m.get("data");
                    if (data != null) {
                        Map<String, Object> ipInfo = new HashMap<String, Object>();
                        ipInfo.put(KEY_PROVINCE_CODE, data.get("region_id"));
                        ipInfo.put(KEY_PROVINCE_NAME, data.get("region"));
                        ipInfo.put(KEY_CITY_CODE, data.get("city_id"));
                        ipInfo.put(KEY_CITY_NAME, data.get("city"));
                        return ipInfo;
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("调用IP信息获取接口失败", e);
        }

        return null;
    }

}
