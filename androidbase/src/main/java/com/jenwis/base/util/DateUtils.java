package com.jenwis.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
    private static SimpleDateFormat sDailySimpleFormat = new SimpleDateFormat("MM.dd");
    private static SimpleDateFormat sMonthlySimpleFormat = new SimpleDateFormat("yyyy.MM");
    private static SimpleDateFormat sSimpleFormat1 = new SimpleDateFormat("MM.dd");
    private static SimpleDateFormat sSimpleFormat2 = new SimpleDateFormat("MM月dd日");
    private static SimpleDateFormat sSimpleFormat3 = new SimpleDateFormat("yyyy年MM月dd日");
    private static SimpleDateFormat sTimeSimpleFormat = new SimpleDateFormat("HH:mm");
    private static SimpleDateFormat sBirthdaySimpleFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatDate1(long date) {
        return sSimpleFormat1.format(date);
    }

    public static String formatDate2(long date) {
        if (isToday(date)) {
            return "今天";
        } else if (isYesterday(date)) {
            return "昨天";
        }

        return sSimpleFormat2.format(date);
    }

    public static String formatDate3(long date) {
        if (isToday(date)) {
            return "今天";
        } else if (isYesterday(date)) {
            return "昨天";
        }

        return sSimpleFormat3.format(date);
    }

    public static long getDayBeginTimeInMillis(long timeInMillis) {
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.setTimeInMillis(timeInMillis);
        calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, Calendar.getInstance().getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, Calendar.getInstance().getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMinimum(Calendar.MILLISECOND));
        return calendar.getTimeInMillis();
    }

    public static Calendar getStartOfDay(Calendar calendar) {
        Calendar start = (Calendar) calendar.clone();
        start.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        start.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        start.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        start.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        return start;
    }

    public static Calendar getEndOfDay(Calendar calendar) {
        Calendar end = (Calendar) calendar.clone();
        end.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        end.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        end.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        end.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        return end;
    }

    public static String getDailyFormatString(long calendar) {
        return sDailySimpleFormat.format(calendar);
    }

    public static String getDailyFormatString(Calendar calendar) {
        return sDailySimpleFormat.format(calendar.getTimeInMillis());
    }

    public static String getBirthdayFormatString(long calendar) {
        return sBirthdaySimpleFormat.format(calendar);
    }

    public static Date getBirthdayParseString(String calendar) {
        try {
            return sBirthdaySimpleFormat.parse(calendar);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static boolean isToday(Calendar calendar) {
        return (calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) &&
                (calendar.get(Calendar.DAY_OF_YEAR) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
    }

    public static boolean isToday(long timeInMillis) {
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.setTimeInMillis(timeInMillis);
        return (calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) &&
                (calendar.get(Calendar.DAY_OF_YEAR) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
    }

    public static boolean isYesterday(long timeInMillis) {
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.setTimeInMillis(timeInMillis);
        return (calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) &&
                (calendar.get(Calendar.DAY_OF_YEAR) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR) - 1);
    }

    public static Calendar getMondayOfWeek(Calendar calendar) {
        Calendar monday = (Calendar) calendar.clone();
        int day_of_week = monday.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        monday.add(Calendar.DATE, -day_of_week + 1);
        monday.set(Calendar.HOUR_OF_DAY, monday.getActualMinimum(Calendar.HOUR_OF_DAY));
        monday.set(Calendar.MINUTE, monday.getActualMinimum(Calendar.MINUTE));
        monday.set(Calendar.SECOND, monday.getActualMinimum(Calendar.SECOND));
        monday.set(Calendar.MILLISECOND, monday.getActualMinimum(Calendar.MILLISECOND));
        return monday;
    }

    public static Calendar getSundayOfWeek(Calendar calendar) {
        Calendar sunday = (Calendar) calendar.clone();
        int day_of_week = sunday.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        sunday.add(Calendar.DATE, -day_of_week + 7);
        sunday.set(Calendar.HOUR_OF_DAY, sunday.getActualMaximum(Calendar.HOUR_OF_DAY));
        sunday.set(Calendar.MINUTE, sunday.getActualMaximum(Calendar.MINUTE));
        sunday.set(Calendar.SECOND, sunday.getActualMaximum(Calendar.SECOND));
        sunday.set(Calendar.MILLISECOND, sunday.getActualMaximum(Calendar.MILLISECOND));
        return sunday;
    }

    public static String getWeeklyFormatString(Calendar calendar) {
        Calendar monday = getMondayOfWeek(calendar);
        Calendar sunday = getSundayOfWeek(calendar);
        return sDailySimpleFormat.format(monday.getTimeInMillis()) + " - " + sDailySimpleFormat.format(sunday.getTimeInMillis());
    }

    public static boolean isThisWeek(Calendar calendar) {
        return (calendar.get(Calendar.WEEK_OF_YEAR) == Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)) &&
                (calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR));
    }

    public static Calendar getStartOfMonth(Calendar calendar) {
        Calendar begin = (Calendar) calendar.clone();
        begin.set(Calendar.DAY_OF_MONTH, begin.getActualMinimum(Calendar.DAY_OF_MONTH));
        begin.set(Calendar.HOUR_OF_DAY, begin.getActualMinimum(Calendar.HOUR_OF_DAY));
        begin.set(Calendar.MINUTE, begin.getActualMinimum(Calendar.MINUTE));
        begin.set(Calendar.SECOND, begin.getActualMinimum(Calendar.SECOND));
        begin.set(Calendar.MILLISECOND, begin.getActualMinimum(Calendar.MILLISECOND));
        return begin;
    }

    public static Calendar getEndOfMonth(Calendar calendar) {
        Calendar end = (Calendar) calendar.clone();
        end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
        end.set(Calendar.HOUR_OF_DAY, end.getActualMaximum(Calendar.HOUR_OF_DAY));
        end.set(Calendar.MINUTE, end.getActualMaximum(Calendar.MINUTE));
        end.set(Calendar.SECOND, end.getActualMaximum(Calendar.SECOND));
        end.set(Calendar.MILLISECOND, end.getActualMaximum(Calendar.MILLISECOND));
        return end;
    }

    public static String getMonthlyFormatString(Calendar calendar) {
        return sMonthlySimpleFormat.format(calendar.getTimeInMillis());
    }

    public static boolean isThisMonth(Calendar calendar) {
        return (calendar.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)) &&
                (calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR));
    }

    public static long getAccuracyToHour(long collectTime) {
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.setTimeInMillis(collectTime);
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        return calendar.getTimeInMillis();
    }

    public static long getAccuracyToDay(long collectTime) {
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.setTimeInMillis(collectTime);
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        return calendar.getTimeInMillis();
    }

    public static String getTimeFormatString(long time) {
        return sTimeSimpleFormat.format(time);
    }

    public static long getSomeMinuteAgo(Calendar calendar, int minute) {
        Calendar end = (Calendar) calendar.clone();
        end.add(Calendar.MINUTE, -1 * minute);
        return end.getTime().getTime();
    }

    public static Date getSomeMinuteAfter(Calendar calendar, int minute) {
        Calendar end = (Calendar) calendar.clone();
        end.add(Calendar.MINUTE, minute);
        return end.getTime();
    }

    public static List<Date> getBeforeAndAfterHalfHour(Date date) {
        List<Date> dateList = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -1 * 30);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.MINUTE, 60);
        Date endDate = calendar.getTime();
        dateList.add(startDate);
        dateList.add(endDate);
        return dateList;
    }

    //yyyy-MM-dd HH:mm:ss
    public static String getYMDHMSFormatString(long calendar) {
        return DateFormatFactory.getFormatYMDHMS().format(calendar);
    }


    /**
     * 计算出两个日期之间相差的天数
     * 建议date1 大于 date2 这样计算的值为正数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return date1 - date2
     */
    public static int dateInterval(long date1, long date2) {
        if (date2 > date1) {
            date2 = date2 + date1;
            date1 = date2 - date1;
            date2 = date2 - date1;
        }

        // Canlendar 该类是一个抽象类
        // 提供了丰富的日历字段
        // 本程序中使用到了
        // Calendar.YEAR    日期中的年份
        // Calendar.DAY_OF_YEAR     当前年中的天数
        // getActualMaximum(Calendar.DAY_OF_YEAR) 返回今年是 365 天还是366天
        Calendar calendar1 = Calendar.getInstance(); // 获得一个日历
        calendar1.setTimeInMillis(date1); // 用给定的 long 值设置此 Calendar 的当前时间值。

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        // 先判断是否同年
        int y1 = calendar1.get(Calendar.YEAR);
        int y2 = calendar2.get(Calendar.YEAR);

        int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int maxDays = 0;
        int day = 0;
        if (y1 - y2 > 0) {
            day = numerical(maxDays, d1, d2, y1, y2, calendar2);
        } else {
            day = d1 - d2;
        }
        return day;
    }

    /**
     * 日期间隔计算
     * 计算公式(示例):
     * 20121201- 20121212
     * 取出20121201这一年过了多少天 d1 = 天数  取出20121212这一年过了多少天 d2 = 天数
     * 如果2012年这一年有366天就要让间隔的天数+1，因为2月份有29日。
     *
     * @param maxDays  用于记录一年中有365天还是366天
     * @param d1       表示在这年中过了多少天
     * @param d2       表示在这年中过了多少天
     * @param y1       当前为2012年
     * @param y2       当前为2012年
     * @param calendar 根据日历对象来获取一年中有多少天
     * @return 计算后日期间隔的天数
     */
    public static int numerical(int maxDays, int d1, int d2, int y1, int y2, Calendar calendar) {
        int day = d1 - d2;
        int betweenYears = y1 - y2;
        List<Integer> d366 = new ArrayList<Integer>();

        if (calendar.getActualMaximum(Calendar.DAY_OF_YEAR) == 366) {
//            System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
            day += 1;
        }

        for (int i = 0; i < betweenYears; i++) {
            // 当年 + 1 设置下一年中有多少天
            calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR)) + 1);
            maxDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
            // 第一个 366 天不用 + 1 将所有366记录，先不进行加入然后再少加一个
            if (maxDays != 366) {
                day += maxDays;
            } else {
                d366.add(maxDays);
            }
            // 如果最后一个 maxDays 等于366 day - 1
            if (i == betweenYears - 1 && betweenYears > 1 && maxDays == 366) {
                day -= 1;
            }
        }
        for (int i = 0; i < d366.size(); i++) {
            // 一个或一个以上的366天
            if (d366.size() >= 1) {
                day += d366.get(i);
            }
        }
        return day;
    }
}
