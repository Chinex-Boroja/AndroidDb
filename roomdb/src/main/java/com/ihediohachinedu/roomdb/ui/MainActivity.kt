package com.ihediohachinedu.roomdb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihediohachinedu.roomdb.R
import com.ihediohachinedu.roomdb.adapter.PersonalDataAdapter
import com.ihediohachinedu.roomdb.databinding.ActivityMainBinding
import com.ihediohachinedu.roomdb.db.PersonalData
import com.ihediohachinedu.roomdb.db.PersonalDataDb
import com.ihediohachinedu.roomdb.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var viewModel: MainViewModel? = null
    private var personalAdapter: PersonalDataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        var dataBaseInstance = PersonalDataDb.getDatabaseInstance(this)
        viewModel?.setInstanceOfDb(dataBaseInstance)
        initViews()
        setListeners()
        observerViewModel()
    }
    private fun initViews() {
        binding.rvSavedRecords.layoutManager= LinearLayoutManager(this)
        personalAdapter = PersonalDataAdapter(){
            it.let {
                viewModel?.deletePerson(it)
            }
        }
        binding.rvSavedRecords.adapter = personalAdapter
    }

    private fun observerViewModel() {
        viewModel?.personsList?.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                handleData(it)
            } else {
                handleZeroCase()
            }
        })
    }

    private fun handleData(data: List<PersonalData>) {
        binding.rvSavedRecords.visibility = View.VISIBLE
        personalAdapter?.setData(data)
    }
    private fun handleZeroCase() {
        binding.rvSavedRecords.visibility = View.GONE
        Toast.makeText(this,"No Records Found",Toast.LENGTH_LONG).show()
    }

    private fun setListeners() {
        binding.saveBtn.setOnClickListener {
            saveData()
        }

        binding.retrieveBtn.setOnClickListener {
            viewModel?.getPersonalData()
        }
    }

    private fun saveData() {
        var name = binding.edtName.text.trim().toString()
        var age = binding.edtAge.text.trim().toString()
        binding.edtName.setText("")
        binding.edtAge.setText("")
        if (name.isBlank() || age.isBlank()) {
            Toast.makeText(this, "Please enter valid details", Toast.LENGTH_LONG).show()
        } else {

            var person = PersonalData(fullName = name, ageTotal = age.toInt())
            viewModel?.saveDataIntoDb(person)

        }
    }

}