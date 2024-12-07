package imgsystem.demo.controller;

import imgsystem.demo.service.ImgSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final ImgSystemService imgSystemService;

    @GetMapping("/")
    public ResponseEntity receiveFile(String zipFilePath) {
// localhost:8080?zipFilePath=src/main/resources/svc/img/test1.zip
//        String zipFilePath = "src/main/resources/svc/img/test1.zip"; // ZIP 파일 경로
        try{
            imgSystemService.logic(zipFilePath);

            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }
}
