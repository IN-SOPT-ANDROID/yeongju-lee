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

    init {
        getAutoLogin()
    }

    fun loginOnClick() {
        postSignIn(requireNotNull(inputId.value), requireNotNull(inputPwd.value))
    }

    private fun postSignIn(id: String, pwd: String) {
        viewModelScope.launch {
            authRepository.postSignIn(id, pwd)
                .onSuccess { response ->
                    setAutoLogin(requireNotNull(isAutoLogin.value))
                    setUserInfo(response.result)
                    _successLogin.value = true
                }.onFailure {
                    _successLogin.value = false
                }
        }
    }

    private fun setAutoLogin(isAutoLogin: Boolean) {
        if (isAutoLogin) {
            authRepository.setAutoLogin(isAutoLogin = true)
        }
    }

    private fun setUserInfo(user: User) {
        authRepository.setUserInfo(user)
    }

    private fun getAutoLogin() {
        if (authRepository.getAutoLogin()) {
            _successLogin.value = true
        }
    }
}
