package org.sopt.sample.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.data.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    val inputId = MutableLiveData("")
    val inputPwd = MutableLiveData("")
    val inputName = MutableLiveData("")

    val isValidId: LiveData<Boolean> = Transformations.map(inputId) { inputId -> checkId(inputId) }
    val isValidPwd: LiveData<Boolean> =
        Transformations.map(inputPwd) { inputPwd -> checkPwd(inputPwd) }

    private val _successSignUp = MutableLiveData<Boolean>()
    val successSignUp: LiveData<Boolean> = _successSignUp

    fun signUpOnClick() {
        postSignUp(
            requireNotNull(inputId.value),
            requireNotNull(inputPwd.value),
            requireNotNull(inputName.value)
        )
    }

    private fun postSignUp(id: String, pwd: String, name: String) {
        viewModelScope.launch {
            authRepository.postSignUp(id, pwd, name)
                .onSuccess {
                    _successSignUp.value = true
                }.onFailure {
                    _successSignUp.value = false
                }
        }
    }

    private fun checkId(id: String): Boolean {
        return id.matches("^(?=.*[a-zA-Z])(?=.*[0-9]).{6,10}\$".toRegex()) || id.isEmpty()
    }

    private fun checkPwd(pwd: String): Boolean {
        return pwd.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*]).{6,12}\$".toRegex()) || pwd.isEmpty()
    }
}
