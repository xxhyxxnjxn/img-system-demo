package imgsystem.demo.service;

import imgsystem.demo.code.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImgSystemService {

    private final FileService fileService;

    public Map<String, List<File>> getGroupFiles(String zipFilePath) {
        String tempPath = Path.test1.getTempPath();     // 추출 경로

        fileService.isExistZipFile(zipFilePath);

        try {
            fileService.unzipFile(zipFilePath, tempPath);
            log.info("압축 해제 완료!");

            //압축 해제한 파일 리스트 구하기
            File[] files = fileService.getUnzipFiles(zipFilePath, tempPath);

            //그룹키 별 파일 그룹화
            return getGroupFiles(files);

        } catch (IOException e) {
            log.error("압축 해제 중 오류 발생: {}" ,e.getMessage());
        }

        return null;
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

    public void getUnzipFileInfos(Map<String, List<File>> groupFiles, BusinessService businessService) {
        //바뀌는 부분
        for (Map.Entry<String, List<File>> entry : groupFiles.entrySet()) {
            log.info("Map entrySet : {}" , entry.getKey());
            List<File> fileList = entry.getValue();
            for (File file : fileList) {
                String fileName = file.getName();
                String fileNameWithoutExtension = fileService.extractFileNameWithoutExtension(fileName);
                String extension = fileService.extractExtension(fileName);
                log.info("File name : {}",fileName);
                log.info("fileNameWithoutExtension : {}" ,fileNameWithoutExtension);
                log.info("extension : {}" ,extension);

                //업무별 추출 값 달라야함
                //익명 내부 클래스 이용
                businessService.extractData(fileName);
            }
        }
    }


}
