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
     * Create chat
     * If user write "Стоп" ? then method wait, when user write "Продолжить".
     * If user write "Закончить" , break.
     */
    public void createChat() {
        File fileIn = createFileWithAnswers("text.txt");
        File fileOut = createFileWithAnswers("log.txt");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")))) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut));
            String userSay = "";
            String compSay = "";
            boolean canSay = true;
            String[] answers = getAnswers(fileIn);
            while ((userSay = reader.readLine()) != null) {

                if (userSay.toLowerCase().equals("закончить")) {
                    compSay = "Chat closed. Bye!";
                    System.out.println("Comp say:" + compSay);
                    bw.write(compSay + "\n");
                    bw.flush();
                    fileIn.delete();
                    fileOut.delete();
                    break;
                }
                canSay = proverka(userSay, canSay);
                if (canSay) {
                    bw.write(userSay + "\n");
                    System.out.println("User say:" + userSay);
                    int rnd = new Random().nextInt(answers.length);
                    System.out.println("Chat say:" + answers[rnd]);
                    bw.write(compSay + "\n");
                } else System.out.println("Ввод текста заблокирован");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Create file
     * If file name is text.txt , then add answers
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
     * return String[] with answers from file
     */

    private String[] getAnswers(File file) throws IOException {
        List<String> array = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                array.add(line);
            }
            reader.close();
        }
        String[] result = (String[]) array.toArray(new String[array.size()]);
        return result;
    }

    /**
     *
     */
    private boolean proverka(String userSay, boolean status) {
        boolean result = status;
        if (userSay.toLowerCase().equals("стоп")) {
            result = false;
            System.out.println("Заблокировано");
        } else if (userSay.toLowerCase().equals("продолжить")) {
            result = true;
        }
        return result;
    }
}
