package ru.job4j.vvod;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Lenovo2 on 08.03.2019.
 */
public class SearchTest {

    private static final String SEP = System.getProperty("file.separator");
    private final String path = System.getProperty("java.io.tmpdir") + SEP + "Search";

    /**
     * Список расширений
     */
    private final List<String> ex = new ArrayList<>();

    @Before
    public void setUp() {
        new File(path).mkdir();
        this.ex.add(".zip");
        this.ex.add(".pdf");
        this.ex.add(".txt");
    }

    @Test
    public void whenAddFile() throws IOException {
        List<File> expected = new ArrayList<>();
        Search search = new Search();
        File file;
        for (int i = 0; i < 8; i++) {
            String filePath = path + SEP + i;
            new File(filePath).mkdir();
            for (String s : ex) {
                File curFile = new File(filePath + SEP + i + s);
                expected.add(curFile);
                curFile.createNewFile();
            }
        }
        List<File> result = search.files(path, ex);
        assertThat(result.size(), is(expected.size()));
    }

}