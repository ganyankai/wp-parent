import org.apache.commons.lang.math.NumberUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * <pre>
 * Description:
 * Author: Zhang zhongtao
 * Version:
 * Since: Ver 1.1
 * Date: 2014-11-03 23:34
 *  double的计算不精确，会有类似0.0000000000000002的误差，正确的方法是使用BigDecimal或者用整型
 * 整型地方法适合于货币精度已知的情况，比如12.11+1.10转成1211+110计算，最后再/100即可
 * 以下是摘抄的BigDecimal方法:
 * </pre>
 */
public class NumberUtil extends NumberUtils {

	// 默认除法运算精度
	private static final Integer DEF_DIV_SCALE = 2;

	/**
	 * 格式化double类型
	 *
	 * @param d
	 *            double类型数据
	 * @return Double
	 */
	public static Double format(Double d) {
		return format(d, 2);
	}

	/**
	 * 格式化double类型
	 * 
	 * @param d
	 *            源值
	 * @param roundingMode
	 *            舍入类型
	 * @return 目标值
	 */
	public static Double format(Double d, RoundingMode roundingMode) {
		return format(d, 2, roundingMode);
	}

	/**
	 * 格式化double类型
	 *
	 * @param d
	 *            double类型数据
	 * @param pos
	 *            精确小数位数
	 * @return Double
	 */
	public static Double format(Double d, Integer pos) {
		return format(d, pos, RoundingMode.HALF_EVEN);
	}

	/**
	 * 格式化double类型
	 * 
	 * @param d
	 *            源值
	 * @param pos
	 *            精确小数位数
	 * @param roundingMode
	 *            舍入类型
	 * @return 目标值
	 */
	public static Double format(Double d, Integer pos, RoundingMode roundingMode) {
		if (d == null) {
			return null;
		}

		if (pos == null || pos < 0) {
			return d;
		}

		String formatStr = StringUtil.rightPad("0.", pos + 2, "0");
		DecimalFormat df = new DecimalFormat(formatStr);
		df.setRoundingMode(roundingMode);
		return Double.valueOf(df.format(d));
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param value1
	 *            被加数
	 * @param value2
	 *            加数
	 * @return 两个参数的和
	 */
	public static Double add(double value1, double value2) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1));
		BigDecimal b2 = new BigDecimal(Double.toString(value2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 *
	 * @param value1
	 *            被减数
	 * @param value2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double value1, double value2) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1));
		BigDecimal b2 = new BigDecimal(Double.toString(value2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 *
	 * @param value1
	 *            被乘数
	 * @param value2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static Double mul(double value1, double value2) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1));
		BigDecimal b2 = new BigDecimal(Double.toString(value2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时， 精确到小数点以后10位，以后的数字四舍五入。
	 *
	 * @param dividend
	 *            被除数
	 * @param divisor
	 *            除数
	 * @return 两个参数的商
	 */
	public static Double div(Double dividend, Double divisor) {
		return div(dividend, divisor, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
	 *
	 * @param dividend
	 *            被除数
	 * @param divisor
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static Double div(Double dividend, Double divisor, Integer scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(dividend));
		BigDecimal b2 = new BigDecimal(Double.toString(divisor));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 *
	 * @param value
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static Double round(Double value, Integer scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(value));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 金钱相加
	 * 
	 * @param value1
	 *            被加数
	 * @param value2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double moneyAdd(double value1, double value2) {
		long money1 = (long) (value1 * 100);
		long money2 = (long) (value2 * 100);
		return format((money1 + money2) / 100.0);
	}

	/**
	 * 金钱相减
	 * 
	 * @param value1
	 *            被减数
	 * @param value2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double moneySub(double value1, double value2) {
		long money1 = (long) (value1 * 100);
		long money2 = (long) (value2 * 100);
		return format((money1 - money2) / 100.0);
	}

    /**
     * 提供精确的小数位取舍处理。
     *
     * @param value
     *            需要取舍的数字
     * @param scale
     *            小数点后保留几位
     * @param roundMode
     *            取舍类型 0-7
     * @return 取舍后的结果
     */
    public static Double round(Double value, Integer scale, int roundMode) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(value));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, roundMode).doubleValue();
    }

}
