# 📖도서관 프로젝트
도서관리 시스템 개발

# 개발 기간
2024.03.01 ~ ?

# ⚙개발 환경
- Java 8
- IDE : IntelliJ
- Springboot(3.x)
- mySql

# ⛓기술 스택


# 주요 기능
## 회원
### 👨내서재
## 대출 현황
 - 현재 대출한 도서 정보 노출
 - 사용자에게 아래 정보 노출
   1. 순번
   2. 도서 제목(클릭 시 해당 도서 상세페이지로 이동)
   3. 대출일
   4. 반납 예정일
   5. 반납 연기 버튼
- 차순 정리는 **대출일**, **반납 예정일** 로 가능

## 대출 이력
 - 과거 대출하였던 도서 정보 노출

  - 사용자에게 아래 정보 노출
    1. 순번
    2. 도서 제목(클릭 시 해당 도서 상세페이지로 이동)
    3. 대출일
    4. 반납일
    5. 상태
    6. 연체(연체일자)
- 차순 정리는 **대출일**, **반납일** 로 가능

## 💜내 책장
- 내가 찜한 책 노출
- 화면 예시
  ![image](https://private-user-images.githubusercontent.com/68980110/309274039-3b4bfbb9-ac51-45bd-aaca-e71dc3fcb69d.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MDkzMTI2MDQsIm5iZiI6MTcwOTMxMjMwNCwicGF0aCI6Ii82ODk4MDExMC8zMDkyNzQwMzktM2I0YmZiYjktYWM1MS00NWJkLWFhY2EtZTcxZGMzZmNiNjlkLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDAzMDElMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwMzAxVDE2NTgyNFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTE5MzBiOTM1OTk1MWNkYTYzM2M1Mjg4ODEyZmZiNjFkOGYyNmFmMTY1NTE4YmIzYzJkMTJmMTViN2FhOTcxZTAmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.qqsQOmHkGdmaeVBE6z4Ea7-HkOCq-7oelGoR116PNcg)

## 🔍도서 조회
- 도서 조회는 누구나 가능
- 조회 조건은 **도서 제목**, **저자명** 으로 가능
- 차순 정리는 **발행년도**, **도서제목** 으로 가능
- 간단 조회 결과 시 사용자에게 아래 내용 노출
  1. 제목
  2. 저자
  3. 발행년도
  4. 도서 상태
  5. 이미지
 
- 상세 조회 결과 시 사용자에 아래 내용 노출
  1. 제목
  2. 저자
  3. 발행연도
  4. 도서상태
  5. 이미지
  6. 리뷰 내용
  7. 내용 설명

## 📝도서 리뷰
 - 대여했던 도서에 한해 회원은 1회 후기 작성 가능
 - 도서 상세 조회 결과 시 해당 내용 노출
     
## 도서 대출/반납/연장
 - 회원만 가능
 - 반납 기한은 대출일로부터 +7일
 - 반납 연장 1회 가능하며, 연장반납은 반납예정일로부터 +7일
 - 회원 당 최대 2권 대여 가능
   
## 🚫도서 연체
- 도서 연체 판단은 **대출한 도서의 반납예정일 넘기는 조건**
- 도서 연체 시 최초 연체 일자부터 연체된 도서의 마지막 반납일+7일까지  도서 대출 불가

## 🔉알림
- 도서 대출 성공 
- 도서 반납 성공(연체된 도서 포함)
- 도서 연체 시 반납 완료 전까지 연체 알림 발송
- 연체 종료


# 프로젝트를 통한 배운 경험

#
