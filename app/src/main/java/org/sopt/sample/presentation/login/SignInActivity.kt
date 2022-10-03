package org.sopt.sample.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.presentation.home.HomeActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private var userId: String? = null
    private var userPwd: String? = null
    private var userMbti: String? = null
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                Snackbar.make(binding.root, "회원가입이 완료되었습니다.", Snackbar.LENGTH_SHORT).show()
                userId = result.data?.getStringExtra("userId") ?: ""
                userPwd = result.data?.getStringExtra("userPwd") ?: ""
                userMbti = result.data?.getStringExtra("userMbti") ?: ""
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoginBtnOnClickListener()
        initSignUpBtnOnClickListener()
    }

    private fun isCheckLogin(): Boolean {
        return binding.etSignInId.text.toString() == userId && binding.etSignInPwd.text.toString() == userPwd
    }

    private fun initSignUpBtnOnClickListener() {
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
            binding.etSignInId.text = null
            binding.etSignInPwd.text = null
        }
    }

    private fun initLoginBtnOnClickListener() {
        binding.btnLogin.setOnClickListener {
            if (isCheckLogin()) {
                Toast.makeText(this, "로그인에 성공하셨습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                putUserInfo(intent)
                startActivity(intent)
                finish()
            } else Toast.makeText(this, "다시 입력하세요.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun putUserInfo(intent: Intent) {
        intent.putExtra("userId", userId)
        intent.putExtra("userMbti", userMbti)
        setResult(RESULT_OK, intent)
    }
}
