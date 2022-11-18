package org.sopt.sample.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.data.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    val inputId = MutableLiveData<String>()
    val inputPwd = MutableLiveData<String>()
    val inputName = MutableLiveData<String>()

    private val _isSignUpRule = MutableLiveData<Boolean>()
    val isSignUpRule: LiveData<Boolean> = _isSignUpRule

    private val _successSignUp = MutableLiveData<Boolean>()
    val successSignUp: LiveData<Boolean> = _successSignUp

    fun signUpOnClick() {
        viewModelScope.launch {
            _isSignUpRule.value =
                (inputId.value?.length in 6..10) && (inputPwd.value?.length in 8..12)
            if (_isSignUpRule.value == true) {
                postSignUp(
                    requireNotNull(inputId.value),
                    requireNotNull(inputPwd.value),
                    requireNotNull(inputName.value)
                )
            } else {
                return@launch
            }
        }
    }

    private fun postSignUp(id: String, pwd: String, name: String) {
        viewModelScope.launch {
            authRepository.postSignUp(
                requireNotNull(inputId.value),
                requireNotNull(inputPwd.value),
                requireNotNull(inputName.value)
            ).onSuccess {
                _successSignUp.value = true
                Log.e("asdf", it.message + it.newUser)
            }.onFailure { throwable ->
                _successSignUp.value = false
                Log.e("asdf", throwable.message.toString())
            }
        }
    }
}
