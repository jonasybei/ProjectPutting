package com.mygdx.CrazyPutting.WriterAndReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Writer {

  private final String pathToDoc = "C:\\Users\\matte.LAPTOP-FLG8V3QC\\Documents\\University Maastricht\\PROJECTS\\Project.Putting\\core\\src\\com\\mygdx\\WriterAndReader\\ScoreInfo.txtt";
  private Reader rd = new Reader();

  public void write(String str, String elementType) {

    ArrayList<String> existingFile = this.rd.readFile();

    try {
      FileWriter writer = new FileWriter(this.pathToDoc);
      PrintWriter out = new PrintWriter(writer);

      for (int i = 0; i < existingFile.size(); i++) {
        if (i % 3 == 0) {
          out.println(existingFile.get(i));
        } else {
          out.println(existingFile.get(i));
        }
      }

      if (elementType.equals("name")) {
        out.println(" ");
        out.println("Username = " + str);
      } else if (elementType.equals("level")) {
        out.println("Level = " + str);
      } else if (elementType.equals("score")) {
        out.println("Score = " + str);
      } else {
        out.println("Time Needed = " + str);
      }

      writer.close();
    } catch (IOException ex) {

    }
  }

}
