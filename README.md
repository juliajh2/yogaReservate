# 주제: 요가 수업 예약 서비스 🧘‍♂️

## 시나리오 📝
1. YogaClass
- 관리자는 요가 수업 시간을 등록, 수정, 삭제할 수 있다.
- 남은 자리가 없는 수업은 예약할 수 없다.
2. Reservation
- 사용자는 요가 수업을 예약할 수 있다.
- 사용자가 요가 수업을 예약하면 수업 인원 수가 증가한다.
- 사용자는 예약을 취소할 수 있다.
- 사용자가 요가 수업을 예약하면 수업 인원 수가 증가한다.
- 사용자가 선택한 날이 수업 최대 인원 충원했으면 예약 불가능하다.
3. Notification
- 예약한 수업일자에 알림을 보낸다.
- 사용자가 수업 예약을 완료했을 때, 수업 예약 완료 알림을 보낸다.
- 사용자가 수업 예약을 취소 했을 때, 수업 예약 취소 알림을 보낸다.
4. Mypage
- 사용자가 예약한 수업을 보여준다.

## MSA 아키텍처 구성도 🎀
![image](https://github.com/user-attachments/assets/61d2eb90-d154-4506-8fce-fe066591e5ef)

## 이벤트 스토밍 ✨
![image](https://github.com/user-attachments/assets/10deb8b8-5952-4b53-8fbf-ee62253f77e6)

## MSA 개발 💻
### 분산트랜잭션-Saga
classId="2"인 yogaClass 생성
![image](https://github.com/user-attachments/assets/82f20c3c-04d3-4c9e-87bc-64bc79f7105f)
classId="2"인 yogaClass 예약
![image](https://github.com/user-attachments/assets/baa2aaf0-3af7-41e8-9b42-33054c769a63)
classId="2"인 yogaClass의 reservedSeat + 1
![image](https://github.com/user-attachments/assets/31a54efd-13b9-415f-8931-8a247627778e)

### 보상처리 - Compensation
### 단일 진입점 - Gateway
### 분산 데이터 프로젝션 - CQRS

## 클라우드 배포 ☁

## 컨테이너 🚦
