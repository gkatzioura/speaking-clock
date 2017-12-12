package com.egkatzioura;

public class SpeakingClock {

    private static final String[] ZERO_TO_NINETEEN_EN = {"","one","two","three","four","five","six","seven" ,
            "eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};

    private static final String[] TEN_EN = {"","","twenty","thirty","fourty","fifty"};

    private static final String SPEAK_TEMPLATE = "It's %s %s %s";

    private TextHourValidator textHourValidator;

    /**
     * It is a dependency thus it can be injected through the constructor
     */
    public SpeakingClock(TextHourValidator textHourValidator) {

        this.textHourValidator = textHourValidator;
    }

    public String speak(String inputText) {

        if(!textHourValidator.validFormat(inputText)) {
            throw new IllegalArgumentException("Invalid time format");
        }

        String[] numbers = inputText.split(":");
        Integer hour = Integer.parseInt(numbers[0]);
        Integer minute = Integer.parseInt(numbers[1]);

        if(!textHourValidator.validHour(hour)) {
            throw new IllegalArgumentException("Invalid hour values");
        }

        if(!textHourValidator.validMinute(minute)) {
            throw new IllegalArgumentException("Invalid minute values");
        }

        return String.format(SPEAK_TEMPLATE,hourToText(hour),minuteToText(hour,minute),amPmAbbreviation(hour)).trim();
    }

    private String hourToText(Integer hour) {

        if(hour==0) {
            return ClockConstants.MIDNIGHT;
        } else if(hour==12) {
            return ClockConstants.MIDDAY;
        }

        return numberToText(hour%12);
    }

    private String minuteToText(Integer hour,Integer minute) {

        if(minute==0) {
            if(hour==0||hour==12) {
                return "";
            } else {
                return ClockConstants.O_CLOCK;
            }
        }

        if(minute<10) {
            return String.format(ClockConstants.O_MINUTES,numberToText(minute));
        }

        return numberToText(minute);
    }

    private String amPmAbbreviation(Integer hour) {

        /**
         * Since in this specific case Midday or Midnight is printed am/pm abbreviations are not needed
         */
        if(hour==12||hour==0) return "";

        return hour>12?ClockConstants.PM:ClockConstants.AM;
    }

    private String numberToText(Integer number) {

        if(number>19) {

            Integer tenIndex = number/10;
            Integer numberIndex = number%10;
            return TEN_EN[tenIndex]+" "+ZERO_TO_NINETEEN_EN[numberIndex];
        } else {
            return ZERO_TO_NINETEEN_EN[number];
        }
    }

}
