package com.egkatzioura;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TextHourValidatorTest {

    private TextHourValidator textHourValidator;

    @Before
    public void setUp() {
        textHourValidator = new TextHourValidator();
    }

    @Test
    public void invalidText() {

        boolean valid = textHourValidator.validFormat("80:80:80");
        Assert.assertFalse(valid);
    }

    @Test
    public void validText() {

        boolean valid = textHourValidator.validFormat("23:59");
        Assert.assertTrue(valid);
    }

    @Test
    public void validHour() {

        boolean valid = textHourValidator.validHour(23);
        Assert.assertTrue(valid);
    }

    @Test
    public void inValidHour() {

        boolean valid = textHourValidator.validHour(60);
        Assert.assertFalse(valid);
    }

    @Test
    public void validMinute() {

        boolean valid = textHourValidator.validMinute(59);
        Assert.assertTrue(valid);
    }

    @Test
    public void inValidMinute() {

        boolean valid = textHourValidator.validMinute(100);
        Assert.assertFalse(valid);
    }

}
