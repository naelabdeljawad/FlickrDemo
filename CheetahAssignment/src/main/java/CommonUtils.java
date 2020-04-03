import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class CommonUtils {

    private static CommonUtils commonUtils;

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized CommonUtils getInstance() {
        if (commonUtils == null)
            return new CommonUtils();

        return commonUtils;
    }

    /**
     * getCurrentTimeAndDate
     *
     * @return
     */
    public String getCurrentTimeDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    /**
     * getRandomNumber
     *
     * @param min
     * @param max
     * @return
     */
    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        int randomNum = random.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    /**
     * getRandomString
     *
     * @param len
     * @return
     */
    public String getRandomString(int len) {
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));

        return sb.toString();
    }

}
