# ๐ฉAdditional Task

### ๐ฅ์คํ ์์

<div>
  <img height="400" src="https://user-images.githubusercontent.com/68374234/121702272-b2846800-cb0c-11eb-8738-af3ceed31a81.gif">
  <img height="400" src="https://user-images.githubusercontent.com/68374234/121702414-d21b9080-cb0c-11eb-8858-047c536f238f.gif">
</div>



### โ๏ธ๊ณผ์  ํํฉ

- 4th Week 2-1 Github Api ์ฌ์ฉ ์๋ฒ ์ฐ๊ฒฐ

  - Github Repository

  - Github User ์ ๋ณด

  - Github Following, Follower

  - Github Organization

    ```kotlin
    interface GitHubService {
        @GET("/users/{username}")
        suspend fun getUserInfo(
            @Path("username") username: String
        ): ResponseUserInfo
    
        @GET("/users/{username}/repos")
        suspend fun getRepository(
            @Path("username") username: String
        ): List<ResponseRepository>
    
        @GET("/users/{username}/orgs")
        suspend fun getOrganization(
            @Path("username") username: String
        ): List<ResponseOrganization>
    
        @GET("/users/{username}/followers")
        suspend fun getFollowers(
            @Path("username") username: String
        ): List<ResponseFollow>
    
        @GET("/users/{username}/following")
        suspend fun getFollowings(
            @Path("username") username: String
        ): List<ResponseFollow>
    }
    ```



# ๐ฉ7th Seminar

### ๐ฅ์คํ ์์

<div>
  <img height="400" src="https://user-images.githubusercontent.com/68374234/121384701-77a2f880-c983-11eb-9413-d23b9ddd892c.gif">
</div>



### โ๏ธ๊ณผ์  ํํฉ

- Step1

  - SharedPreference ์ฌ์ฉํด์ ์๋๋ก๊ทธ์ธ ๊ตฌํํ๊ธฐ

    - SharedPreference

    - ์๋๋ก๊ทธ์ธ ๊ตฌํ

      ```kotlin
      override fun onCreate(savedInstanceState: Bundle?) {
              super.onCreate(savedInstanceState)
              setAutoSignIn()
              setIsSignInObserve()
      }
      
      private fun setAutoSignIn() {
          if (UserAuthStorage.hasUserData()) {
      			signInViewModel.autoSetUserInfo(UserAuthStorage.getUserId(), UserAuthStorage.getUserPw())
      			signInViewModel.checkIsNotNull()
      		}
      }
      private fun setIsSignInObserve() {
          signInViewModel.isSignIn.observe(this) { isSignIn ->
              if (isSignIn) {
                  UserAuthStorage.saveUserIdPw(
                      requireNotNull(signInViewModel.email.value),
                      requireNotNull(signInViewModel.password.value)
                  )
                  toastMessageUtil("${signInViewModel.nickname.value}๋ ๋ก๊ทธ์ธ๋์์ต๋๋ค.")
                  startActivity(Intent(this, HomeActivity::class.java))
                  finish()
              } else {
                  toastMessageUtil("์์ด๋/๋น๋ฐ๋ฒํธ๋ฅผ ํ์ธํด์ฃผ์ธ์!")
              }
          }
      }
      ```

  - ๋ก๊ทธ์์ ์ SharedPreference clearํ๊ธฐ

    - SharedPreference ์ญ์ 

    - ๋ก๊ทธ์์

      ```kotlin
      // UserFragment.kt
      private fun setOnLogOutBtnClick() {
          binding.btnUserLogout.setOnClickListener {
              UserAuthStorage.clearData()
              userInfoViewModel.setIsLogout(true)
          }
      }
      
      //UserInfoActivity.kt
      private fun setIsLogoutObserve() {
      		userInfoViewModel.isLogout.observe(this) { isLogout ->
      				if (isLogout) {
                      val intent = Intent(this, SignInActivity::class.java)
                      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                      startActivity(intent)
                      Toast.makeText(this, "๋ก๊ทธ์์๋์์ต๋๋ค.", Toast.LENGTH_LONG).show()
                      finish()
      				}
      		}
      }
      ```



# ๐ฉ4th Seminar

### ๐ฅ์คํ ์์

<div>
  <img height="400" src="https://user-images.githubusercontent.com/68374234/118389227-d49cde80-b663-11eb-8f0e-2d2573e649f4.gif">
</div>



### ๐ทPostman Test

<div>
  SignUp๐
  <img height="400" src="https://user-images.githubusercontent.com/68374234/118389234-dbc3ec80-b663-11eb-8169-cf8d168e3c19.png">
  SignIn๐
  <img height="400" src="https://user-images.githubusercontent.com/68374234/118389232-db2b5600-b663-11eb-91e6-b4738611e361.png">
