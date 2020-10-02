import com.ohlc.trading.ohlcEngine.model.Trade;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        //Unix seconds
        long unix_seconds = 1538409748332169137L;
        printDate(unix_seconds);
        Trade t1 = new Trade();
        t1.settS2(1511533205001L);
        t1.setSym("A");
       // System.out.println(t1.toString());

        unix_seconds = 1511533206001L;
        printDate(unix_seconds);
        Trade t2 = new Trade();
        t2.settS2(1511533206001L);
        t2.setSym("B");
       // System.out.println(t2.toString());

        unix_seconds = 1511533207001L;
        printDate(unix_seconds);
        Trade t3 = new Trade();
        t3.settS2(1511533207001L);
        t3.setSym("C");

       /* List<Trade> trades = new ArrayList<>();
        trades.add(t3);
        trades.add(t2);
        trades.add(t1);
        trades.stream().forEach(System.out::println);

        Collections.sort(trades, Comparator.comparing(Trade::gettS2));
        trades.stream().forEach(System.out::println);*/
    }

    private static void printDate(long unix_seconds) {
        //convert seconds to milliseconds
        Date date = new Date(unix_seconds*1000L);
        // format of the date
        SimpleDateFormat jdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
        //SimpleDateFormat jdf = new SimpleDateFormat("HH:mm:ss");
        jdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String java_date = jdf.format(date);
        Timestamp original = new Timestamp(unix_seconds);
        //System.out.println("### Timestamp :: "+java_date+" ###");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));

        /*Instant instant = Instant.ofEpochSecond( unix_seconds );
        Date dateIns = Date.from( instant );*/
        System.out.println("### Raw :: "+unix_seconds+
                " SimpleDateFormat :: "+java_date+" ###"+
                "### Timestamp :: "+original+
                " DateFormat :: "+format.format(date)+" dateIns ::  ###");
    }
}
