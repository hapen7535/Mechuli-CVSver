# 편의점 버전 메추리
편의점 음식 제품을 추천해주는 안드로이드 애플리케이션 프로젝트입니다.  
2022년 6월에 마무리한 메추리 애플리케이션의 다른 버전입니다.  
이번 메추리에서는 ***편의점 음식 제품***에 주제를 집중시켜 개발하였습니다.  
또한 편의점 음식 제품을 이용하여 만든 레시피를 공유할 수 있는 커뮤니티 기능을 추가하였습니다.  
  
### 📍프로젝트 요약
- 편의점 3사(세븐일레븐, CU, GS25)의 음식 제품에 대한 평점을 매길 수 있습니다.
- 매긴 평점을 바탕으로 편의점 음식 제품을 추천받을 수 있습니다.
- 로그인 후에는 평점을 수정할 수 있으며, 다른 제품에 대한 평점을 추가/수정할 수도 있습니다.
- 로그인 후에는 편의점 음식 레시피 커뮤니티를 이용할 수 있습니다.
- 편의점 음식 레시피 커뮤니티에서는 편의점 음식 제품을 이용한 레시피를 공유할 수 있습니다.

### 🙋본인 기여 내용
- 추천 기능 안드로이드 부분 개발
- 커뮤니티 기능 안드로이드 부분 개발
- UI/UX 디자인

### ⚒사용 기술
- Languages : Kotlin
- Networking : Retrofit2
- Asynchronus Task : Coroutine
- Android Architecture Components : ViewModel, LiveData, DataBinding
- Image Loading : Coil
- Architecture Pattern : MVVM

### 💿시연 영상
- 회원가입
<center><img src="https://user-images.githubusercontent.com/79076150/214351086-48e783a7-a1f2-4fd9-b96a-442345edd9bd.gif" width="300" height="612"></center>
  
회원가입 시에는 편의점 음식 제품에 대한 평점이 5개 필요합니다.  
해당 평점은 추천 알고리즘에 사용될 초기 데이터가 됩니다.
  

- 평점 추가/수정
<center><img src="https://user-images.githubusercontent.com/79076150/214352283-9b6feb37-fffc-49cc-9a91-e931330ed00b.gif" width="300" height="612"></center>
  
로그인 후에는 편의점 음식 제품을 검색하여, 평점을 추가하거나 수정할 수 있습니다.  
수정된 평점 이력이 추천 받을 시에 바로 반영됩니다.


- 추천 받기 기능
<center><img src="https://user-images.githubusercontent.com/79076150/214353147-7950b814-80c0-403b-b31b-3ac32e65490e.gif" width="300" height="612"></center>
  
로그인 후에 메뉴 추천 받기 버튼을 누르면 바로 추천을 받을 수 있습니다.
  
- 커뮤니티 기능
<center><img src="https://user-images.githubusercontent.com/79076150/214353335-dd5c999c-1bb1-49e4-a489-abf66dc0bada.gif" width="300" height="612"></center>
  
로그인 후에 커뮤니티에 접속할 수 있습니다.  
커뮤니티에서는 편의점 음식 제품으로 만든 레시피를 공유할 수 있습니다.  
다른 사용자의 레시피에 댓글을 달아, 평점을 매기고 식평을 남길 수 있습니다.  
  
---
  
### ⚙️프로젝트 구성 Project Architecture
<center><img src="https://user-images.githubusercontent.com/79076150/214353899-b24717b3-cbf1-4fa6-b691-c1e272b3d4d4.png" width="800" height="450"></center>