</div>



### โ๏ธ๊ณผ์  ํํฉ

- Step1 - ๋ก๊ทธ์ธ/ํ์๊ฐ์ ํต์  ๊ตฌํํ๊ธฐ

  - RetrofitBuilder : Retrofit Interface ๊ตฌํ์ฒด

    ```kotlin
    object RetrofitBuilder {
        private const val BASE_URL = "http://cherishserver.com"
    
        private val loginRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    
        val loginService: LoginService = loginRetrofit.create(LoginService::class.java)
    }
    ```

  - LoginService : ํ์๊ฐ์, ๋ก๊ทธ์ธ ์๋ฒ ์์ฒญ ๋์ ์ ์
    => ๋ค์ ํจ์๋ฅผ viewModelScope ๋ด์์ ์ฌ์ฉํ๊ธฐ ์ํด suspend modifier ์ฌ์ฉ.

    ```kotlin
    interface LoginService {
        @POST("/login/signin")
        suspend fun postSignIn(
            @Body requestSignIn: RequestSignIn
        ): ResponseSignIn
    
        @POST("/login/signup")
        suspend fun postSignUp(
            @Body requestSignUp: RequestSignUp
        ): ResponseSignUp
    }
    ```

  - SignUpViewModel ์์ ์ด๋ฆ, ์์ด๋, ๋น๋ฐ๋ฒํธ๋ฅผ ๋ชจ๋ ์๋ ฅํ์ ์(databinding๊ณผ observeํตํด์ null์ฒดํฌ) ์๋ฒํต์ ์์ฒญ
    => Dispatchers๋ฅผ IO๋ก ์ค์ ํ  ๊ฒฝ์ฐ, livedata์ ๊ฐ ์ค์ ์ postValue์ ์ด์ฉํ์ฌ ์ค์ .

    ```kotlin
    fun requestSignUp() = viewModelScope.launch {
        try {
            RetrofitBuilder.loginService.postSignUp(
                RequestSignUp(
                    birth = "none",
                    email = email.value!!,
                    nickname = nickname.value!!,
                    password = password.value!!,
                    phone = "none",
                    sex = "none"
                )
            )
            _isSignUp.postValue(true)
        } catch (e: HttpException) {
            _isSignUp.postValue(false)
        }
    }
    ```

  - SignInViewModel ์์ ์์ด๋, ๋น๋ฐ๋ฒํธ๋ฅผ ๋ชจ๋ ์๋ ฅํ์ ์(databinding๊ณผ observeํตํด์ null์ฒดํฌ) ์๋ฒํต์ ์์ฒญ
    => Dispatchers๋ฅผ IO๋ก ์ค์ ํ  ๊ฒฝ์ฐ, livedata์ ๊ฐ ์ค์ ์ postValue์ ์ด์ฉํ์ฌ ์ค์ .

    ```kotlin
    fun requestSignIn() = viewModelScope.launch {
        try {
            val responseSignIn = RetrofitBuilder.loginService.postSignIn(
                RequestSignIn(
                    email = email.value!!,
                    password = password.value!!
                )
            )
            _nickname.postValue(responseSignIn.data.userNickname)
            _isSignIn.postValue(true)
        } catch (e: HttpException) {
            _isSignIn.postValue(false)
        }
    }
    ```



# ๐ฉ2nd Seminar


### ๐ฅ์คํ ์์

<div>
  <img height="400" src="https://user-images.githubusercontent.com/68374234/115982289-daf3e980-a5d4-11eb-8788-ab7f2593c57a.gif">
</div>





### โ๏ธ๊ณผ์  ํํฉ

- Step1

  - Repo RecyclerView ๊ตฌํ

    ```kotlin
    private fun setRepoRcvAdapter() {
        binding.homeRcvRepository.adapter = RepoRcvAdapter()
    }
    
    private fun setRepoListObserver() {
        homeViewModel.repoList.observe(this) { repoList ->
            repoList.let {
                if (binding.homeRcvRepository.adapter != null) with(binding.homeRcvRepository.adapter as RepoRcvAdapter) {
                    submitList(repoList)
                }
            }
        }
    }
    ```

  - RecyclerView ์์ดํ ๋ด๋ถ repo name, repo info ๊ธด ๊ฒฝ์ฐ์ ... ๋์ค๋๋ก ์ฒ๋ฆฌ

    ```xml
    android:ellipsize="end"
    android:maxLines="1"
    ```

- Step2

  - GridLayout์ ์ ์ฉํ RecyclerView ๊ตฌํ(์์งx)
  - ์ฌ๋ฌ ViewHolder ๋ง๋ค์ด RecyclerView ์์ 2๊ฐ์ง์ด์์ ๋ทฐ ๋ณด์ฌ์ฃผ๊ธฐ(์์งx)
  - RecyclerView Item ๊ธฐ๋ฅ ๊ตฌํํ๊ธฐ(์์ง x)

