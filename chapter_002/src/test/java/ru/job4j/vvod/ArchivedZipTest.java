package ru.job4j.vvod;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by Lenovo2 on 11.03.2019.
 */
public class ArchivedZipTest {
    /**
     * Temp directory.
     */
    private final String path = System.getProperty("java.io.tmpdir");
    /**
     * File separator.
     */
    private final String fs = System.getProperty("file.separator");
    /**
     * Source directory.
     */
    private final String source = path + fs + "ArchiveTest";
    /**
     * File archive.
     */
    private final String destination = path + fs + "archive.zip";


    @Before
    public void init() throws IOException {
        createTestFilesStructure();
        String[] args = {"-d", source, "-e", "java", "xml", "-o", destination};
        ArchivedZip.main(args);
    }


    public void createTestFilesStructure() throws IOException {
        String[] sects = {"pdf", "txt", "java", "xml"};
        String serf = fs + "folder";
        String curPath = source;

        for (int f = 0; f < 3; f++) {
            if (f != 0) {
                curPath = curPath + serf + f;
            }
            new File(curPath).mkdirs();
            for (String str : sects) {
                new File(curPath + fs + "file." + str).createNewFile();
            }
        }
    }

    /**
     * Test archive list of file.
     * @throws IOException -
     */
    @Test
    public void shouldReturnTheSameListOfFiles() throws IOException {
        List<File> result = new ArrayList<>();
        ArchivedZip archive = new ArchivedZip();
        archive.zipArchive();
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(destination));
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            result.add(new File(zipEntry.getName()));
        }
        Search search = new Search();
        List<String> extensions = new ArrayList<>();
        extensions.add("java");
        extensions.add("xml");
        List<File> sourceFiles = search.files(source, extensions);
        List<File> expected = new ArrayList<>();
        for (File sFile : sourceFiles) {
            expected.add(new File(Paths.get(source).relativize(Paths.get(sFile.getPath())).toString()));
        }
        assertThat(result, is(expected));
    }
}