package org.abratuhi.i18n;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class I18nPropertiesValidatorTest {

    private I18nPropertiesValidator validator = new I18nPropertiesValidator();

    @Test
    public void testNegative() throws IOException {
        boolean actual = validator.isConsistent("src/test/resources/negative/negative.*\\.properties");
        assertFalse("", actual);
    }

    @Test
    public void testPositive() throws IOException {
        boolean actual = validator.isConsistent("src/test/resources/positive/positive.*\\.properties");
        assertTrue("", actual);
    }
}
