package com.egkatzioura;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextHourValidator {

    private static final Pattern pattern24Hour = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");

    public boolean validFormat(String inputText) {

        Matcher matcher = pattern24Hour.matcher(inputText);
        return matcher.matches();
    }

    public boolean validHour(Integer hour) {

        return hour>=0&&hour<=23;
    }

    public boolean validMinute(Integer minute) {
        return minute>=0&&minute<=59;
    }

}
