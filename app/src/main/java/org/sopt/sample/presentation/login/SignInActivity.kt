package org.sopt.sample.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.entity.User
import org.sopt.sample.presentation.home.HomeActivity
import org.sopt.sample.util.binding.BaseActivity
import org.sopt.sample.util.showToast

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private val signInViewModel: SignInViewModel by viewModels()
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                Snackbar.make(binding.root, "회원가입이 완료되었습니다.", Snackbar.LENGTH_SHORT).show()
                val userInfo = result.data?.getSerializableExtra("userInfo") as User
                signInViewModel.setUserInfo(userInfo)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = signInViewModel
        observeSucessLogin()
        initSignUpBtnOnClickListener()
    }

    private fun initSignUpBtnOnClickListener() {
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
            binding.etSignInId.text = null
            binding.etSignInPwd.text = null
        }
    }

    private fun observeSucessLogin() {
        signInViewModel.successLogin.observe(this) { success ->
            if (success) {
                showToast("로그인에 성공하셨습니다.")
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("userInfo", signInViewModel.userInfo.value)
                startActivity(intent)
                finish()
            } else showToast("다시 입력하세요.")
        }
    }
}
