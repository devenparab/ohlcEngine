import java.util.*;
import java.sql.*;

public class Test1 {
    public static void main(String[] args) {
        long retryDate = System.currentTimeMillis();
        long unix_seconds = 1511533205001L;
        int sec = 15;

        Timestamp original = new Timestamp(unix_seconds);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(original.getTime());
        cal.add(Calendar.SECOND, sec);
        Timestamp later = new Timestamp(cal.getTime().getTime());

        System.out.println(original);
        System.out.println(later);
    }
}