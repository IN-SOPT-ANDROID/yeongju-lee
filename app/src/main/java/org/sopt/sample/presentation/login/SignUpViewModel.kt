package org.sopt.sample.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    val id = MutableLiveData<String>()
    val pwd = MutableLiveData<String>()
    val mbti = MutableLiveData<String>()

    private val _successSignUp = MutableLiveData<Boolean>()
    val successSignUp: LiveData<Boolean> = _successSignUp

    fun signUpOnClick() {
        viewModelScope.launch {
            _successSignUp.value =
                id.value?.length in 6..10 && pwd.value?.length in 8..12 && mbti.value?.length == 4
        }
    }
}
