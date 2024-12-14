package imgsystem.demo.service;

import imgsystem.demo.repository.CPRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CPService {

    private final ImgSystemService imgSystemService;

    public void extractData(String zipFilePath) {
        Map<String, List<File>> groupFiles = imgSystemService.getGroupFiles(zipFilePath);

        imgSystemService.getUnzipFileInfos(groupFiles, (fileName) -> {
            String[] datas = fileName.split("_");

            log.info("This is CPService , Extract Data is {}", datas[1]);

        });
    }
}
