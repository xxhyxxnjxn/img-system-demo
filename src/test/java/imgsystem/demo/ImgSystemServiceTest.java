package imgsystem.demo;

import imgsystem.demo.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImgSystemServiceTest {

    @Autowired
    FileService fileService;

    String zipFilePath = "src/main/resources/svc/img/test1.zip"; // ZIP 파일 경로
    String tempPath="src/main/resources/svc/temp/";

    @Test
    public void unzipFile(){
        try {
            fileService.unzipFile(zipFilePath, tempPath);

            assertEquals(new File("src/main/resources/svc/temp/test1").exists(),true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getFolderName(){
        String fileNameWithoutExtension = fileService.extractFileNameWithoutExtension(zipFilePath);

        assertEquals(fileNameWithoutExtension,"test1");
    }

    @Test
    public void getZipFileExtension(){
        String zipFileExtension = fileService.extractExtension(zipFilePath);

        assertEquals(zipFileExtension,".zip");
    }

    @Test
    public void getUnzipFiles(){
        String fileNameWithoutExtension = fileService.extractFileNameWithoutExtension(zipFilePath);
        String unzipFolderPath = tempPath+fileNameWithoutExtension;

        File folder = new File(unzipFolderPath);
        File[] files = folder.listFiles();
        assertEquals(files.length,4);
    }

    @Test
    public void getUnzipFileInfos(){
        String zipFileNameWithoutExtension = fileService.extractFileNameWithoutExtension(zipFilePath);
        String unzipFolderPath = tempPath+zipFileNameWithoutExtension;

        File folder = new File(unzipFolderPath);
        File[] files = folder.listFiles();

        String fileNameWithExtension = files[0].getName();
        String fileNameWithoutExtension = fileService.extractFileNameWithoutExtension(fileNameWithExtension);
        String extensionOfFileName=fileService.extractExtension(fileNameWithExtension);

        assertEquals(fileNameWithExtension,"KakaoTalk_Photo_2022-12-28-13-20-53.jpeg");
        assertEquals(fileNameWithoutExtension,"KakaoTalk_Photo_2022-12-28-13-20-53");
        assertEquals(extensionOfFileName,".jpeg");
    }
}