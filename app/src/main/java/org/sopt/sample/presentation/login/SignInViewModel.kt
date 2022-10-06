package org.sopt.sample.presentation.login

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.sopt.sample.entity.User

class SignInViewModel : ViewModel() {
    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User> = _userInfo

    val id = MutableLiveData("")
    val pwd = MutableLiveData("")
    val isAutoLogin = MutableLiveData<Boolean>()

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
