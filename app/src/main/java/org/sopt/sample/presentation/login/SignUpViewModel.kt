package org.sopt.sample.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    val inputId = MutableLiveData<String>()
    val inputPwd = MutableLiveData<String>()
    val inputMbti = MutableLiveData<String>()

    private val _successSignUp = MutableLiveData<Boolean>()
    val successSignUp: LiveData<Boolean> = _successSignUp

    fun signUpOnClick() {
        viewModelScope.launch {
            _successSignUp.value =
                (inputId.value?.length in 6..10) && (inputPwd.value?.length in 8..12) && (inputMbti.value?.length == 4)
        }
    }
}
