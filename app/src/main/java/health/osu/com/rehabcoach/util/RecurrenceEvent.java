package health.osu.com.rehabcoach.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by brainfreak on 10/23/14.
 */
public class RecurrenceEvent {
    public static HashMap<String, String> getDates(String rawEvent) {

        int position;
        String startDate, endDate;

        if(rawEvent.contains(";")) {
            position = rawEvent.indexOf(";");
            position += 2;

            String rawEndDate = rawEvent.substring(position);
            if(rawEndDate.contains("time")) {

            } else if(rawEndDate.contains("until")) {

                endDate = getEndDate(rawEndDate);
            }
        }

        startDate = getStartDate();

        return null;
    }

    private static void matchStartDate(String rawEvent) {

    }

    public static String getStartDate() {
        Calendar calendar = Calendar.getInstance();
        return formatDate(calendar.get(Calendar.DATE), "MM/dd/yyyy");
    }

    private static String formatDate(int date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        return sdf.format(new Date(date));
    }
    private static String getFrequency(String rawDate) {
        return null;
    }

    private static String getCount(String rawDate) {
        return null;
    }

    private static String getEndDate(String rawDate) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile("[0-9]+/[0-9]+.*");
        matcher = pattern.matcher(rawDate);
        return matcher.group(0);
    }

    private static String getUpdate(String rawDate) {
        return null;
    }
}
