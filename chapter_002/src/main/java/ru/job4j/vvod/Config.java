package ru.job4j.vvod;

import java.io.*;
import java.util.Properties;

/**
 * Created by Lenovo2 on 29.03.2019.
 */
public class Config {
   private Properties property = new Properties();

    public Config(String propertyFileName) {
        try {
            FileInputStream fis = new FileInputStream(propertyFileName);
            property.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return property.getProperty(key);
    }

    public void put(String key, String value) {
        try {
            FileOutputStream out = new FileOutputStream("src/main/resources/config.properties");
            property.setProperty(key, value);
            property.store(out,  null);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
