package org.sopt.sample.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.sample.entity.User

class SignInViewModel : ViewModel() {
    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User> = _userInfo

    val id = MutableLiveData("")
    val pwd = MutableLiveData("")

    private val _successLogin = MutableLiveData<Boolean>()
    val successLogin: LiveData<Boolean> = _successLogin

    fun loginOnClick() {
        viewModelScope.launch {
            _successLogin.value =
                id.value == userInfo.value?.id && pwd.value == userInfo.value?.pwd
        }
    }

    fun setUserInfo(user: User) {
        _userInfo.value = user
    }
}
