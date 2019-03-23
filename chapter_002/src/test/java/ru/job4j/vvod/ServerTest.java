package ru.job4j.vvod;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static java.lang.System.out;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by Lenovo2 on 23.03.2019.
 */
public class ServerTest {
    private final static String LN = System.lineSeparator();
    @Test
    public void testServer() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(
                Joiner.on(LN).join(
                        "Hello",
                        "asdadad",
                        "exit"
                ).getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        new Server(socket).start();
        assertThat(out.toString(), is(
                Joiner.on(LN).join(
                        "Hello, dear friend, I'm a oracle.",
                        "?????",
                        "good luck",
                        ""
                )
        ));
    }

}