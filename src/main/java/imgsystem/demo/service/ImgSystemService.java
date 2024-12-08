package imgsystem.demo.service;

import imgsystem.demo.code.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImgSystemService {

    private final FileService fileService;

    public void logic(String zipFilePath) {
        String tempPath = Path.test1.getTempPath();     // 추출 경로

        fileService.isExistZipFile(zipFilePath);

        try {
            fileService.unzipFile(zipFilePath, tempPath);
            System.out.println("압축 해제 완료!");

            //압축 해제한 파일 리스트 구하기
            File[] files = fileService.getUnzipFiles(zipFilePath, tempPath);

            //파일 리스트 for 문 돌면서 값 추출
            try{
                getUnzipFileInfos(files);
            }catch (Exception e){
                System.err.println("값 추출 중 오류 발생: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("압축 해제 중 오류 발생: " + e.getMessage());
        }
    }

    public void getUnzipFileInfos(File[] files) {

        for(File file : files){
            System.out.println(file.getName());
        }
    }
}
