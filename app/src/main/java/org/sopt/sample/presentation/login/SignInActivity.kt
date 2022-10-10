package org.sopt.sample.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.entity.User
import org.sopt.sample.presentation.home.MainActivity
import org.sopt.sample.util.base.BaseActivity
import org.sopt.sample.util.extensions.showSnackbar
import org.sopt.sample.util.extensions.showToast

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private val signInViewModel: SignInViewModel by viewModels()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var userInfo: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = signInViewModel
        setSignUpResult()
        observeSucessLogin()
        initSignUpBtnOnClickListener()
    }

    private fun setSignUpResult() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    userInfo = result.data?.getSerializableExtra(USER_INFO) as User
                }
                signInViewModel.setUserInfo(userInfo)
                binding.root.showSnackbar(getString(R.string.complete_sign_up))
            }
    }

    private fun initSignUpBtnOnClickListener() {
        binding.btnSignUp.setOnClickListener {
            val toSignUp = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(toSignUp)
            signInViewModel.inputId.value = null
            signInViewModel.inputPwd.value = null
            signInViewModel.isAutoLogin.value = false
        }
    }

    private fun observeSucessLogin() {
        signInViewModel.successLogin.observe(this) { success ->
            if (success) {
                showToast(getString(R.string.success_login, signInViewModel.userInfo.value!!.id))
                val toHome = Intent(this, MainActivity::class.java)
                toHome.putExtra(USER_INFO, signInViewModel.userInfo.value)
                startActivity(toHome)
                finish()
            } else showToast(getString(R.string.fail_login))
        }
    }

    companion object {
        const val USER_INFO = "userInfo"
    }
}
