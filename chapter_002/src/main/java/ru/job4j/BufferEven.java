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
           while (br.ready()) {
               int text = br.read();
               if (text % 2 == 0) {
                   System.out.println(text);
                   result = true;
               }
           }
       }
       return result;
   }
}
