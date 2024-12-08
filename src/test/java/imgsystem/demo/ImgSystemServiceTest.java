package imgsystem.demo;

import imgsystem.demo.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImgSystemServiceTest {

    @Autowired
    FileService fileService;

    String zipFilePath = "src/main/resources/svc/img/test1.zip"; // ZIP 파일 경로
    String tempPath="src/main/resources/svc/temp/";
    String tempPath2="src/main/resources/svc/temp/test1/";

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

    @Test
    public void getGroupKey(){
        File tempUnzipFolder = new File(tempPath2);
        File[] files = tempUnzipFolder.listFiles();
        List<File> fileList = Arrays.asList(files);

        assertEquals(fileList.size(),4);
        assertEquals(fileList.get(0).getName(),"KakaoTalk_Photo_2022-12-28-13-20-53.jpeg");

        Map<String, List<File>> groupFiles=getGroupFiles(fileList);

        assertEquals(groupFiles.get("KakaoTalk").get(0).getName(),"KakaoTalk_Photo_2022-12-28-13-20-53.jpeg");
    }

    public  Map<String, List<File>> getGroupFiles(List<File> fileList){
        return fileList.stream().collect(Collectors.groupingBy(file -> extractGroupKey(file.getName())));
    }

    public String extractGroupKey(String fileName){
        //바뀌는 부분
        if(fileName == null && fileName.equals("")){
            return "unknown";
        }

        return fileName.split("_")[0];
    }


}