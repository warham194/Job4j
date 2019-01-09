package ru.job4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Lenovo2 on 09.01.2019.
 */
public class BufferEven {

   public boolean isNumber(InputStream in) throws IOException {
        boolean result = false;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String text = br.readLine();
            int even = Integer.parseInt(text);
            if (even % 2 == 0) {
                result = true;
            }
        }
        return result;
   }
}
