package com.ihediohachinedu.accessimageapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ihediohachinedu.accessimageapp.model.User
import com.ihediohachinedu.accessimageapp.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class UserViewModel @ViewModelInject constructor(private val userRepo: UserRepo) : ViewModel() {

    /**
     * Insert user details
     */
    private val _response = MutableLiveData<Long>()
    val response: LiveData<Long> = _response

    //insert user details to room database
    fun insertUserDetails(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            _response.postValue(userRepo.createUserRecords(user))
        }
    }

    /**
     * Retrieve user details
     */
    //check if song is liked
    private val _userDetails = MutableStateFlow<List<User>>(emptyList())
    val userDetails : StateFlow<List<User>> =  _userDetails

    fun doGetUserDetails(){
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.getUserDetails
                .catch { e->
                    //Log error here
                }
                .collect {
                    _userDetails.value = it
                }
        }
    }

    /**
     * Delete single user record
     */
    fun doDeleteSingleUserRecord(){
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.deleteSingleUserRecord()
        }
    }

}