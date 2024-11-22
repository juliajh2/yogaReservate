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
- 사용자가 수업 예약을 완료했을 때, 수업 예약 완료 알림을 보낸다.
- 사용자가 수업 예약을 취소 했을 때, 수업 예약 취소 알림을 보낸다.
4. Mypage
- 사용자가 예약한 수업을 보여준다.

## MSA 아키텍처 구성도 🎀
![image](https://github.com/user-attachments/assets/61d2eb90-d154-4506-8fce-fe066591e5ef)
- Istio: Service Mesh. 각 마이크로서비스의 네트워크 트래픽을 중앙에서 제어하고 관리합니다.
- Microservices: Reservation, YogaClass, Notification, MyPage
- Kafka: 메시지 브로커. 
## 이벤트 스토밍 ✨
![image](https://github.com/user-attachments/assets/10deb8b8-5952-4b53-8fbf-ee62253f77e6)

## MSA 개발 💻
### 분산트랜잭션-Saga
- classId=2인 yogaClass 생성. 최대 예약 인원 2명.<br>
![image](https://github.com/user-attachments/assets/8b2ad392-1521-4b4e-a665-4a551b90d55b)

- userId=11이 classId=2인 yogaClass 예약<br>
![image](https://github.com/user-attachments/assets/a1d26ed2-9cad-4915-a699-b48e6616de9f)
![image](https://github.com/user-attachments/assets/1b5ec3e6-3d5f-4d74-987e-7425ad173cd7)

- userId=12이 classId=2인 yogaClass 예약<br>
![image](https://github.com/user-attachments/assets/423f4837-5f47-4e35-b4cf-f21d361bfcb2)

- Kafaka를 통해 이벤트를 Pub/Sub<br>
![image](https://github.com/user-attachments/assets/8e64552e-b8a1-4baf-b81d-68431727dae8)

### 보상처리 - Compensation
- userId=13이 classId=2인 yogaClass 예약 
![image](https://github.com/user-attachments/assets/4f8aa680-553c-45b7-80b8-62bdb4ba2a3b)
![image](https://github.com/user-attachments/assets/f7a331af-dd7a-403f-be1e-6e5d6f50ef2a)
![image](https://github.com/user-attachments/assets/2f335953-6f9e-4518-8df4-1adc3f34eeb4)
-> 수강인원이 올라가지 않음
-> status update
  
### 단일 진입점 - Gateway
- 8088 port로 Gateway를 통해 서비스 호출<br>
![image](https://github.com/user-attachments/assets/158721b6-ebdf-4426-9fa4-cea929e379c6)

### 분산 데이터 프로젝션 - CQRS
![image](https://github.com/user-attachments/assets/a1608540-50eb-4618-8611-c629460e552b)

## 클라우드 배포 ☁
- azure vm 가상머신 접속
![image](https://github.com/user-attachments/assets/a45aa09b-987e-4e90-a17b-14bb753b1528)

- Jenkins Pipeline 생성
![image](https://github.com/user-attachments/assets/0051deab-4c2b-4d71-a2ed-469a49dbf0a3)

- Jenkinsfile 변경
![image](https://github.com/user-attachments/assets/d942c42f-d1b5-4884-b33c-d503535c7de6)

- Webhook 설정<br>
![image](https://github.com/user-attachments/assets/11dc48cd-f862-4a7a-a212-5dd307c1346b)

- github에서 push 할 경우, trigger되어 Jenkins를 통해 배포
![image](https://github.com/user-attachments/assets/166d9ef0-46f4-4303-b61a-b6ea16836388)
![image](https://github.com/user-attachments/assets/f6c3217a-776f-4552-887b-b5134d59803a)


## 컨테이너 🚦
### 컨테이너 자동확장 - HPA
- AutoScale 설정<br>
![image](https://github.com/user-attachments/assets/c41fd4df-a7be-4787-9c46-710fc67e48b3)
- 부하테스트<br>
![image](https://github.com/user-attachments/assets/7ad7f293-bacb-4e60-8da4-e8bc33c660a6)
- pod 자동으로 증가<br>
![image](https://github.com/user-attachments/assets/58cf6205-1223-45f2-b1ad-087511a59708)
- targets 증가<br>
![image](https://github.com/user-attachments/assets/81a64d64-6aff-4822-a52f-a119b134a024)

### 컨테이너로부터 환경분리 - ConfigMap
- Config Map 생성<br>
![image](https://github.com/user-attachments/assets/4bca90f3-feea-4b62-90aa-c73bc78d37e5)
- deployment.yaml 변경<br>
![image](https://github.com/user-attachments/assets/eb68a8b5-ae58-470d-8e0d-5eb4d46b7f0b)
- 환경변수 확인<br>
![image](https://github.com/user-attachments/assets/dbb401ec-f9b7-4a98-b301-27638463db86)

### 클라우드스토리지 활용 - PVC
- volumeMounts 설정<br>
![image](https://github.com/user-attachments/assets/143969a8-9ca3-4a92-a0ef-7bd9d23e1013)
![image](https://github.com/user-attachments/assets/21c991d2-7ea8-47e7-89b2-c68b1b7010f6)
- 테스트 파일 생성<br>
![image](https://github.com/user-attachments/assets/bb34bd0c-f9f0-475f-9096-19b69c69067b)
- 다른 pods에서도 확인 가능<br>
![image](https://github.com/user-attachments/assets/f0d3568b-b87e-4287-b541-8388590afd2f)

### 무정지배포 - Readiness Probe
- Readiness probe: 컨테이너가 비정상일 경우에는 해당 Pod를 사용할 수 없음으로 표시하고 서비스에서 제외
- deployment.yaml 변경 후 재배포<br>
![image](https://github.com/user-attachments/assets/8a3ca8ff-1925-465f-bda0-91578f12e1df)

### 서비스 메쉬 응용 - Mesh
- Istio 사이드카 주입 활성화<br>
![image](https://github.com/user-attachments/assets/86fca42e-fabf-416b-96a5-26071d0e5b70)
-> default 네임스페이스에 배포된 모든 Pod에 자동으로 Istio 사이드카 프록시가 추가
- Istion사이드카 프록시 주입됐는지 확인<br>
![image](https://github.com/user-attachments/assets/d36790c6-73cf-46ed-b0fe-d0eef2329460)

### 통합 모니터링 - Monitoring
- 프로메테우스 접속
![image](https://github.com/user-attachments/assets/5c11e1d1-1561-41cf-9fcb-762dd744c168)

