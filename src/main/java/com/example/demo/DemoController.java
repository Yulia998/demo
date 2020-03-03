package com.example.demo;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.CDL;
import org.json.JSONObject;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;

@RestController
public class DemoController {
    private static final String EXTERNAL_FILE_PATH = "C:/fileDownloadExample/";

    @RequestMapping("/")
    public ResponseEntity<String> index() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://gturnquist-quoters.cfapps.io/api/random";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        JSONObject jsonObject = new JSONObject(response.getBody());
        String type = jsonObject.getString("type");
        String quote = jsonObject.getJSONObject("value").getString("quote");
        return response;
    }

    @RequestMapping("/doc")
    public ResponseEntity<?> crdoc() throws Exception {
        return ResponseEntity.ok(new CreateDocument().doc());
//        File file = new File("createdocument.docx");
//        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
////        return ResponseEntity.badRequest().body("bad");
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+file.getName())
//                .contentLength(file.length())
//                .body(resource);
    }

    @RequestMapping("/del")
    public ResponseEntity<?> deldoc() throws Exception {
        File file = new File("createdocument2.docx");
        file.delete();
        return ResponseEntity.ok("delete");
    }

    @RequestMapping(
            value = "/person" , produces = {"application/json", "application/xml"})
    public Person person() {
        Person person2 = new Person("Nick", "Yan");
        Person person = new Person("Ann", "Sm");
        return person;
    }
}
