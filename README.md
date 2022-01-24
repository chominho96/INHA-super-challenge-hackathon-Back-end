# INHA univ. Super Challenge Hackathon Back-end Repository
## 하텍이미래당 팀

인하대학교 2022 슈퍼챌린지 소프트웨어 해커톤 (2022.01.14 ~ 2022.01.21)
코로나19 관련 종합정보 서비스 백엔드 개발 Repository


## Topic
- 코로나19 방역으로 고생하시는 교육 종사자분들을 위한 코로나19 관련 정보 종합 서비스
- Comprehensive service for information related to COVID-19 for education workers suffering from COVID-19 quarantine










## Features

- 학생/선생님 구분 회원가입, 로그인 기능 (쿠키 기반)
- Student/teacher separate sign up, login function (based on Cookie)
- 자가진단 작성 서비스
- Self-diagnosis service
- 자가진단 미참여자 자동, 수동 (선생님이 학생에게 전송) SMS 알림 서비스 (Naver S.E.N.S Api 기반)
- Automatic/manual SMS notification service for non-participants in self-diagnosis (based on Naver S.E.N.S Api)
- 코로나19 현황 제공 기능 (Open Api 기반)
- Corona 19 status provision function (based on Open Api)
- 질병관리청, 각 학급별의 코로나19 관련 지침 제공 기능
- Korea Centers for Disease Control and Prevention, function to provide guidance related to COVID-19 for each class





## Tech

백엔드 개발에 사용된 언어 및 툴

- [Spring] - 주요 Service, Repository 등을 REST API 기반으로 구현, 프론트엔드와 JSON 타입으로 요청 및 응답
- [Spring boot] - 웹 어플리케이션 개발을 위해 Spring boot을 이용
- [H2 Database] - 회원, 각 회원의 자가진단서를 H2 Database를 이용해 비메모리 저장을 구현
- [Open API] - 코로나19 현황 정보 및 카카오톡 알림 서비스를 공공데이터 포털, 카카오톡 Open API를 이용하여 구현




## Installation

정상적인 프로그램 실행을 위해 JAVA 11 버전과 H2 Database 1.4.200 버전이 필요하다.
JAVA 11 version and H2 Database version 1.4.200 are required for normal program execution.

- JAVA 11 Installation
- https://www.oracle.com/java/technologies/downloads/#java11



- H2 Database 1.4.200 Installation
- Windows가 아닌 타 OS에서는 "Platform-Independent Zip" 을 이용하여 설치
- https://www.h2database.com/html/download-archive.html



## Guideline / IO Format & Examples

애플리케이션 구동 전 H2 Database 초기화 및 실행 방법, 입력과 출력 형식과 예제를 'Guildline and IO Format.txt' 에 정리했다.
You are recommended to read 'Guildline and IO Format.txt' before running application.


## Service Diagram

### 0. Key Services Summary
![전체서비스구성도](https://user-images.githubusercontent.com/66549638/150732365-64abbceb-d745-4cee-8b5d-7143f626f532.jpg)

### 1. Home
![슬라이드3](https://user-images.githubusercontent.com/66549638/150731457-e27e6f60-649f-43b6-a1b9-f2eae381c935.JPG)

### 2. Login
![슬라이드4](https://user-images.githubusercontent.com/66549638/150731777-8cf8985f-edc7-4965-bb18-5c836adcb139.JPG)

### 3. Logout
![슬라이드5](https://user-images.githubusercontent.com/66549638/150731824-a840db0c-d1ba-4fd0-9468-1bcf8e121e1c.JPG)

### 4. Sign up
![슬라이드6](https://user-images.githubusercontent.com/66549638/150731876-8a78d026-b358-43a9-9c65-05f079d71bd8.JPG)

### 5. Self Diagnosis Write
![슬라이드7](https://user-images.githubusercontent.com/66549638/150731920-5fa131b2-d7d1-49f5-9b4c-e8f20d7775b7.JPG)

### 6. Self-diagnosis non-participant List
![슬라이드8](https://user-images.githubusercontent.com/66549638/150731976-96e977cc-97ea-465b-942a-2a3efe9233d0.JPG)

### 7. Self-diagnosis non-participant SMS notification Service
![슬라이드9](https://user-images.githubusercontent.com/66549638/150732043-229fc25c-05c0-43cf-acfa-72ca2e91d069.JPG)














[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
