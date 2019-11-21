package com.catt.common.util.convert;

import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

/**
 * OpenOffice服务工厂类
 * Created by Sebarswee on 2014/11/1.
 */
public class OfficeManagerFactory {
    private OfficeManager officeManager;

    private OfficeManagerFactory() {
        officeManager = new DefaultOfficeManagerConfiguration()
                .setOfficeHome(com.catt.common.util.ConfigUtils.config.getProperty("OpenOfficeHome"))
                .setMaxTasksPerProcess(300000)
                .buildOfficeManager();
        officeManager.start();
    }

    private static class SingletonHolder {
        private static final com.catt.common.util.convert.OfficeManagerFactory INSTANCE = new com.catt.common.util.convert.OfficeManagerFactory();
    }

    public static com.catt.common.util.convert.OfficeManagerFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取officeManager
     * @return officeManager
     */
    public OfficeManager getOfficeManager() {
        return officeManager;
    }

    /**
     * 停止officeManager
     */
    public void stopOfficeManager() {
        this.officeManager.stop();
    }
}
