package org.sopt.sample.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.entity.User
import org.sopt.sample.util.binding.BaseActivity

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = signUpViewModel
        observeSuccessSignUp()
    }

    private fun observeSuccessSignUp() {
        signUpViewModel.successSignUp.observe(this) { success ->
            if (success) {
                putUserInfo()
                finish()
            } else Snackbar.make(binding.root, "입력한 정보를 확인해주세요.", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun putUserInfo() {
        val intent = Intent(this, SignInActivity::class.java)
        val userInfo = User(
            id = signUpViewModel.id.value,
            pwd = signUpViewModel.pwd.value,
            mbti = signUpViewModel.mbti.value
        )
        intent.putExtra("userInfo", userInfo)
        setResult(RESULT_OK, intent)
    }
}
