package ru.job4j.vvod;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Lenovo2 on 14.03.2019.
 */
public class Chat {


    public static void main(String[] args) throws IOException{
        Chat chat = new Chat();
        chat.createChat();
    }

    /**
     * Метод реализует чат с юзером.
     * Если пользователь вводит слово "Стоп", вывод ответов прекращается, но чат не закрывается.
     * Если пользователь вводит слово "Продолжить", вывод ответов возобновляется.
     * Если пользователь вводит слово "Закончить", чат завершается.
     */
    public void createChat() {
        File filein = createFileWithAnswers("text.txt");
        File fileOut = createFileWithAnswers("log.txt");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")))) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut));
            String userSay = "";
            String compSay = "";
            boolean canSay = true;
            while ((userSay = reader.readLine()) != null) {
                System.out.println("User say:" + userSay);
                canSay = proverka(userSay, canSay);
                if (canSay) {
                    bw.write(userSay + "\n");
                    if (userSay.toLowerCase().equals("закончить")) {
                        compSay = "Chat closed. Bye!";
                        System.out.println("Comp say:" + compSay);
                        bw.write(compSay + "\n");
                        bw.flush();
                        filein.delete();
                        fileOut.delete();
                        break;
                    }
                    System.out.println("Chat say:" + getRandomAnswer(filein));
                    bw.write(compSay + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Метод создает файл
     * Если файл text.txt , то добавляем варианты ответов
     */
    private File createFileWithAnswers(String name) {
        File fileIn = new File(name);
        try {
            if (!fileIn.exists()) {
                System.out.println("Создаем файл "+ name + ": " + fileIn.createNewFile());
                if (name.equals("text.txt")) {
                    List<String> phrases = Arrays.asList("Привет",
                            "Удачи",
                            "Без понятия ",
                            "..... ?",
                            "Нифига себе!");
                    Files.write(Paths.get(fileIn.toString()), phrases, Charset.forName("UTF-8"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileIn;
    }

    /**
     * Метод берет случайный ответ из файла
     */

    private String getRandomAnswer(File file) throws IOException {
        List<String> array = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                array.add(line);
            }
            reader.close();
        }
        Random random = new Random();
        String result = array.get(random.nextInt(array.size()));
        return result;
    }

    /**
     * Метод проверяет наличие ключевых слов во фразе пользователя (пауза).
     */
    private boolean proverka(String userSay, boolean status) {
        boolean result = status;
        if (userSay.toLowerCase().equals("стоп")) {
            result = false;
        } else if (userSay.toLowerCase().equals("продолжить")) {
            result = true;
        }
        return status;
    }
}