- Step3

  - notifyDataSetChanged๊ฐ ํ๋ ์ผ(์์ง x)



# ๐ฉ1st Seminar


### ๐ฅ์คํ ์์

<div>
  Login๐
  <img height="400" src="https://user-images.githubusercontent.com/68374234/114338500-25657700-9b8e-11eb-997d-0d8a2c03121c.gif">  
  SignUp๐
  <img height="400" src="https://user-images.githubusercontent.com/68374234/114338507-28606780-9b8e-11eb-928c-e41f87f5902a.gif">
  Bonus๐
  <img height="400" src="https://user-images.githubusercontent.com/68374234/114338513-2ac2c180-9b8e-11eb-8554-adf2f80ff6c2.gif">
</div>



### โ๏ธ๊ณผ์  ํํฉ

- Step1
  - ํ๋ฉด ์ ํ(o)
  - ์กํฐ๋นํฐ ๊ฐ ๋ฐ์ดํฐ ์ ๋ฌ(o)
- Step2
  - ๋ณ์์ด๋ฆ์ฒดํฌ(o)
  - chain์ฌ์ฉ(o)
- Step3
  - ViewBinding ์ด๋ฆ์ ๋ป์ด๋ญ๊น์?โจโจ 
  - ๊ฐ์ฒด์งํฅ ์ด๋์ ๋ ๋ค๋ค๋ณด์จ๋์?โจโจ
  - ์ํคํ์ณ๋ผ๊ณ  ๋ค์ด๋ณด์จ๋์?โจโจ




### ๐ญstrartActivityForResult(), registerForActivityResult()

- <a href = "https://developer.android.com/training/basics/intents/result?hl=ko"> ๊ณต์ย ๋ฌธ์ย ๋ณด๋ฌ๊ฐ๊ธฐ</a>

- startActivity()์ startActivityForResult() ๋ชจ๋ ํ๋ฉด ์ ํ์ ์ฌ์ฉ๋๋๋ฐ ์ฐจ์ด์ ์?

  - startActivity() : ์์ ์กํฐ๋นํฐ์์์ ๊ฒฐ๊ณผ๋ฅผ ๋ถ๋ชจ ์กํฐ๋นํฐ์์ ์ฒ๋ฆฌํ์ง ์์๋ ๋  ๋ ์ฌ์ฉ.
  - startActivityForResult() : ์์ ์กํฐ๋นํฐ์์์ ๊ฒฐ๊ณผ๋ฅผ ๋ถ๋ชจ ์กํฐ๋นํฐ์์ ์ฒ๋ฆฌํ  ๋ ์ฌ์ฉ.

- onActivityResult()์์ startActivityForResult()๋ฅผ ํตํด ๋ฐ์์จ ๊ฐ๋ค์ ์ฒ๋ฆฌํด์คฌ์๋๋ฐ ์ด์  ์ฌ๋ผ์ง๋ค๊ณ  ํจ...

