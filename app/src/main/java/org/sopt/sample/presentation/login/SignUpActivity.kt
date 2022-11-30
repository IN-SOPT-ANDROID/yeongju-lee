package org.sopt.sample.presentation.login

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.util.binding.BindingActivity
import org.sopt.sample.util.extensions.showToast

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = signUpViewModel
        initSuccessSignUpObserve()
    }

    private fun initSuccessSignUpObserve() {
        signUpViewModel.successSignUp.observe(this) { success ->
            if (success) {
                showToast(getString(R.string.complete_sign_up))
                finish()
            } else {
                showToast(getString(R.string.fail_sign_up))
            }
        }
    }
}
