# 🚩1st Week


### 🎥실행영상

<div>
  Login👉
<img width="200" src="https://user-images.githubusercontent.com/68374234/114297368-28f3f200-9aeb-11eb-8015-80ca0df748b8.gif">  
  SignUp👉
  <img width="200" src="https://user-images.githubusercontent.com/68374234/114297373-3315f080-9aeb-11eb-813e-335ef89c2609.gif">
</div>





### 🍭strartActivityForResult(), registerActivityForResult()

- <a href = "https://developer.android.com/training/basics/intents/result?hl=ko"> 공식 문서 보러가기</a>

- startActivity()와 startActivityForResult() 모두 화면 전환에 사용되는데 차이점은?

  - startActivity() : 자식 액티비티에서의 결과를 부모 액티비티에서 처리하지 않아도 될 때 사용.
  - startActivityForResult() : 자식 액티비티에서의 결과를 부모 액티비티에서 처리할 때 사용.

- onActivityResult()에서 startActivityForResult()를 통해 받아온 값들을 처리해줬었는데 이제 사라진다고 함...

- 그래서 registerForActivityResult()에서 처리.

  - 기존에 startActivityForResult()를 사용하려면 Request Code와 Result Code가 필요했음.
  각 액티비티마다 RequestCode를 부여해줬어야했고 onActivityResult에서 조건을 확인해서 처리해야했음 => 코드 지저분해짐...
    - Request Code : 각 액티비티에 부여해준 값.<br>
      ▶ 각 액티비티마다 다르게 지정 가능. 이 값에 따라 처리할 것을 달리할 수 있음.
    - Result Code : 액티비티가 정상적으로 왔는지 확인해주는 값.<br>
      ▶ 자식 액티비티에서 setResult()를 통해 값 지정 가능.
  - registerForActivityResult()에서 처리하게되면 RequestCode가 필요 없음.
    - registerForActivityResult()는 ActivityResultContract 및 ActivityResultCallback을 가져와서 다른 활동을 실행하는데 사용할 ActivityResultLauncher를 반환함.
    - ActivityResultContract : 결과를 생성하는데 필요한 입력 유형과 결과의 출력 유형을 정의함.
    - ActivityResultCallback : ActivityResultContract에 정의된 출력 유형의 객체를 가져오는 onActivityResult()가 포함된 단일 메소드 인터페이스
    - 입력이 있으면 ActivityResultLauncher는 ActivityResultContract 유형과 일치하는 입력을 가져옴.
      - lanuch()를 호출하면 결과를 생성하는 프로세스가 시작됨. 사용자가 후속 활동을 완료하고 반환하면 ActivityResultCallback의 onActivityResult()가 실행됨.
    - StartActivityForResult를 사용 => Intent를 입력으로 가져오고, ActivityResult를 반환 => resultCode와 Intent를 콜백의 일부로 추출할 수 있음.

- ```kotlin
  private val signUpActivityLauncher = registerForActivityResult(
      ActivityResultContracts.StartActivityForResult()
  ) {
    // 여기서 it은 ActivityResult
      setIdPwAfterSignUp(it)
  }
  
  private fun setIdPwAfterSignUp(activityResult: ActivityResult) {
          if (activityResult.resultCode == RESULT_OK) {
              signInBinding.signInEdtId.setText(activityResult.data!!.getStringExtra("id"))
              signInBinding.signInEdtPw.setText(activityResult.data!!.getStringExtra("pw"))
            // 여기서 data는 ActivityResult 클래스에서의 getData()로 받아온 mdata인데 intent임.
          }
      }
  
  private fun setOnSignUpBtnClick() {
          signInBinding.signInBtnSignUp.setOnClickListener {
              signUpActivityLauncher.launch(Intent(this, SignUpActivity::class.java))
          }
      }
  ```

<div>
  <img width="430" alt="스크린샷 2021-04-11 오후 5 54 10" src="https://user-images.githubusercontent.com/68374234/114298095-9f92ee80-9aef-11eb-9000-cc46b0f4b48a.png">
  <img width="271" alt="스크린샷 2021-04-11 오후 5 53 48" src="https://user-images.githubusercontent.com/68374234/114298106-af123780-9aef-11eb-8349-fd8b502b5672.png">
</div>