- ๊ทธ๋์ registerForActivityResult()์์ ์ฒ๋ฆฌ.

  - ๊ธฐ์กด์ startActivityForResult()๋ฅผ ์ฌ์ฉํ๋ ค๋ฉด Request Code์ Result Code๊ฐ ํ์ํ์.
  ๊ฐ ์กํฐ๋นํฐ๋ง๋ค RequestCode๋ฅผ ๋ถ์ฌํด์คฌ์ด์ผํ๊ณ  onActivityResult์์ ์กฐ๊ฑด์ ํ์ธํด์ ์ฒ๋ฆฌํด์ผํ์ => ์ฝ๋ ์ง์ ๋ถํด์ง...
    - Request Code : ๊ฐ ์กํฐ๋นํฐ์ ๋ถ์ฌํด์ค ๊ฐ.<br>
      โถ ๊ฐ ์กํฐ๋นํฐ๋ง๋ค ๋ค๋ฅด๊ฒ ์ง์  ๊ฐ๋ฅ. ์ด ๊ฐ์ ๋ฐ๋ผ ์ฒ๋ฆฌํ  ๊ฒ์ ๋ฌ๋ฆฌํ  ์ ์์.
    - Result Code : ์กํฐ๋นํฐ๊ฐ ์ ์์ ์ผ๋ก ์๋์ง ํ์ธํด์ฃผ๋ ๊ฐ.<br>
      โถ ์์ ์กํฐ๋นํฐ์์ setResult()๋ฅผ ํตํด ๊ฐ ์ง์  ๊ฐ๋ฅ.
  - registerForActivityResult()์์ ์ฒ๋ฆฌํ๊ฒ๋๋ฉด RequestCode๊ฐ ํ์ ์์.
    - registerForActivityResult()๋ ActivityResultContract ๋ฐ ActivityResultCallback์ ๊ฐ์ ธ์์ ๋ค๋ฅธ ํ๋์ ์คํํ๋๋ฐ ์ฌ์ฉํ  ActivityResultLauncher๋ฅผ ๋ฐํํจ.
    - ActivityResultContract : ๊ฒฐ๊ณผ๋ฅผ ์์ฑํ๋๋ฐ ํ์ํ ์๋ ฅ ์ ํ๊ณผ ๊ฒฐ๊ณผ์ ์ถ๋ ฅ ์ ํ์ ์ ์ํจ.
    - ActivityResultCallback : ActivityResultContract์ ์ ์๋ ์ถ๋ ฅ ์ ํ์ ๊ฐ์ฒด๋ฅผ ๊ฐ์ ธ์ค๋ onActivityResult()๊ฐ ํฌํจ๋ ๋จ์ผ ๋ฉ์๋ ์ธํฐํ์ด์ค
    - ์๋ ฅ์ด ์์ผ๋ฉด ActivityResultLauncher๋ ActivityResultContract ์ ํ๊ณผ ์ผ์นํ๋ ์๋ ฅ์ ๊ฐ์ ธ์ด.
      - lanuch()๋ฅผ ํธ์ถํ๋ฉด ๊ฒฐ๊ณผ๋ฅผ ์์ฑํ๋ ํ๋ก์ธ์ค๊ฐ ์์๋จ. ์ฌ์ฉ์๊ฐ ํ์ ํ๋์ ์๋ฃํ๊ณ  ๋ฐํํ๋ฉด ActivityResultCallback์ onActivityResult()๊ฐ ์คํ๋จ.
      - ์๋์ ์ฝ๋์ฒ๋ผ ๋ด๊ฐ ๊ฒฐ๊ณผ๊ฐ์ ๋ฐ์์ค๊ณ  ์ถ์ Activity๋ณ๋ก Launcher๋ฅผ ์์ฑํ๊ณ  ๊ฐ๊ฐ launch()๋ฅผ ํธ์ถํจ => ๊ฐ๊ฐ์ Launcher์์ ์ด๋ค Activity๋ก ์คํํ๋์ง ์๊ณ ์๊ธฐ๋๋ฌธ์ onActivityResult()์์ ์ฒ๋ฆฌํด์คฌ์๋์ ๋ฌ๋ฆฌ request code๊ฐ ํ์์์.
    - StartActivityForResult๋ฅผ ์ฌ์ฉ => Intent๋ฅผ ์๋ ฅ์ผ๋ก ๊ฐ์ ธ์ค๊ณ , ActivityResult๋ฅผ ๋ฐํ => resultCode์ Intent๋ฅผ ์ฝ๋ฐฑ์ ์ผ๋ถ๋ก ์ถ์ถํ  ์ ์์.

- ```kotlin
  private val signUpActivityLauncher = registerForActivityResult(
      ActivityResultContracts.StartActivityForResult()
  ) {
    // ์ฌ๊ธฐ์ it์ ActivityResult
      setIdPwAfterSignUp(it)
  }
  
  private fun setIdPwAfterSignUp(activityResult: ActivityResult) {
          if (activityResult.resultCode == RESULT_OK) {
              signInBinding.signInEdtId.setText(activityResult.data!!.getStringExtra("id"))
              signInBinding.signInEdtPw.setText(activityResult.data!!.getStringExtra("pw"))
            // ์ฌ๊ธฐ์ data๋ ActivityResult ํด๋์ค์์์ getData()๋ก ๋ฐ์์จ mdata์ธ๋ฐ intent์.
          }
      }
  
  private fun setOnSignUpBtnClick() {
          signInBinding.signInBtnSignUp.setOnClickListener {
              signUpActivityLauncher.launch(Intent(this, SignUpActivity::class.java))
          }
      }
  ```

<div>
  <img width="430" alt="์คํฌ๋ฆฐ์ท 2021-04-11 ์คํ 5 54 10" src="https://user-images.githubusercontent.com/68374234/114298095-9f92ee80-9aef-11eb-9000-cc46b0f4b48a.png">
  <img width="271" alt="์คํฌ๋ฆฐ์ท 2021-04-11 ์คํ 5 53 48" src="https://user-images.githubusercontent.com/68374234/114298106-af123780-9aef-11eb-8349-fd8b502b5672.png">
</div>



### ๐ญ์๋ช์ฃผ๊ธฐ Log

<img width="648" alt="์คํฌ๋ฆฐ์ท 2021-04-12 ์ค์  1 10 21" src="https://user-images.githubusercontent.com/68374234/114311962-de469a00-9b2b-11eb-98c0-d7fa926909d1.png">

