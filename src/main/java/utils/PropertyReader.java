package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private final Properties properties = new Properties();

    public PropertyReader() throws IOException {
        properties.load(new FileReader("src/test/resources/testdata/"
                + System.getProperty("environment", "qa")
                + ".properties"));
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
