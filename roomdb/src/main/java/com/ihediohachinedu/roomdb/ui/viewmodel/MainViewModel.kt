package com.ihediohachinedu.roomdb.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ihediohachinedu.roomdb.db.PersonalData
import com.ihediohachinedu.roomdb.db.PersonalDataDb
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    private var databaseInstance : PersonalDataDb? = null

    var personsList = MutableLiveData<List<PersonalData>>()

    fun setInstanceOfDb(databaseInstance: PersonalDataDb) {
        this.databaseInstance = databaseInstance
    }

    fun saveDataIntoDb(data: PersonalData) {

        databaseInstance?.personalDataDao()?.insertPersonData(data)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({

            },{

            })?.let {
                compositeDisposable.add(it)
            }
    }

    fun getPersonalData() {
        databaseInstance?.personalDataDao()?.getAllRecords()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                if(!it.isNullOrEmpty()){
                    personsList.postValue(it)
                }else{
                    personsList.postValue(listOf())
                }
                it?.forEach {
                    it.fullName?.let { it1 -> Log.v("Person Name", it1) }
                }
            },{

            })?.let {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }

    fun deletePerson(person: PersonalData) {
        databaseInstance?.personalDataDao()?.deletePersonalData(person)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({
                //Refresh Page data
                getPersonalData()
            },{

            })?.let {
                compositeDisposable.add(it)
            }
    }
}