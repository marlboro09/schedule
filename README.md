Schedule관리 프로젝트
---

개발기간
- 2024.5.15 - 2024.5.17 (스케줄 CRUD 제작)
- 2024.5.25 - 2024.5.31
- 피드백과 리펙토링
- https://github.com/marlboro09/schedule/wiki
---

  
💻 개발환경
- Version : Java 17
- IDE : IntelliJ
- DB: MySQL 8.0.28
- Tools : GitHub, Git
- Framework : SpringBoot 3.2.5
- Swagger : 3.0.0

---
⚙️특징

**1. 회원가입**

사용자는 닉네임, 이름, 비밀번호, 유저권한, ADMIN TOKEN을 입력해 회원가입을 할 수 있습니다. 유저권한은 ADMIN TOKEN값을 입력해 ADMIN유저로 회원가입이 가능하며 ADMIN유저는 모든 모든기능에 접근이 가능합니다.




**2. 로그인**

사용자는 이름과 비밀번호를 입력해 로그인을 할 수 있습니다. 로그인을한 사용자만이 스케줄 관리와 댓글 관리 기능을 사용할 수 있습니다. 




**3. 스케줄 등록**

로그인을 한 사용자만 제목, 내용, 작성일을 입력해 새로운 스케줄을 생성할 수 있습니다. 스케줄 ID, 제목, 내용, 작성자의 닉네임, 일정, 작성시간을 반환하며 이를 통해 사용자에게 스케줄이 등록되었음을 확인할 수 있습니다.




**4. 선택한 스케줄 조회**

사용자는 특정 일정을 선택하여 해당 일정의 정보를 조회할 수 있습니다. 




**5. 스케줄 목록 조회**

사용자는 등록된 전체 스케줄을 조회할 수 있습니다. 이 기능은 작성일 기준 내림차순으로 정렬된 스케줄 목록 확인할 수 있습니다.




**6. 선택한 스케줄 수정**

사용자는 스케줄ID를 입력해 선택한 스케줄의 제목, 내용, 날짜를 수정할 수 있습니다. 수정을 하려면 로그인을 필수로 해야하며 작성한 사용자만 수정을 허용합니다. 수정이 완료되면 업데이트된 스케줄을 확인할 수 있습니다. ADMIN은 직접 작성하지 않아도 수정가능합니다.




**7. 스케줄 삭제**

사용자는 스케줄ID를 입력해 삭제할 수 있습니다. 작성한 사용자만 삭제가 가능하며 ADMIN은 직접 작성한 스케줄이 아니라도 삭제가 가능합니다.




**8. 댓글 등록**

사용자는 특정 스케줄에 댓글을 등록할 수 있습니다. 사용자는 댓글을 등록하려는 스케줄의 ID값과 내용을 입력해 등록이 가능합니다




**9. 댓글 수정**

사용자는 본인이 작성한 댓글의 ID를 입력해 댓글 내용을 수정할 수 있습니다. ADMIN은 직접 작성하지 않아도 내용을 수정할 수 있습니다.




**10. 댓글 삭제**

사용자는 본인이 작성한 댓글의 ID를 입력해 댓글을 삭제할 수 있습니다. ADMIN은 직접 작성하지 않아도 삭제할 수 있습니다.




---
Use Case Diagram
---
![ERD](https://github.com/marlboro09/schedule/blob/main/src/main/resources/image/newschedule.png)
---


API 명세서
---
![API](https://github.com/marlboro09/schedule/blob/main/src/main/resources/image/API.png)
---


ERD DIAGRAM
---
![Diagram](https://github.com/marlboro09/schedule/blob/main/src/main/resources/image/ERD.png)
