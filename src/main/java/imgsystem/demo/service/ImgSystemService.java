package imgsystem.demo.service;

import imgsystem.demo.code.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

            //그룹키 별 파일 그룹화
            Map<String, List<File>> groupFiles = getGroupFiles(files);

            //파일 리스트 for 문 돌면서 값 추출
            getUnzipFileInfos(groupFiles);

        } catch (IOException e) {
            System.err.println("압축 해제 중 오류 발생: " + e.getMessage());
        }
    }

    public Map<String, List<File>> getGroupFiles(File[] files){
        List<File> fileList = Arrays.asList(files);

        return fileList.stream().collect(Collectors.groupingBy(file -> extractGroupKey(file.getName())));
    }

    public String extractGroupKey(String fileName){
        //바뀌는 부분
        if(fileName == null && fileName.equals("")){
            return "unknown";
        }

        return fileName.split("_")[0];
    }

    public void getUnzipFileInfos(Map<String, List<File>> groupFiles) {
        //바뀌는 부분
        for (Map.Entry<String, List<File>> entry : groupFiles.entrySet()) {
            System.out.println("Map entrySet : " + entry.getKey());
            List<File> fileList = entry.getValue();
            for (File file : fileList) {
                System.out.println("File name : " + file.getName());
            }
        }
    }
}
