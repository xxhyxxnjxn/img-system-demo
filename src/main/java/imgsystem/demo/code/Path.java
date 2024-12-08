package imgsystem.demo.code;

import lombok.Getter;

@Getter
public enum Path {
    test1("src/main/resources/svc/temp/", "src/main/resources/svc/error/");

    private String tempPath;
    private String errorPath;

    Path(String tempPath ,String errorPath) {
        this.tempPath = tempPath;
        this.errorPath = errorPath;
    }
}
