import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Test {
    public static void main(String[] args) {
        //Unix seconds
        long unix_seconds = 1538409748332169137L;
        printDate(unix_seconds);

        unix_seconds = 1538409748332223252L;
        printDate(unix_seconds);

        unix_seconds = 1538409748332270624L;
        printDate(unix_seconds);
    }

    private static void printDate(long unix_seconds) {
        //convert seconds to milliseconds
        Date date = new Date(unix_seconds *1000L);
        // format of the date
        //SimpleDateFormat jdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
        SimpleDateFormat jdf = new SimpleDateFormat("HH:mm:ss");
        jdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String java_date = jdf.format(date);
        System.out.println("\n"+java_date+"\n");
    }
}
