package org.sopt.sample.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.entity.User
import org.sopt.sample.util.SaveUserInfo
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val saveUserInfo: SaveUserInfo
) : ViewModel() {
    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User> = _userInfo

    val inputId = MutableLiveData<String>()
    val inputPwd = MutableLiveData<String>()
    val isAutoLogin = MutableLiveData<Boolean>()

    private val _successLogin = MutableLiveData<Boolean>()
    val successLogin: LiveData<Boolean> = _successLogin

    init {
        viewModelScope.launch {
            if (saveUserInfo.getAutoLogin()) {
                _userInfo.value = saveUserInfo.getUserInfo()
                _successLogin.value = true
            } else {
                _successLogin.value = false
            }
            Log.d(
                "asdf",
                "SignInViewModel, 자동로그인:${saveUserInfo.getAutoLogin()}, 유저정보:${saveUserInfo.getUserInfo()}"
            )
        }
    }

    fun loginOnClick() {
        viewModelScope.launch {
            if (inputId.value == userInfo.value?.id && inputPwd.value == userInfo.value?.pwd) {
                if (isAutoLogin.value!!) {
                    saveUserInfo.setAutoLogin(isAutoLogin = true)
                    saveUserInfo.setUserInfo(userInfo.value!!)
                }
                _successLogin.value = true
            } else _successLogin.value = false
        }
    }

    fun setUserInfo(user: User) {
        _userInfo.value = user
    }
}
