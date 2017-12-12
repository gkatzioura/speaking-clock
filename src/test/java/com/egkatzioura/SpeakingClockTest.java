package com.egkatzioura;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpeakingClockTest {


    private SpeakingClock speakingClock;

    @Before
    public void setUp() {
        this.speakingClock = new SpeakingClock(new TextHourValidator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFormat() {
        speakingClock.speak("56:45:98");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNumbers() {
        speakingClock.speak("31:98");
    }

    @Test
    public void testMidnight() {
        String hourText = speakingClock.speak("00:00");
        Assert.assertEquals("It's Midnight",hourText.trim());
    }

    @Test
    public void testMidday() {
        String hourText = speakingClock.speak("12:00");
        Assert.assertEquals("It's Midday",hourText.trim());
    }

    @Test
    public void testMiddayWithMinutes() {
        String hourText = speakingClock.speak("12:18");
        Assert.assertEquals("It's Midday eighteen",hourText.trim());
    }

    @Test
    public void testMidnightWithMinutes() {
        String hourText = speakingClock.speak("00:05");
        Assert.assertEquals("It's Midnight o-five",hourText.trim());
    }

    @Test
    public void testAmPm() {
        String nineAm = speakingClock.speak("09:31");
        String ninePm = speakingClock.speak("21:31");
        Assert.assertNotEquals(nineAm,ninePm);
    }

    @Test
    public void testOclock() {
        String hourText = speakingClock.speak("01:00");
        Assert.assertEquals("It's one o'clock am",hourText.trim());
    }

    @Test
    public void testDecimalclock() {
        String hourText = speakingClock.speak("23:56");
        Assert.assertEquals("It's eleven fifty six pm",hourText.trim());
    }

    @Test
    public void testONine() {
        String hourText = speakingClock.speak("23:09");
        Assert.assertEquals("It's eleven o-nine pm",hourText.trim());
    }

    @Test
    public void testNormal() {

        String hour = speakingClock.speak("18:34");
        Assert.assertEquals("It's six thirty four pm",hour);
    }

}