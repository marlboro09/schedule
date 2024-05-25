Schedule관리 프로젝트
---

개발기간
- 2024.5.15 - 2024.5.17
---

  
💻 개발환경
- Version : Java 17
- IDE : IntelliJ
- DB: MySQL 8.0.28
- Tools : GitHub, Git
- Framework : SpringBoot 3.2.5

---
⚙️특징


1. 스케줄 등록

사용자는 제목, 내용, 이름, 비밀번호, 작성일을 입력해 새로운 스케줄을 생성할 수 있습니다. 생성된 일정 비밀번호를 제외하고 반환되며, 이를 통해 사용자에게 스케줄이 등록되었음을 확인할 수 있습니다.


2. 선택한 스케줄 조회

사용자는 특정 일정을 선택하여 해당 일정의 정보를 조회할 수 있습니다. 


3. 스케줄 목록 조회

사용자는 등록된 전체 스케줄을 조회할 수 있습니다. 이 기능은 작성일 기준 내림차순으로 정렬된 스케줄 목록 확인할 수 있습니다.


4. 선택한 스케줄 수정

사용자는 특정 스케줄의 제목, 내용, 담당자를 수정할 수 있습니다. 수정을 하려면 비밀번호를 함께 보내야 하며, 서버는 비밀번호가 일치하는 경우에만 수정을 허용합니다. 수정이 완료되면 업데이트된 스케줄을 확인할 수 있습니다.


5. 스케줄 삭제

사용자는 특정 스케줄을 삭제할 수 있습니다. 삭제 요청 시에도 비밀번호를 함께 보내야 하며, 비밀번호가 일치하는 경우에만 삭제가 가능합니다.


---
Use Case Diagram
---
![ERD](https://github.com/marlboro09/schedule/blob/main/src/main/resources/scheduleERD.png)
---


API 명세서
---
![API](https://github.com/marlboro09/schedule/blob/main/src/main/resources/scheduleAPI.png)
---


ERD DIAGRAM
---
![Diagram](https://github.com/marlboro09/schedule/blob/main/src/main/resources/scheduleDiagram.png)
