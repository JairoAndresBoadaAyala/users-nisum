package co.com.users.config.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionUtil {


    public static Boolean isValid(String phrase, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phrase);
        return matcher.matches();
    }
}
