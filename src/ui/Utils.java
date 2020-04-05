package ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {return false;}
        try { int i = Integer.parseInt(strNum); } catch (NumberFormatException nfe) { return false; }
        return true;
    }

    public static boolean isValidDate(String strDate) {
        Date retDate;
        String DATE_PATTERN = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        sdf.setLenient(false);
        try {
            retDate = sdf.parse(strDate);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    public static boolean isValidEmail(String strEmail) {
        String rgx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern patt = Pattern.compile(rgx);
        Matcher matcher = patt.matcher(strEmail);
        return matcher.matches();
    }

    public static boolean hasNoEmptyStrings(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("")) {
                return false;
            }
        }
        return true;
    }

    public static String concatLLString(LinkedList<String> lls) {
        StringBuilder sb = new StringBuilder("");
        while (lls.size() != 0) {
            String temp = lls.pop();
            sb.append(temp);
            sb.append("\n");
            sb.append("------------------\n");
        }
        return sb.toString();
    }

    public static String getCountText(LinkedList<String> lls) {
        StringBuilder sb = new StringBuilder("Returned ");
        String count = (Integer.toString(lls.size())) + " reviews";
        sb.append(count);
        return sb.toString();
    }
}
