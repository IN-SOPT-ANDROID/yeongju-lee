package org.sopt.sample.presentation.home.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.data.entity.Follower
import org.sopt.sample.data.entity.User
import org.sopt.sample.data.repository.AuthRepository
import org.sopt.sample.data.repository.ReqResRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val reqResRepository: ReqResRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User> = _userInfo

    private val _followerInfo = MutableLiveData<List<Follower>>()
    val followerInfo: LiveData<List<Follower>> = _followerInfo

    private val _logout = MutableLiveData<Boolean>()
    val logout: LiveData<Boolean> = _logout

    init {
        getFollower()
    }

    private fun getFollower() {
        viewModelScope.launch {
            reqResRepository.getFollower()
                .onSuccess { response ->
                    _followerInfo.value = response.data
                    _userInfo.value = authRepository.getUserInfo()
                    Log.e("asdf", authRepository.getUserInfo().toString())
                    Log.e("asdf", _userInfo.value.toString())
                    Log.e("asdf", response.data.toString())
                }
        }
    }

    fun logoutOnClick() {
        viewModelScope.launch {
            authRepository.setAutoLogin(isAutoLogin = false)
            authRepository.setUserInfo(
                User(
                    id = -1,
                    name = "",
                    profileImage = null,
                    bio = null,
                    email = "",
                    password = ""
                )
            )
            _logout.value = true
        }
    }
}
