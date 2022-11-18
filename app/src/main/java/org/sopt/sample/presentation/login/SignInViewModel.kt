package org.sopt.sample.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.data.entity.User
import org.sopt.sample.data.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    val inputId = MutableLiveData<String>()
    val inputPwd = MutableLiveData<String>()
    val isAutoLogin = MutableLiveData(false)

    private val _successLogin = MutableLiveData<Boolean>()
    val successLogin: LiveData<Boolean> = _successLogin

    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User> = _userInfo

    init {
        getAutoLogin()
    }

    fun loginOnClick() {
        viewModelScope.launch {
            postSignIn(requireNotNull(inputId.value), requireNotNull(inputPwd.value))
        }
    }

    private fun postSignIn(id: String, pwd: String) {
        viewModelScope.launch {
            authRepository.postSignIn(id, pwd)
                .onSuccess { response ->
                    if (isAutoLogin.value!!) {
                        authRepository.setAutoLogin(isAutoLogin = true)
                    }
                    authRepository.setUserInfo(response.result)
                    _successLogin.value = true
                    _userInfo.value = response.result
                }.onFailure {
                    _successLogin.value = false
                }
        }
    }

    private fun getAutoLogin() {
        viewModelScope.launch {
            if (authRepository.getAutoLogin()) {
                _successLogin.value = true
            }
        }
    }
}
