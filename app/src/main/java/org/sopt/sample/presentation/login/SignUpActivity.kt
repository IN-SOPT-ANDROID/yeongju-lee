package org.sopt.sample.presentation.login

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.util.binding.BaseActivity

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSignUpBtnOnClickListener()
    }

    private fun initSignUpBtnOnClickListener() {
        binding.btnSignUp.setOnClickListener {
            if (checkId() && checkPwd() && checkMbti()) {
                putUserInfo()
                finish()
            } else Snackbar.make(binding.root, "입력한 정보를 확인해주세요.", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun putUserInfo() {
        val intent = Intent(this, SignInActivity::class.java)
        intent.putExtra("userId", binding.etSignUpId.text.toString())
        intent.putExtra("userPwd", binding.etSignUpPwd.text.toString())
        intent.putExtra("userMbti", binding.etSignUpMbti.text.toString())
        setResult(RESULT_OK, intent)
    }

    private fun checkId(): Boolean {
        return binding.etSignUpId.text.length in 6..10
    }

    private fun checkPwd(): Boolean {
        return binding.etSignUpPwd.text.length in 8..12
    }

    private fun checkMbti(): Boolean {
        return binding.etSignUpMbti.text.length == 4
    }
}
