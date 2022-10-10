package org.sopt.sample.presentation.home

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
class MainViewModel @Inject constructor(
    private val saveUserInfo: SaveUserInfo
) : ViewModel() {
    val id = MutableLiveData<String>()
    val mbti = MutableLiveData<String>()

    private val _logout = MutableLiveData<Boolean>()
    val logout: LiveData<Boolean> = _logout

    fun logoutOnClick() {
        viewModelScope.launch {
            saveUserInfo.setAutoLogin(isAutoLogin = false)
            saveUserInfo.setUserInfo(
                User(
                    id = null,
                    pwd = null,
                    mbti = null
                )
            )
            _logout.value = true
        }
    }
}
