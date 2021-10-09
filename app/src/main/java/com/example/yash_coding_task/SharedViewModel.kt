package com.example.yash_coding_task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// This view model is getting shared in LoginScreenFragment and HomeScreenFragment
class SharedViewModel : ViewModel() {
    private var usernameLiveData = MutableLiveData<String>()
    private var passwordLiveData = MutableLiveData<String>()

    // Getting called from HomeScreenFragment
    fun getUserName(): LiveData<String> {
        return usernameLiveData
    }

    fun getPassword(): LiveData<String> {
        return passwordLiveData
    }

    // Setting data from LoginScreenFragment
    fun setLoginDetails(username: String, password: String) {
        usernameLiveData.value = username
        passwordLiveData.value = password
    }
}