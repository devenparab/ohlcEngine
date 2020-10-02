import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class Java8ConvertEpoch {

    public static void main(String[] args) {

        long epoch = 1538409748332315018L;//Instant.now().toEpochMilli();
        System.out.println(epoch);

        LocalDate ld = Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(ld);

        LocalDateTime ldt = Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(ldt);

        System.out.println(Instant.ofEpochMilli(epoch).until(Instant.now(), ChronoUnit.DAYS));

        String timestamp =  "1538409748332315018";
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM 'at' hh:mm a z" );
        String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
        System.out.println(dateString);


        long seconds = epoch / 1_000_000_000;
        long nanos = epoch % 1_000_000_000;
        Instant instant = Instant.ofEpochSecond(seconds, nanos);
        System.out.println(instant);

        //timeStamp in zone - "Asia/Kolkata"
        /*ZonedDateTime timestampAtGMTPlus1= instant.atZone(ZoneId.of("Asia/Kolkata"));
        System.out.println("In 'Asia/Kolkata' Time Zone:"+ timestampAtGMTPlus1);*/

        Date myDate = Date.from(instant);
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String formattedDate = format.format(myDate);
        System.out.println(formattedDate);

        DateTimeFormatter ldf = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
        //LocalDateTime dateTime = LocalDateTime.parse(formattedDate, formatte);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Kolkata"));
        System.out.println(dateTime);
        String formatDateTime = dateTime.format(ldf);
        System.out.println("formatted: "+formatDateTime);
        System.out.println("Adding 15 sec  : "+dateTime.plusSeconds(15));
    }

}