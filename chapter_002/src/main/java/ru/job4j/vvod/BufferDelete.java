package ru.job4j.vvod;

import java.io.*;

/**
 * Created by Lenovo2 on 10.01.2019.
 */
public class BufferDelete {

    void dropAbuses(InputStream in, OutputStream out, String[] abuse) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in))){
            String cenzor;
            while ((cenzor = bufferedReader.readLine()) != null) {
                for (String s : abuse) {
                    if (cenzor.contains(s)) {
                        cenzor = cenzor.replaceAll(s, "***");
                    }
                }
                out.write(cenzor.getBytes());
                System.out.println(out.toString());
            }
        }
    }
}
