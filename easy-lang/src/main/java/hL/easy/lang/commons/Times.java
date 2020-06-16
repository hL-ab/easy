package hL.easy.lang.commons;

import hL.easy.lang.simulation.commons.lang.StringUtils;
import hL.easy.lang.simulation.slf4j.Logger.Logger;
import hL.easy.lang.simulation.slf4j.Logger.LoggerFactory;
import java.time.LocalDate;

/**
 * @author @HL
 * @since 0.1.001
 */
public final class Times {

    private static final Logger logger = LoggerFactory.getLogger(Times.class);

    /**
     * 获取运行环境的当前日期。{@link java.time.LocalDate}<br/>
     *
     * @return 当前日期
     */
    public static String now() {
        return LocalDate.now().toString();
    }

    /**
     * 以指定长度格式化指定时间。<br/> 若指定长度不可解释，将指定时间原样返回。<br/>
     *
     * <b>以时间2010年1月1日12时0分0秒为例，目前支持：</b><br/>
     * <i>0:2010-01-01</i><br/>
     * <i>4:2010</i><br/>
     * <i>6:201001</i><br/>
     * <i>7:2010-01</i><br/>
     * <i>8:20100101</i><br/>
     * <i>10:2010-01-01</i><br/>
     * <i>14:20100101120000</i><br/>
     * <i>15:20100101 120000</i><br/>
     * <i>16:2010-01-01120000 或 2010010112:00:00</i>（取决于指定时间中有无符号<i>-</i>）<br/>
     * <i>16:2010-01-01 120000 或 20100101 12:00:00</i>（取决于指定时间中有无符号<i>-</i>）</i><br/>
     * <i>18:2010-01-0112:00:00</i><br/>
     * <i>19:2010-01-01 12:00:00</i><br/>
     *
     * @param prev   要转换的时间
     * @param length 目标时间的长度
     * @return 符合指定长度的格式化时间字符串
     */
    public static String format(String prev, int length) {
        if (StringUtils.isBlank(prev)) {
            prev = now();
        }
        int l = prev.length();
        if (length == 0) {
            length = 10;
        }
        String yyyy = "";
        String mM = "01";
        String dd = "01";
        String hh = "00";
        String mm = "00";
        String ss = "00";
        switch (l) {
            case 4:
                yyyy = prev;
                break;
            case 6:
                yyyy = prev.substring(0, 4);
                mM = prev.substring(4);
                break;
            case 7:
                yyyy = prev.substring(0, 4);
                mM = prev.substring(5);
                break;
            case 8:
                yyyy = prev.substring(0, 4);
                mM = prev.substring(4, 6);
                dd = prev.substring(6);
                break;
            case 10:
                yyyy = prev.substring(0, 4);
                mM = prev.substring(5, 7);
                dd = prev.substring(9);
                break;
            case 14:
                yyyy = prev.substring(0, 4);
                mM = prev.substring(4, 6);
                dd = prev.substring(6, 8);
                hh = prev.substring(8, 10);
                mm = prev.substring(10, 12);
                ss = prev.substring(12);
                break;
            case 15:
                yyyy = prev.substring(0, 4);
                mM = prev.substring(4, 6);
                dd = prev.substring(6, 8);
                hh = prev.substring(9, 11);
                mm = prev.substring(11, 13);
                ss = prev.substring(13);
                break;
            case 16:
                if (prev.indexOf('-') < 0) {
                    yyyy = prev.substring(0, 4);
                    mM = prev.substring(4, 6);
                    dd = prev.substring(6, 8);
                    hh = prev.substring(8, 10);
                    mm = prev.substring(11, 13);
                    ss = prev.substring(15);
                } else {
                    yyyy = prev.substring(0, 4);
                    mM = prev.substring(5, 7);
                    dd = prev.substring(8, 10);
                    hh = prev.substring(10, 12);
                    mm = prev.substring(12, 14);
                    ss = prev.substring(14);
                }
                break;
            case 17:
                if (prev.indexOf('-') < 0) {
                    yyyy = prev.substring(0, 4);
                    mM = prev.substring(4, 6);
                    dd = prev.substring(6, 8);
                    hh = prev.substring(9, 11);
                    mm = prev.substring(12, 14);
                    ss = prev.substring(16);
                } else {
                    yyyy = prev.substring(0, 4);
                    mM = prev.substring(5, 7);
                    dd = prev.substring(8, 10);
                    hh = prev.substring(11, 13);
                    mm = prev.substring(13, 15);
                    ss = prev.substring(15);
                }
                break;
            case 18:
                yyyy = prev.substring(0, 4);
                mM = prev.substring(5, 7);
                dd = prev.substring(8, 10);
                hh = prev.substring(10, 12);
                mm = prev.substring(13, 15);
                ss = prev.substring(16);
                break;
            case 19:
                yyyy = prev.substring(0, 4);
                mM = prev.substring(5, 7);
                dd = prev.substring(8, 10);
                hh = prev.substring(11, 13);
                mm = prev.substring(14, 16);
                ss = prev.substring(17);
                break;
        }
        switch (length) {
            case 4:
                return yyyy;
            case 6:
                return yyyy + mM;
            case 7:
                return yyyy + "-" + mM;
            case 8:
                return yyyy + mM + dd;
            case 10:
                return yyyy + "-" + mM + "-" + dd;
            case 14:
                return yyyy + mM + dd + hh + mm + ss;
            case 15:
                return yyyy + mM + dd + " " + hh + mm + ss;
            case 16:
                if (prev.indexOf('-') < 0) {
                    return yyyy + mM + dd + hh + ":" + mm + ":" + ss;
                } else {
                    return yyyy + "-" + mM + "-" + dd + hh + mm + ss;
                }
            case 17:
                if (prev.indexOf('-') < 0) {
                    return yyyy + mM + dd + " " + hh + ":" + mm + ":" + ss;
                } else {
                    return yyyy + "-" + mM + "-" + dd + " " + hh + mm + ss;
                }
            case 18:
                return yyyy + "-" + mM + "-" + dd + hh + ":" + mm + ":" + ss;
            case 19:
                return yyyy + "-" + mM + "-" + dd + " " + hh + ":" + mm + ":" + ss;
        }
        return prev;
    }
}

