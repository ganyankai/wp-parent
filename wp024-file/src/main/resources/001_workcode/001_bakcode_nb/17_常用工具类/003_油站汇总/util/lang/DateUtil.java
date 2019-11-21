package com.catt.common.util.lang;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil extends DateUtils {
    public static final String yyMMdd = "yy-MM-dd";
    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static final String HHmmss = "HH:mm:ss";
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
    public static final String yyMMddHHmmss = "yy-MM-dd HH:mm:ss";
    /**
     * 毫秒
     */
    private static final long Millisecond = 1000;
    /**
     * 秒
     */
    private static final long SECOND = 1;
    /**
     * 分钟
     */
    private static final long MINUTE = 60;
    /**
     * 小时
     */
    private static final long HOUR = 60 * 60;
    /**
     * 一天
     */
    private static final long DAY = 60 * 60 * 24;
    /**
     * 一个月(30天为一个月)
     */
    private static final long MONTH = 60 * 60 * 24 * 30;
    //最小时间
    private static Date MIN_DATE = null;

    /**
     * 格式化日期为yy-MM-dd字符串
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return format(date, yyyyMMdd);
    }

    /**
     * 格式化日期为yyyy-MM-dd HH:mm:ss字符串
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        return format(date, yyyyMMddHHmmss);
    }

    /**
     * 将Date对象格式化为指定日期格式String
     *
     * @param date   日期
     * @param format 指定格式
     * @return 返回字符串
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 解析字符串为日期对象，使用常用的日期格式
     *
     * @param str
     * @return
     */
    public static Date parseDate(String str) {
        if (com.catt.common.util.lang.StringUtil.isBlank(str)) {
            return null;
        }

        str = str.trim();
        if (str.length() > 19) {
            str = str.substring(0, 19);
        }

        try {
            return parseDate(str, yyyyMMddHHmmss, yyyyMMddHHmm, yyyyMMdd);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象转日期
     *
     * @param obj
     * @return
     */
    public static Date parseDate(Object obj) {
        if (obj == null || obj instanceof Date) {
            return (Date) obj;
        }

        if (obj instanceof String) {
            return parseDate((String) obj);
        }

        throw new IllegalArgumentException("无法转换类型【" + obj.getClass().getName()
                + "】为java.util.Date类型");
    }

    /**
     * 清除日期对象的时间部分为0
     *
     * @param date
     * @return
     */
    public static Date clearTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        clearTime(calendar);
        return calendar.getTime();
    }

    /**
     * 清除时间部分
     *
     * @param calendar
     */
    public static void clearTime(Calendar calendar) {
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 获取当年的第一天
     *
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     *
     * @return
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * 得到本月的第一天的00:00:00
     *
     * @return 本月第一天零时的Date对象
     */
    public static Date getMonthFirstDateTime() {
        return getMonthFirstDateTime(0, new Date());
    }

    /**
     * 得到本月之后/前N月的第一天的00:00:00
     *
     * @param num 当前月份偏移量（正数往后推，负数往前推）
     * @return 本月第一天零时的Date对象
     */
    public static Date getMonthFirstDateTime(int num) {
        return getMonthFirstDateTime(num, new Date());
    }

    /**
     * 得到选取日期所在月份的第一天的00:00:00
     *
     * @param date 选取日期
     * @return 本月第一天零时的Date对象
     */
    public static Date getMonthFirstDateTime(Date date) {
        return getMonthFirstDateTime(0, date);
    }

    /**
     * 得到选取日期所在月份之后/前N月的第一天的00:00:00
     *
     * @param num  当前月份偏移量（正数往后推，负数往前推）
     * @param date 选取日期
     * @return 本月第一天零时的Date对象
     */
    public static Date getMonthFirstDateTime(int num, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + num);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY,
                calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE,
                calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND,
                calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND,
                calendar.getActualMinimum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * 得到本月的最后一天的 23:59:59
     *
     * @return 本月第一天 23:59:59 的Date对象
     */
    public static Date getMonthLastDateTime() {
        return getMonthLastDateTime(0, new Date());
    }

    /**
     * 得到本月之后/前N月的最后一天的 23:59:59
     *
     * @param num 当前月份偏移量（正数往后推，负数往前推）
     * @return 本月第一天 23:59:59 的Date对象
     */
    public static Date getMonthLastDateTime(int num) {
        return getMonthLastDateTime(num, new Date());
    }

    /**
     * 得到选取日期所在月份的最后一天的 23:59:59
     *
     * @param date 选取日期
     * @return 本月第一天 23:59:59 的Date对象
     */
    public static Date getMonthLastDateTime(Date date) {
        return getMonthLastDateTime(0, date);
    }

    /**
     * 得到选取日期所在月份之后/前N月的最后一天的 23:59:59
     *
     * @param num  当前月份偏移量（正数往后推，负数往前推）
     * @param date 选取日期
     * @return 本月第一天 23:59:59 的Date对象
     */
    public static Date getMonthLastDateTime(int num, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + num);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY,
                calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE,
                calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND,
                calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND,
                calendar.getActualMaximum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * 获取本周第一天的日期(周日)
     *
     * @return
     */
    public static Date getFirstWeekDay(Date date) {
        Calendar calendar = toCalendar(date);
        //把当前选择的日期强制设置到当前周的开始时间(周日)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        //临时时间
        Calendar calendarTemp = toCalendar(calendar.getTime());
        //日期定位到周六(一周最后一天)
        //calendarTemp.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        calendarTemp.getTime();
        //成立则表明已经跨年
        if (calendar.get(Calendar.YEAR) != calendarTemp.get(Calendar.YEAR)) {
            //跨年了，则最后一天为本周的结束时间
            calendar.set(Calendar.DATE, calendar.getMaximum(Calendar.DATE));
        } else {
            calendar = calendarTemp;
        }

        return calendar.getTime();
    }

    /**
     * 获取本周最后一天的日期(周六)
     *
     * @return
     */
    public static Date getLastWeekDay(Date date) {
        Calendar calendar = toCalendar(date);
        //把当前选择的日期强制设置到当前周的开始时间(周日)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        //临时时间
        Calendar calendarTemp = toCalendar(calendar.getTime());
        //日期定位到周六(一周最后一天)
        calendarTemp.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        calendarTemp.getTime();
        //成立则表明已经跨年
        if (calendar.get(Calendar.YEAR) != calendarTemp.get(Calendar.YEAR)) {
            //跨年了，则最后一天为本周的结束时间
            calendar.set(Calendar.DATE, calendar.getMaximum(Calendar.DATE));
        } else {
            calendar = calendarTemp;
        }

        return calendar.getTime();
    }

    /**
     * 返回1900-01-01 00:00:00 时间点
     *
     * @return
     */
    public static Date getMinDate() {
        if (MIN_DATE == null) {
            try {
                MIN_DATE = parseDate("1900-01-01 00:00:00",
                        new String[]{DATE_FORMAT.YYYY_MM_DD_HH_MM_SS.getFormat()});
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return MIN_DATE;
    }

    /**
     * 获取两个时间之间相差的月份数(以30天为单位计算)
     * 修改:
     * 梁展鹏  20160122（方法实现，参数返回类型）
     *
     * @param start
     * @param end
     * @return
     */
    public static Long getMonthsBetween(Date start, Date end) {
        Calendar calStart = com.catt.common.util.lang.DateUtil.toCalendar(start);
        Calendar calEnd = com.catt.common.util.lang.DateUtil.toCalendar(end);
        clearTime(calStart);
        clearTime(calEnd);

        /*Integer days =
                (Math.abs(calStart.get(Calendar.YEAR) - calEnd.get(Calendar.YEAR)) * 12)
                        + (Math.abs(calStart.get(Calendar.MONTH) - calEnd.get(Calendar.MONTH)));*/

        Long days = Math.abs((calStart.getTimeInMillis() - calEnd.getTimeInMillis()) / MONTH / 1000);

        return days;
    }

    /**
     * 获取两个时间之间相差的年份数
     *
     * @param start
     * @param end
     * @return
     */
    public static Integer getYearsBetween(Date start, Date end) {
        Calendar calStart = com.catt.common.util.lang.DateUtil.toCalendar(start);
        Calendar calEnd = com.catt.common.util.lang.DateUtil.toCalendar(end);
        clearTime(calStart);
        clearTime(calEnd);

        Integer days = Math.abs(calStart.get(Calendar.YEAR) - calEnd.get(Calendar.YEAR));

        return days;
    }

    /**
     * 获取两个时间之间相差的天数
     *
     * @param start
     * @param end
     * @return
     */
    public static Integer getDaysBetween(Date start, Date end) {
        Calendar calStart = com.catt.common.util.lang.DateUtil.toCalendar(start);
        Calendar calEnd = com.catt.common.util.lang.DateUtil.toCalendar(end);
        //精确到日期，忽略时分秒
        clearTime(calStart);
        clearTime(calEnd);

        Long days = (Math.abs(calStart.getTimeInMillis() - calEnd.getTimeInMillis())) / (1000 * 3600 * 24);

        return days.intValue();
    }

    /**
     * 获取两个时间之间相差的秒数
     *
     * @param start
     * @param end
     * @return
     */
    public static Long getSecondBetween(Date start, Date end) {
        Calendar calStart = com.catt.common.util.lang.DateUtil.toCalendar(start);
        Calendar calEnd = com.catt.common.util.lang.DateUtil.toCalendar(end);


        Long seconds = (Math.abs(calStart.getTimeInMillis() - calEnd.getTimeInMillis())) / (1000);

        return seconds;
    }

    /**
     * 获取两个时间之间相差的小时数
     * @param start
     * @param end
     * @return
     */
    public static Long getHourBetween(Date start, Date end) {
        Long secondBetween = getSecondBetween(start, end);
        long h = secondBetween / 60 / 60;
        return h;
    }

    /**
     * 获取两个时间点相差的周数
     *
     * @param start
     * @param end
     * @return
     */
    public static Integer getWeeksBetween(Date start, Date end) {
        Calendar calStart = com.catt.common.util.lang.DateUtil.toCalendar(start);
        Calendar calEnd = com.catt.common.util.lang.DateUtil.toCalendar(end);
        clearTime(calStart);
        clearTime(calEnd);

        Integer weeks =
                (Math.abs(calStart.get(Calendar.YEAR) - calEnd.get(Calendar.YEAR)) * 53)
                        + (Math.abs(calStart.get(Calendar.WEEK_OF_YEAR) - calEnd.get(Calendar.WEEK_OF_YEAR)));

        return weeks;
    }

    /**
     * 获取日期是第几周
     *
     * @param date
     * @return
     */
    public static String getWeekOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        // 一周中第一天为周日
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR) + "";
    }

    /**
     * 获取当前日期比邻日期
     * 梁展鹏 20151219
     *
     * @param dateFormat    时间格式化输出格式
     * @param nearDayNumber 临近多少天(+后多少天，-前多少天)
     * @return
     */
    public static String getNearByDay(String dateFormat, Integer nearDayNumber) {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, nearDayNumber);
        return sf.format(calendar.getTime());
    }

    /**
     * 获取当前日期比邻日期
     * 周明祥 20160115
     *
     * @param nearDayNumber 临近多少天(+后多少天，-前多少天)
     * @return
     */
    public static Date getNearByDay(Integer nearDayNumber) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, nearDayNumber);
        return calendar.getTime();
    }

    /**
     * 获取当前日期比邻日期
     * 梁展鹏 20160127
     *
     * @param date          参考时间（为空则当前时间参考）
     * @param nearDayNumber 临近多少天(+后多少天，-前多少天)
     * @return
     */
    public static Date getNearByDay(Date date, Integer nearDayNumber) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.DAY_OF_MONTH, nearDayNumber);
        return calendar.getTime();
    }

    /**
     * 获取日期比邻日期
     * 梁展鹏 20160120
     *
     * @param dateFormat  时间格式化输出格式
     * @param date        时间(不传则代表用当前时间作为参照中心)
     * @param nearDayTime 临近多少天(+后多少天，-前多少天)
     * @return
     */
    public static String getNearByDay(String dateFormat, Date date, Integer nearDayTime) {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.DAY_OF_MONTH, nearDayTime);
        return sf.format(calendar.getTime());
    }

    /**
     * 获取日期比邻月份
     * 梁展鹏 20160121
     *
     * @param dateFormat  时间格式化输出格式
     * @param date        时间(不传则代表用当前时间作为参照中心)
     * @param nearDayTime 临近多少月(+后多少月，-前多少月)
     * @return
     */
    public static String getNearByMonth(String dateFormat, Date date, Integer nearDayTime) {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.MONTH, nearDayTime);
        return sf.format(calendar.getTime());
    }

    /**
     * 获取日期比邻时间
     * 周明祥 20160527
     *
     * @param dateFormat  时间格式化输出格式
     * @param date        时间(不传则代表用当前时间作为参照中心)
     * @param nearDayTime 临近多少小时(+后多少小时，-前多少小时)
     * @return
     */
    public static String getNearByHour(String dateFormat, Date date, Integer nearDayTime) {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.HOUR, nearDayTime);
        return sf.format(calendar.getTime());
    }

    /**
     * 获取日期比邻时间
     * 周明祥 20160527
     *
     * @param date        时间(不传则代表用当前时间作为参照中心)
     * @param nearDayTime 临近多少小时(+后多少小时，-前多少小时)
     * @return
     */
    public static Date getNearByHour(Date date, Integer nearDayTime) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.HOUR, nearDayTime);
        return calendar.getTime();
    }

    /**
     * 获取日期比邻时间（年）
     * 周明祥 20170227
     *
     * @param date        时间(不传则代表用当前时间作为参照中心)
     * @param nearDayTime 临近多少年(+后多少年，-前多少年)
     * @return
     */
    public static Date getNearByYear(Date date, Integer nearDayTime) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.YEAR, nearDayTime);
        return calendar.getTime();
    }

    /**
     * jdk8中的时间对象转换为Date类型
     * @param localDateTime jdk8中的时间对象
     * @return
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 时间格式,针对Java，非数据库
     */
    public enum DATE_FORMAT {
        /**
         * yyyy-MM-dd HH:mm:ss
         */
        YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
        /**
         * yyyy-MM-dd
         */
        YYYY_MM_DD("yyyy-MM-dd"),
        /**
         * yyyy-MM
         */
        YYYY_MM("yyyy-MM");

        private String format;

        DATE_FORMAT(String format) {
            this.format = format;
        }

        public String getFormat() {
            return this.format;
        }
    }

}
