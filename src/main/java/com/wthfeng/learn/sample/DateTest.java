import java.util.Calendar;

public class DateTest {
    public static void main() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);

    }
}