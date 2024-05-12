package ru.itis.aisd304.avltree;

import java.io.*;
public class TestGenerator {
    private static final int size = 10000;
    private static int[] array = new int[size];
    public static void testGenerate() throws IOException {
        File myFile = new File("myFile.txt");
        myFile.createNewFile();

        try (FileWriter writer = new FileWriter("myFile.txt")){
            for (int j = 0; j < size; j++) {
                int t = (int) (Math.random() * 10000);
                writer.write(t + " ");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            FileReader fr = new FileReader("myFile.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] str = line.split(" ");
                array = new int[str.length];
                for (int i = 0; i < size; i++) {
                    array[i] = Integer.parseInt(str[i]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
