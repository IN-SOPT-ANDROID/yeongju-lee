package org.sopt.sample.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val id = MutableLiveData<String>()
    val mbti = MutableLiveData<String>()
}
