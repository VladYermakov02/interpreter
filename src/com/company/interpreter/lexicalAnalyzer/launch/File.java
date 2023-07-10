package com.company.interpreter.lexicalAnalyzer.launch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class File {
    private final String filePath;

    public File(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> getLines() {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(new FileReader(filePath))) {
            String strTemp;
            while ((strTemp = buffer.readLine()) != null) {
                lines.add(strTemp);
            }
        }
        catch (IOException e) {
            System.out.println("IOException has occurred");
            e.printStackTrace();
        }
        return lines;
    }
}
