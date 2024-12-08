# img-system-demo

## 개요
이미지 시스템 프로젝트를 참여하면서 리팩토링 하고 싶었던 코드를 재현하기 위한 프로젝트

## 플로우
- [x] zip 파일 경로와 이름 파라메터로 전송 받음
    -[x] 해당 zip 파일 존재하지 않으면 exception 발생
  - ~~zip 확장자가 아닐 시 exception 발생~~ -> 위 조건에서 걸러짐.
-[x] zip 파일 temp 경로에 압축 해제
   -[x] 압축 해제 시 temp경로에 해당 zip 파일 이름으로 폴더 생성 후 그 내부에 압축 해제
-[ ] 압축 파일 내부에 여러 파일이 존재함 
   -[ ] 업무 별 해당 그룹키가 존재하는데 그 그룹 키가 여러개 올 수 있음
  -[ ] 그룹 키 별 파일들을 그룹화 시켜야함
-[ ] 파일들을 반복문을 돌리면서 해당 파일명에 존재하는 값을 추출한다. ("_" 기준)
   -[ ] 업무 별 해당 추출 값들의 의미가 다 다를 수 있다.
-[ ] 추출한 값들을 데이터베이스에 저장한다.
   -[ ] 업무 별 각각 다른 테이블에 저장한다.

## 디자인 패턴 적용
### 템플릿 메소드 패턴
- 압축 해제한 파일들을 반복문 돌리는 로직(반복되는 로직)을 템플릿 메소드 패턴으로 분리한다.

### 템플릿 콜백 패턴
- 반복문 내부에 업무 별로 값을 추출하는 로직을 콜백 패턴으로 분리한다.