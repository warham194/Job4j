package ru.job4j.vvod;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Lenovo2 on 23.03.2019.
 */
public class ClientTest {
    private final static String LN = System.lineSeparator();
    @Test
    public void clientTestGiveExitFromConsoleShouldExitAndPrintMessage() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(String.format("text%s%s", LN, LN).getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        System.setIn(new BufferedInputStream(new ByteArrayInputStream(String.format("exit%s", LN).getBytes())));
        ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOut));
        new Client(socket).start();
        assertThat(out.toString(), is(String.format("exit%s", System.lineSeparator())));
        assertThat(consoleOut.toString(), is(String.format("text%s", LN)));
    }
}