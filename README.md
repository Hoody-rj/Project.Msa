## 구성 내용

2025-07-31
각 모듈 별로 흩어져 있던 Application을 하나로 합침

커밋 초기 History
> product => https://github.com/Hoody-rj/msa_productserver.git <br>
> order => https://github.com/Hoody-rj/msa_order.git

### TIL 공부 내용
> TIL => https://velog.io/@hoody-rj/posts

### MindMap/ERD
- 진행 로직 및 구성 내용 정리 Map
- 경로
  + Service Struct Map 폴더 참조

## 프로그램 구성
### Up-Server
  - port : 19090
  - Eureka 서버 사용
  - 
### Front-Gateway
  - port : 19091
### Middle-Product
  - port 1: 19093
  - port 2: 19094
  - id를 제외한 모든 값을 입력해야함
### Middle-Order
  - port : 19092
  - id를 제외한 모든 값을 입력해야함
### Middle-Auth
  - port : 19095
  - Auth 키 값 발급 시 기존 값은 user
  - 
<hr>
## 프로그램 로직
/auth/signU





