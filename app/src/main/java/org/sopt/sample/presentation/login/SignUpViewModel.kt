package org.sopt.sample.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    private val _pwd = MutableLiveData<String>()
    val pwd: LiveData<String> = _pwd

    private val _mbti = MutableLiveData<String>()
    val mbti: LiveData<String> = _mbti

    private val _successSignUp = MutableLiveData<Boolean>()

    fun signUpOnClick() {
        viewModelScope.launch {
            //if (id.value?.length in 6..10)
        }
    }
}
