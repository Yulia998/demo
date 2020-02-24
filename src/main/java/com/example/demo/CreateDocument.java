package com.example.demo;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.net.URL;
import java.util.List;

public class CreateDocument {


    public String doc () throws Exception {
        XWPFDocument document = new XWPFDocument(new FileInputStream(("createdocument.docx")));
        List<XWPFTable> table = document.getTables();
        String s = table.get(0).getRow(0).getCell(1).getText().replace("{title}", "The Avengers");
//        String s = table.get(0).getRow(0).getCell(1).getParagraphs().get(0).getRuns().get(0).text().replace("{title}", " The Avengers");
        table.get(0).getRow(0).getCell(1).getParagraphs().get(0).getRuns().get(3).setText("The Avengers", 0);
        table.get(0).getRow(2).getCell(1).getParagraphs().get(0).getRuns().get(3)
                .setText("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop" +
                        "the mischievous Loki and his alien army from enslaving humanity.", 0);
//        List<XWPFRun> run = table.get(0).getRow(0).getCell(0).getParagraphs().get(0).getRuns();
//        System.out.println(run);
        InputStream pic = new URL("https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg").openStream();
        XWPFRun run = table.get(0).getRow(0).getCell(0).getParagraphs().get(0).getRuns().get(1);
        run.getDocument().addPictureData(pic, Document.PICTURE_TYPE_JPEG);

//        for (XWPFTable xwpfTable : table) {
//            List<XWPFTableRow> tableRows = xwpfTable.getRows();
//        }
//        System.out.println("dds");
        FileOutputStream out = new FileOutputStream(new File("createdocument2.docx"));
        document.write(out);
        out.flush();
        out.close();
        return "try";
    }
}
