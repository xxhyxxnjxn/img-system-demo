package imgsystem.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImgSystemServiceTest {

    @Autowired
    FileService fileService;

    @Test
    public void unzipFile(){
        String zipFilePath = "src/main/resources/svc/img/test1.zip"; // ZIP 파일 경로
        String tempPath="src/main/resources/svc/temp/";
        try {
            fileService.unzipFile(zipFilePath, tempPath);

            assertEquals(new File("src/main/resources/svc/temp/test1").exists(),true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}