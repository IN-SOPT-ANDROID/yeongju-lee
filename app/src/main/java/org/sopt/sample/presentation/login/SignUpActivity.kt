package org.sopt.sample.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.entity.User
import org.sopt.sample.util.base.BaseActivity
import org.sopt.sample.util.extensions.showSnackbar

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = signUpViewModel
        observeSuccessSignUp()
        initBackBtnOnClickListener()
    }

    private fun observeSuccessSignUp() {
        signUpViewModel.successSignUp.observe(this) { success ->
            if (success) {
                putUserInfo()
                finish()
            } else binding.root.showSnackbar(getString(R.string.check_your_input))
        }
    }

    private fun putUserInfo() {
        val toSignIn = Intent(this, SignInActivity::class.java)
        val userInfo = User(
            id = signUpViewModel.id.value,
            pwd = signUpViewModel.pwd.value,
            mbti = signUpViewModel.mbti.value
        )
        toSignIn.putExtra("userInfo", userInfo)
        setResult(RESULT_OK, toSignIn)
    }

    private fun initBackBtnOnClickListener() {
        binding.ivSignUpBack.setOnClickListener {
            finish()
        }
    }
}
