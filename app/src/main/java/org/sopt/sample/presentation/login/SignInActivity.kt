package org.sopt.sample.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.presentation.main.MainActivity
import org.sopt.sample.util.binding.BindingActivity
import org.sopt.sample.util.extensions.showToast

@AndroidEntryPoint
class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = signInViewModel
        initSuccessLoginObserve()
        initSignUpBtnOnClickListener()
    }

    private fun initSignUpBtnOnClickListener() {
        binding.btnSignUp.setOnClickListener {
            val toSignUp = Intent(this, SignUpActivity::class.java)
            startActivity(toSignUp)
            signInViewModel.inputId.value = null
            signInViewModel.inputPwd.value = null
            signInViewModel.isAutoLogin.value = false
        }
    }

    private fun initSuccessLoginObserve() {
        signInViewModel.successLogin.observe(this) { success ->
            if (success) {
                showToast(getString(R.string.success_login))
                val toHome = Intent(this, MainActivity::class.java)
                startActivity(toHome)
                finish()
            } else showToast(getString(R.string.fail_login))
        }
    }

    companion object {
        const val USER_INFO = "userInfo"
    }
}
