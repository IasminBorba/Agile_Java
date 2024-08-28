package util;

import junit.framework.TestCase;
import java.io.IOException;
import java.text.*;
import java.util.Locale;

public class BundleTest extends TestCase {
    private static final String KEY = "someKey";
    private static final String TEST_APPEND = "Test";
    private String filename;
    private String existingBundleName;

    private void prepare() {
        TestUtil.delete(filename);
        existingBundleName = Bundle.getName();
        Bundle.setName(existingBundleName + TEST_APPEND);
    }

    protected void tearDown() {
        Bundle.setName(existingBundleName);
        TestUtil.delete(filename);
    }

    public void testMessage() throws IOException {
        filename = getFilename();
        prepare();
        final String value = "open the door";
        writeBundle(value);
        assertEquals(value, Bundle.get(KEY));
    }

    public void testLocalizedMessage() throws IOException {
        final String language = "es";
        final String country = "MX";
        filename = getFilename(language, country);
        prepare();
        Locale mexican = new Locale(language, country);
        Locale current = Locale.getDefault();
        try {
            Locale.setDefault(mexican);
            final String value = "abre la puerta";
            writeBundle(value);assertEquals(value, Bundle.get(KEY));
        }
        finally {
            Locale.setDefault(current);
        }
    }

    private void writeBundle(String value) throws IOException {
        String record = String.format("%s=%s", KEY, value);
        LineWriter.write(getFilename(), new String[]{record});
    }

    private String getFilename(String language, String country) {
        StringBuilder builder = new StringBuilder();
        builder.append("src/sis/resources/util/");
        builder.append(Bundle.DEFAULT_BASE_NAME);

        if (!language.isEmpty())
            builder.append("_").append(language);
        if (!country.isEmpty())
            builder.append("_").append(country);

        builder.append(".properties");
        return builder.toString();
    }

    private String getFilename() {
        return getFilename("", "");
    }

//    public void testAvailableLocales() {
//        for (Locale locale: Locale.getAvailableLocales())
//            System.out.printf("%s %s: use '_%s%s'\n",
//                    locale.getDisplayLanguage(),
//                    locale.getDisplayCountry(),
//                    locale.getLanguage(),
//                    (locale.getCountry().isEmpty() ? "" : "_" + locale.getCountry()));
//    }

    public void testMessageFormat() {
        String message = "You have {0} dependents, {1}. Is this correct?";
        MessageFormat formatter = new MessageFormat(message);
        assertEquals("You have 5 dependents, Señor Wences. Is this correct?",
                formatter.format(message, 5, "Señor Wences"));
    }

    public void testChoiceFormat() {
        double[] dependentLimits = {0, 1, 2 };
        String[] dependentFormats = {"no dependents", "one dependent", "{0} dependents" };
        ChoiceFormat formatter = new ChoiceFormat(dependentLimits, dependentFormats);

        String message = "You have {0}, {1}. Is this correct?";
        MessageFormat messageFormatter = new MessageFormat(message);

        Format[] formats = {formatter, null };
        messageFormatter.setFormats(formats);
        assertEquals("You have one dependent, Señor Wences. Is this correct?",
                messageFormatter.format(new Object[] {1, "Señor Wences" }));
        assertEquals("You have 10 dependents, Señor Wences. Is this correct?",
                messageFormatter.format(new Object[] {10, "Señor Wences" }));
    }
}
