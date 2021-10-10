package com.example.yash_coding_task.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// This view model is getting shared in LoginScreenFragment and HomeScreenFragment
class SharedViewModel : ViewModel() {
    private var usernameLiveData = MutableLiveData<String>()

    // Getting called from HomeScreenFragment
    fun getUserName(): LiveData<String> {
        return usernameLiveData
    }

    // Setting data from LoginScreenFragment
    fun setLoginDetails(username: String) {
        usernameLiveData.value = username
    }
}
