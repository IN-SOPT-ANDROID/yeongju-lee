package org.sopt.sample.presentation.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.preference.PreferenceManager
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.entity.User
import org.sopt.sample.presentation.home.HomeActivity
import org.sopt.sample.util.base.BaseActivity
import org.sopt.sample.util.extensions.showSnackbar
import org.sopt.sample.util.extensions.showToast

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private val signInViewModel: SignInViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                binding.root.showSnackbar(getString(R.string.complete_sign_up))
                val userInfo = result.data?.getSerializableExtra("userInfo") as User
                signInViewModel.setUserInfo(userInfo)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = signInViewModel
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        editor = sharedPreferences.edit()
        checkSharedPreferences()
        observeSucessLogin()
        initSignUpBtnOnClickListener()
    }

    private fun checkSharedPreferences() {
        signInViewModel.isAutoLogin.value =
            sharedPreferences.getBoolean(getString(R.string.auto_login), false)
        signInViewModel.id.value =
            sharedPreferences.getString(getString(R.string.sign_in_id_hint), "")
        signInViewModel.pwd.value =
            sharedPreferences.getString(getString(R.string.sign_in_pwd_hint), "")
    }

    private fun initSignUpBtnOnClickListener() {
        binding.btnSignUp.setOnClickListener {
            val toSignUp = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(toSignUp)
            signInViewModel.id.value = null
            signInViewModel.pwd.value = null
            signInViewModel.isAutoLogin.value = false
        }
    }

    private fun observeSucessLogin() {
        signInViewModel.successLogin.observe(this) { success ->
            if (success) {
                observeAutoLogin()
                showToast(getString(R.string.success_login))
                val toHome = Intent(this, HomeActivity::class.java)
                toHome.putExtra("userInfo", signInViewModel.userInfo.value)
                startActivity(toHome)
                finish()
            } else showToast(getString(R.string.fail_login))
        }
    }

    private fun observeAutoLogin() {
        signInViewModel.isAutoLogin.observe(this) { checked ->
            if (checked) {
                editor.putBoolean(getString(R.string.auto_login), true)
                editor.apply()
                editor.putString(getString(R.string.sign_in_id_hint), signInViewModel.id.value)
                editor.putString(
                    getString(R.string.sign_in_pwd_hint),
                    signInViewModel.pwd.value
                )
                editor.commit()
            } else {
                editor.putBoolean(getString(R.string.auto_login), false)
                signInViewModel.id.value =
                    sharedPreferences.getString(getString(R.string.sign_in_id_hint), "")
                signInViewModel.pwd.value =
                    sharedPreferences.getString(getString(R.string.sign_in_pwd_hint), "")
                editor.commit()
            }
        }
    }
}
