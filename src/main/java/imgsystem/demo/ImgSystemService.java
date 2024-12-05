package imgsystem.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImgSystemService {

    private final FileService fileService;

    public void logic(String zipFilePath){
        String destDirectory = "src/main/resources/svc/temp/";     // 추출 경로

        try {
            fileService.unzipFile(zipFilePath, destDirectory);
            System.out.println("압축 해제 완료!");


        } catch (IOException e) {
            System.err.println("압축 해제 중 오류 발생: " + e.getMessage());
        }
    }
}
