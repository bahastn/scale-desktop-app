

package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigurationClass {
    public ConfigurationClass() {
    }

    public static Properties configProperties() throws IOException {
        Properties properties = new Properties();
        OutputStream outputStream = new FileOutputStream("config.properties");
        properties.setProperty("driver", "org.h2.Driver");
        properties.setProperty("dbpath", "jdbc:h2:~/data/erbilfeed");
        properties.setProperty("excelpath", "C:/data");
        properties.store(outputStream, null);
        InputStream input = new FileInputStream("config.properties");
        properties.load(input);
        return properties;
    }
}


