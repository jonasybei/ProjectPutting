package com.mygdx.WriterAndReader;


import java.io.*;
import java.util.ArrayList;

public class Reader {

    private final String filePath = "C:\\Users\\matte.LAPTOP-FLG8V3QC\\Documents\\University Maastricht\\PROJECTS\\Project.Putting\\core\\src\\com\\mygdx\\WriterAndReader\\ScoreInfo.txtt";

    public ArrayList<String> readFile() {

        ArrayList<String> file = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
            String line;
            while ((line = br.readLine()) != null) {

                file.add(line);
            }
            br.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file: " + filePath);
        } catch (IOException ex) {
            System.out.println("Error reading file: " + filePath);
        }

        return file;
    }
}
