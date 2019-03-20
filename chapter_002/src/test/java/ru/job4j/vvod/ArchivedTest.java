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
import static org.junit.Assert.assertThat;

/**
 * Class ArchiveTest.
 *
 * @author Aleksey Slivko
 * @version $1.0$
 * @since 03.03.2019
 */
public class ArchivedTest {
    @Test
    public void whenAddArgumentsThenTrue() {
        Args args = new Args(new String[]{
                "-d", "Directory",
                "-o", "output.txt",
                "-e", "exclude.rtf"
        });
        assertThat(args.directory(), is("Directory"));
        assertThat(args.output(), is("output.txt"));
        assertThat(args.exclude(), is("exclude.rtf"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAddUnsupportedArgumentsThenEx() {
        Args args = new Args(new String[]{
                "-d", "Directory",
                "-f", "output.txt",
                "-e", "exclude.rtf"
        });
    }
}