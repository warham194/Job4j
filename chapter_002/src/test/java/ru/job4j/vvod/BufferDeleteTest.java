package ru.job4j.vvod;

import org.junit.Test;

import java.io.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Lenovo2 on 10.01.2019.
 */
public class BufferDeleteTest {
    @Test
    public void whenRemoveBannedWords() throws Exception, IOException {
        String t = "Как же хочется забить на все и спать";
        String[] ban = {"забить", "спать"};
        String result = "Как же хочется *** на все и ***";
        try(InputStream in = new ByteArrayInputStream(t.getBytes())) {
            OutputStream out = new ByteArrayOutputStream();
            BufferDelete del = new BufferDelete();
            del.dropAbuses(in, out, ban);
            assertThat(result.equals(out.toString()), is(true));
        }
    }
}