package ru.job4j;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by Lenovo2 on 09.01.2019.
 */
public class BufferEvenTest {


    @Test
    public void whenInEvenThenResultTrue() throws Exception, IOException {
        BufferEven bufferEven = new BufferEven();
        try (InputStream stream = new ByteArrayInputStream(new byte[]{1, 2, 3, 4})) {
            assertThat(bufferEven.isNumber(stream), is(true));
        }
    }
}