package core.utility;

import android.content.Context;

import com.azmin.skelton.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtils {
    public static String getTimeDifferenceFromDate(Date startDate, Date endDate) {
        int day, hour, minute, seconds;
        String date_time = "";

        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;
        day = (int) elapsedDays;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;
        hour = (int) elapsedHours;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;
        minute = (int) elapsedMinutes;

        long elapsedSeconds = different / secondsInMilli;
        seconds = (int) elapsedSeconds;

        //System.out.printf("%d days, %d hours, %d minutes, %d seconds%n", elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);

        if (day > 0) {
            if (day >= 30) {
                date_time = String.valueOf(day / 30) + " month ago";
            } else if (day >= 7) {
                date_time = String.valueOf((day % 365) / 7) + " week ago";
            } else if (day == 1) {
                date_time = String.valueOf(day) + " day ago";
            } else {
                date_time = String.valueOf(day) + " days ago";
            }

        } else if (hour > 0) {
            if (hour == 1) {
                date_time = String.valueOf(hour) + "hour ago";
            } else {
                date_time = String.valueOf(hour) + "hours ago";
            }

        } else if (minute > 0) {
            if (minute == 1) {
                date_time = String.valueOf(minute) + "minute ago";
            } else {
                date_time = String.valueOf(minute) + "minutes ago";
            }

        } else if (seconds > 0) {
            if (seconds == 1) {
                date_time = String.valueOf(seconds) + "second ago";
            } else {
                date_time = String.valueOf(seconds) + "seconds ago";
            }

        }

        return date_time;

    }


    public static String getTimeDifferenceRemains(Context context, long currentTime, long endTime) {
        int day, hour, minute, seconds;
        String date_time = "";

        long different = endTime - currentTime;

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;
        day = (int) elapsedDays;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;
        hour = (int) elapsedHours;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;
        minute = (int) elapsedMinutes;

        long elapsedSeconds = different / secondsInMilli;
        seconds = (int) elapsedSeconds;

        if (day > 0) {
            if (day >= 30) {
                date_time = String.valueOf((day % 365) / 30) + " month left";
            } else if (day >= 7) {
                date_time = String.valueOf((day % 365) / 7) + " week left";
            } else if (day == 1) {
                date_time = String.valueOf(day) + " " + context.getString(R.string.day_left);
            } else {
                date_time = String.valueOf(day) + " " + context.getString(R.string.days_left);
            }

        } else if (hour > 0) {
            if (hour == 1) {
                date_time = String.valueOf(hour) + " " + context.getString(R.string.hour_left);
            } else {
                date_time = String.valueOf(hour) + " " + context.getString(R.string.hours_left);
            }

        } else if (minute > 0) {
            if (minute == 1) {
                date_time = String.valueOf(minute) + " " + context.getString(R.string.minute_left);
            } else {
                date_time = String.valueOf(minute) + " " + context.getString(R.string.minutes_left);
            }

        } else if (seconds > 0) {
            if (seconds == 1) {
                date_time = String.valueOf(seconds) + " " + context.getString(R.string.second_left);
            } else {
                date_time = String.valueOf(seconds) + " " + context.getString(R.string.seconds_left);
            }

        }

        return date_time;

    }


    public static String updateTimeRemaining(Context context, long currentTime, long endTime) {
        String date_time = "";
        long current_time = currentTime / 1000L;
        long timeDiff = endTime - current_time;
        long days = timeDiff / 86400;
        long hours = timeDiff / 3600;

        if (timeDiff > 0) {
            if (days > 1) {
                date_time = String.valueOf(days) + " " + context.getString(R.string.days_left);

            } else if (days == 1) {
                date_time = String.valueOf(days) + " " + context.getString(R.string.day_left);
            } else if (days == 0) {
                if (hours > 1) {
                    date_time = String.valueOf(hours) + " " + context.getString(R.string.day_left);
                } else if (hours == 1) {
                    date_time = String.valueOf(hours) + " " + context.getString(R.string.day_left);
                } else {
                    date_time = context.getString(R.string.overdue_deadline);
                }

            }
        } else {
            date_time = context.getString(R.string.overdue_deadline);
        }

        return date_time;
    }

    public static String getMonthNameFromDate(String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        String monthName = new SimpleDateFormat("MMM").format(cal.getTime());
        return monthName;
    }

    public static int getMonthNumberFromDate(String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int month = cal.get(Calendar.MONTH);
        return month + 1;
    }

    public static int getDayNumberFromDate(String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int date_number = cal.get(Calendar.DATE);
        return date_number;
    }

    public static String getDayNameFromDate(String date) throws ParseException {

        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date_val = inFormat.parse(date);
        SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
        String day_name = outFormat.format(date_val);

        return day_name;
    }

    public static String getDateTime(String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).parse(date);
        String date_value = new SimpleDateFormat("MMM dd, yyyy hh:mm aaa", Locale.ENGLISH).format(d);
        return date_value;
    }

    public static String getDateTime(String date, String to, String from) throws ParseException {
        try {
            Date d = new SimpleDateFormat(from, Locale.ENGLISH).parse(date);
            return new SimpleDateFormat(to, Locale.ENGLISH).format(d);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getDateTime1(String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).parse(date);
        String date_value = new SimpleDateFormat("dd-MMM-yyyy \n  hh:mm aaa", Locale.ENGLISH).format(d);
        return date_value;
    }

    public static Date getDateTimeD1(String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).parse(date);
        return d;
    }

    public static Date getDate(String date, String formate) throws ParseException {
        Date d = new SimpleDateFormat(formate, Locale.ENGLISH).parse(date);
        return d;
    }


    public static String getDateTimeFolowUp1(String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).parse(date);
        String date_value = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(d);
        return date_value;
    }

    public static String getDateTimeFolowUp2(String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).parse(date);
        String date_value = new SimpleDateFormat("dd-MMM-yyyy,  hh:mm aaa", Locale.ENGLISH).format(d);
        return date_value;
    }

    public static String getDate(String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).parse(date);
        String date_value = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).format(d);
        return date_value;
    }


    public static String getDayTime(String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).parse(date);
        String date_value = new SimpleDateFormat("MMM dd\nhh:mm", Locale.ENGLISH).format(d);
        return date_value;
    }

    public static String getDateForDailyReport(String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date);
        String date_value = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).format(d);
        return date_value;
    }

    public static long getTimeStamp(String date) throws ParseException {
        Date d = new SimpleDateFormat("dd-MMM-yyyy hh:mm a", Locale.ENGLISH).parse(date);
        return d.getTime();
    }

    public static long getTimeStamp2(String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(date);
        return d.getTime();
    }


    public static String getCurrentDate(String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    public static Date getCurrentDateD(String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        String currentDateandTime = sdf.format(new Date());
        return new SimpleDateFormat(format, Locale.ENGLISH).parse(currentDateandTime);
    }

    public static String getDateWithServerTimeStamp(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(date);
    }
}
