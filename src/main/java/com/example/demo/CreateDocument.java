package com.example.demo;

import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.Paragraph;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CreateDocument {


    public String doc () throws Exception {
        XWPFDocument document = new XWPFDocument(new FileInputStream(("./src/main/resources/templates/createdocument.docx")));
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        XWPFRun runTable;
//        for (int i = 0; i<2; i++) {
            runTable = paragraphs.get(0).createRun();
            runTable.addBreak(BreakType.PAGE);
            document.createTable();
            XWPFTable xtable = document.getTables().get(0);
            XWPFRun run = xtable.getRow(0).getCell(0).getParagraphs().get(0).getRuns().get(0);
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://m.media-amazon.com/images/M/MV5BMTYwOTEwNjAzMl5BMl5BanBnXkFtZTcwODc5MTUwMw@@._V1_SX300.jpg";
            ResponseEntity<Resource> responseEntity = restTemplate.getForEntity(url, Resource.class);
            InputStream inputStream = responseEntity.getBody().getInputStream();
            run.addPicture(inputStream , XWPFDocument.PICTURE_TYPE_JPEG, "",
                Units.toEMU(200), Units.toEMU(200));
            xtable.getRow(0).getCell(1).getParagraphs().get(0).getRuns().get(3).setText("The Avengers", 0);

            document.setTable(1, document.getTables().get(0));
            //XWPFTable table1 = document.getTables().get(1);
            //table1.getRow(0).getCell(1).getParagraphs().get(0).getRuns().get(3).setText("The Avengers", 0);
            document.createParagraph();
        url = "https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg";
        responseEntity = restTemplate.getForEntity(url, Resource.class);
        inputStream = responseEntity.getBody().getInputStream();
        xtable.getRow(0).getCell(0).getParagraphs().get(0).removeRun(0);
        xtable.getRow(0).getCell(0).getParagraphs().get(0).createRun()
                .addPicture(inputStream , XWPFDocument.PICTURE_TYPE_JPEG, "",
                Units.toEMU(200), Units.toEMU(200));
        xtable.getRow(0).getCell(1).getParagraphs().get(0).getRuns().get(3).setText("The", 0);
//        }
//        List<XWPFTable> table = document.getTables();
////        String s = table.get(0).getRow(0).getCell(1).getText().replace("{title}", "The Avengers");
////        String s = table.get(0).getRow(0).getCell(1).getParagraphs().get(0).getRuns().get(0).text().replace("{title}", " The Avengers");
//        table.get(1).getRow(0).getCell(1).getParagraphs().get(0).getRuns().get(3).setText("The Avengers", 0);
//        table.get(1).getRow(2).getCell(1).getParagraphs().get(0).getRuns().get(3)
//                .setText("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop" +
//                        "the mischievous Loki and his alien army from enslaving humanity.", 0);
//        XWPFRun run = table.get(1).getRow(0).getCell(0).getParagraphs().get(0).getRuns().get(1);
////        System.out.println(run);

//
//
//        table.get(2).getRow(0).getCell(1).getParagraphs().get(0).getRuns().get(3).setText("The blabla", 0);
//        table.get(2).getRow(2).getCell(1).getParagraphs().get(0).getRuns().get(3)
//                .setText("Earth's mightiest ", 0);
//        run = table.get(2).getRow(0).getCell(0).getParagraphs().get(0).getRuns().get(1);
//        url = "https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg";
//        responseEntity = restTemplate.getForEntity(url, Resource.class);
//        inputStream = responseEntity.getBody().getInputStream();
//        run.addPicture(inputStream , XWPFDocument.PICTURE_TYPE_JPEG, "",
//                Units.toEMU(200), Units.toEMU(200));


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

//    public String doc () throws Exception {
//        XWPFDocument document = new XWPFDocument(new FileInputStream(("createdoc.docx")));
//        XWPFParagraph p = document.createParagraph();
//        XWPFRun r = p.createRun();
//        RestTemplate im = new RestTemplate();
//        String ur = "https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg";
//        ResponseEntity<Resource> responseEntity = im.getForEntity(ur, Resource.class);
////        URL imageUrl = new URL("https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg");
// //       File imageFile = new File("dom.jpg");
////        BufferedImage image = ImageIO.read(imageUrl);
////        InputStream inputStream = imageUrl.openStream();
//        InputStream inputStream = responseEntity.getBody().getInputStream();
//
//        r.addPicture(inputStream , XWPFDocument.PICTURE_TYPE_JPEG, "",
//                Units.toEMU(200), Units.toEMU(200));
////        image.setAlignment(ParagraphAlignment.CENTER);
////        XWPFRun imageRun = image.createRun();
////        imageRun.setTextPosition(20);
////        URL url = ClassLoader.getSystemResource("dom.jpg");
////        Path imagePath = Paths.get(ClassLoader.getSystemResource("dom.jpg").toURI());
////        imageRun.addPicture(Files.newInputStream(imagePath), XWPFDocument.PICTURE_TYPE_JPEG, imagePath.getFileName().toString(),
////                Units.toEMU(50), Units.toEMU(50));
//        FileOutputStream out = new FileOutputStream(new File("createdoc2.docx"));
//        document.write(out);
//        out.flush();
//        out.close();
//        return "try";
//    }
}
