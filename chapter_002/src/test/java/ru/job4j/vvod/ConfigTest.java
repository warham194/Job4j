package ru.job4j.vvod;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by Lenovo2 on 29.03.2019.
 */
public class ConfigTest {

    @Test
    public void getKeyAndValueFromPropertiesFile() {
        Config config = new Config("config.properties");
        String name = "Max";
        String password = "qwert";
        config.put(name, password);
        assertThat(password, is(config.get(name)));
    }

    @Test
    public void getValueThanPutNewValue() {
        Config config = new Config("config.properties");
        String name = config.get("Max");
        String password = "newPassword";
        config.put("Max", password);
        assertThat(password, is(config.get("Max")));
    }

}