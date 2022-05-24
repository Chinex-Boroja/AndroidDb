package com.ihediohachinedu.accessimageapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ihediohachinedu.accessimageapp.R
import com.ihediohachinedu.accessimageapp.databinding.ActivityUserDetailsBinding
import com.ihediohachinedu.accessimageapp.viewmodel.DataStoreViewModel
import com.ihediohachinedu.accessimageapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsBinding

    private lateinit var dataStoreViewModel: DataStoreViewModel
    private lateinit var userViewModel: UserViewModel
//    private val userViewModel: UserViewModel by viewModels()
//    private val dataStoreViewModel: DataStoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        dataStoreViewModel = ViewModelProvider(this, factory).get(DataStoreViewModel::class.java)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)


        getUserDetails()

        handleClicks()

    }

    private fun handleClicks(){

        binding.btnClearRecord.setOnClickListener {

            //clear record from room database
            userViewModel.doDeleteSingleUserRecord()

            //remove the datastorage key
            dataStoreViewModel.setSavedKey(false)

            //go to main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }

    private fun getUserDetails(){

        this.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){

                userViewModel.doGetUserDetails()
                userViewModel.userDetails.collect { users->

                    for (user in users){
                        //set data into view
                        binding.txtName.text = user.name
                        binding.txtAge.text = user.age
                        binding.txtNumber.text = user.number
                    }

                }
            }
        }

    }

}