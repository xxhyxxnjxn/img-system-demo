package imgsystem.demo.controller;

import imgsystem.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final CPService cpService;
    private final CTService ctService;
    private final BNPLService bnplService;

    @GetMapping("/cp")
    public ResponseEntity receiveCPFile(String zipFilePath) {
// localhost:8080?zipFilePath=src/main/resources/svc/img/test1.zip
//        String zipFilePath = "src/main/resources/svc/img/test1.zip"; // ZIP 파일 경로
        try{
            cpService.extractData(zipFilePath);

            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/ct")
    public ResponseEntity receiveCTFile(String zipFilePath) {
// localhost:8080?zipFilePath=src/main/resources/svc/img/test1.zip
//        String zipFilePath = "src/main/resources/svc/img/test1.zip"; // ZIP 파일 경로
        try{
            ctService.extractData(zipFilePath);

            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/bnpl")
    public ResponseEntity receiveBNPLFile(String zipFilePath) {
// localhost:8080?zipFilePath=src/main/resources/svc/img/test1.zip
//        String zipFilePath = "src/main/resources/svc/img/test1.zip"; // ZIP 파일 경로
        try{
            bnplService.extractData(zipFilePath);

            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }
}
