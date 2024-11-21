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
- classId=2인 yogaClass 생성. 최대 예약 인원 2명.
![image](https://github.com/user-attachments/assets/8b2ad392-1521-4b4e-a665-4a551b90d55b)

- userId=11이 classId=2인 yogaClass 예약
![image](https://github.com/user-attachments/assets/a1d26ed2-9cad-4915-a699-b48e6616de9f)

- classId=2인 yogaClass의 reservedSeat + 1
![image](https://github.com/user-attachments/assets/1b5ec3e6-3d5f-4d74-987e-7425ad173cd7)

- userId=12이 classId=2인 yogaClass 예약
- classId=2인 yogaClass의 reservedSeat + 1
![image](https://github.com/user-attachments/assets/423f4837-5f47-4e35-b4cf-f21d361bfcb2)

- userId=13이 classId=2인 yogaClass 예약
![image](https://github.com/user-attachments/assets/4f8aa680-553c-45b7-80b8-62bdb4ba2a3b)

- 수강 인원 더 올라가지 않음.
'''
public static void increaseReserveSeat(ReservePlaced reservePlaced) {
        
        //implement business logic here:
        repository().findById(Long.valueOf(reservePlaced.getClassId())).ifPresent(yogaClass->{
            
            //예약가능
            if(yogaClass.getReservedSeat() < yogaClass.getMaxSeat()){
                yogaClass.setReservedSeat(yogaClass.getReservedSeat() + 1);
                repository().save(yogaClass);

                SeatNumIncreased seatNumIncreased = new SeatNumIncreased(yogaClass);
                seatNumIncreased.publishAfterCommit();
            }
            //예약불가능
            else{
                NoSeatsLeft noSeatsLeft = new NoSeatsLeft(yogaClass);
                noSeatsLeft.publishAfterCommit();
            }

         });
  }
'''
![image](https://github.com/user-attachments/assets/f7a331af-dd7a-403f-be1e-6e5d6f50ef2a)

### 보상처리 - Compensation
### 단일 진입점 - Gateway
### 분산 데이터 프로젝션 - CQRS

## 클라우드 배포 ☁

## 컨테이너 🚦
