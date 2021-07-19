package utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class PropertyReader {
    private final Properties properties = new Properties();

    public PropertyReader() {
        try (Reader reader = new FileReader("src/test/resources/testdata/"
                + System.getProperty("environment", "qa")
                + ".properties"))
        {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String systemProperty = System.getProperty(key);
        if (systemProperty == null || systemProperty.equals("")) {
            return properties.getProperty(key);
        }
        return systemProperty;
    }
}
