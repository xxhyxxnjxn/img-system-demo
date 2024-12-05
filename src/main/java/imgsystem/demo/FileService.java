package imgsystem.demo;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class FileService {

    public void unzipFile(String zipFilePath, String destDirectory) throws IOException {

            File destDir = new File(destDirectory);
            if (!destDir.exists()) {
                destDir.mkdirs(); // 대상 디렉터리가 없으면 생성
            }

            try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
                ZipEntry entry = zipIn.getNextEntry(); // ZIP 파일의 첫 번째 항목 가져오기
                while (entry != null) {
                    String filePath = destDirectory + entry.getName();
                    if (!entry.isDirectory()) {
                        extractFile(zipIn, filePath); // 파일 추출
                    } else {
                        File dir = new File(filePath);
                        dir.mkdirs(); // 디렉터리 생성
                    }
                    zipIn.closeEntry(); // 현재 항목 닫기
                    entry = zipIn.getNextEntry(); // 다음 항목으로 이동
                }
            }
    }

    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        if (filePath.contains("__MACOSX") || filePath.contains("/._")) {
            return; // 불필요한 항목 무시
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = zipIn.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
}
