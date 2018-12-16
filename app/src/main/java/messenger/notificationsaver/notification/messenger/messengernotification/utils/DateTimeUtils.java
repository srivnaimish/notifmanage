package messenger.notificationsaver.notification.messenger.messengernotification.utils;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import messenger.notificationsaver.notification.messenger.messengernotification.R;

public class DateTimeUtils {

    public static final String DATE_FORMAT_DATE_AND_TIME = "dd MMM yyyy (EEEE) • hh:mm a";
    public static final String DATE_FORMAT_DATE_ONLY = "EEEE • dd MMM yyyy";

    public static DateFormat getDateTimeFormat(String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        formatter.setTimeZone(TimeZone.getTimeZone("IST"));
        return formatter;
    }

    public static DateFormat getDateTimeFormatNew(String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+0530"));
        return formatter;
    }

    public static boolean isSameDay(long date1, long date2) {

        return isSameDay(new Date(date1), new Date(date2));
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    public static String getCurrentDateString() {

        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy");
        return sdf.format(date);
    }

    public static String getDateString(long dateInMillis) {
        return getDateString(dateInMillis, true);
    }

    public static String getDateString(long dateInMillis, boolean includeDay) {

        SimpleDateFormat sdf = new SimpleDateFormat((includeDay ? "EEE, " : "") + "MMM d, yyyy");
        return sdf.format(dateInMillis);
    }

    public static String getMaterialUpdatedTimeString(Context context, long updatedAtTimestamp) {
        long millisSinceUpdate = System.currentTimeMillis() - updatedAtTimestamp;

        if (millisSinceUpdate < Constants.ONE_MINUTE_MILLIS) {
            return context.getString(R.string.just_now);
        } else if (millisSinceUpdate < Constants.ONE_HOUR_MILLIS) {
            int minutesSinceUpdate = (int) Math.floor(1.0d * millisSinceUpdate / Constants.ONE_MINUTE_MILLIS);
            return context.getString(R.string.minutes_ago, Integer.toString(minutesSinceUpdate));
        } else if (millisSinceUpdate < Constants.ONE_DAY_MILLS) {
            int hoursSinceUpdate = (int) Math.floor(1.0d * millisSinceUpdate / Constants.ONE_HOUR_MILLIS);
            return context.getString(R.string.hours_ago, Integer.toString(hoursSinceUpdate));
        }

        return getDateString(updatedAtTimestamp, false);
    }

    public static String getCurrentTimeString() {

        long timeInMillis = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        return sdf.format(timeInMillis);
    }

    public static String getTimeString(long timeInMillis) {
        return String.format(Locale.getDefault(), "%02d:%02d",
                (timeInMillis / 1000) / 60,
                (timeInMillis / 1000) % 60);
    }

    public static int compareDate(long timestamp1, long timestamp2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTimeInMillis(timestamp1);
        cal2.setTimeInMillis(timestamp2);

        if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)) {
            return 0;
        }

        return Long.compare(timestamp1, timestamp2);
    }

    public static int getCurrentMillis() {
        return (int) (System.currentTimeMillis() % 1000000);
    }

    public static long getYearMillisFromCurrentDate(int yearCount) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, (0 - yearCount));
        cal.add(Calendar.DATE, -1);
        return cal.getTimeInMillis();
    }

    public static String getFormattedQuizTimerText(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int sec = seconds % 60;
        StringBuilder text = new StringBuilder();
        if (hours > 0) {
            text.append(hours);
            text.append(":");
        }
        if (minutes >= 0) {
            if (minutes < 10) {
                text.append("0");
            }
            text.append(minutes);
            text.append(":");
        }
        if (sec >= 0) {
            if (sec < 10)
                text.append("0"); // to show 5:05 instead of 5:5
            text.append(sec);
        }
        return text.toString();
    }

    public static String getTimeStampAsDate(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date netDate = (new Date(timeStamp));
        return sdf.format(netDate);
    }

    public static String getTimeStampAsTime(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a dd/MM/yyyy", Locale.getDefault());
        Date netDate = (new Date(timeStamp));
        return sdf.format(netDate);
    }
}
