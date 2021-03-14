package com.example.section9roomdbdemo

import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.section9roomdbdemo.db.Subcribers
import com.example.section9roomdbdemo.db.SubscriberDaoRepository
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class MainActivityViewModel(private val subscriberRepository: SubscriberDaoRepository) :
    ViewModel(), Observable {

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputMail = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val deleteAllButtonText = MutableLiveData<String>()

    private var isUpdateOrDelete = false

    private lateinit var subscribersUpdateOrDelete: Subcribers

    private var _allSubscriberList = MutableLiveData<List<Subcribers>>()

    val allSubscriberList: LiveData<List<Subcribers>>
        get() = _allSubscriberList

    var errorSubscribers = MutableLiveData<Event<String>>()

    val subscribersList = subscriberRepository.allSubcribersData

    init {
        saveOrUpdateButtonText.value = "Save"
        deleteAllButtonText.value = "Clear all"
    }


    fun saveSubscriber() {
        if (inputName.value == null) {
            errorSubscribers.value = Event("name is required")
        } else if (inputMail.value == null) {
            errorSubscribers.value = Event("mail is required")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputMail.value!!).matches()) {
            errorSubscribers.value = Event("not valid email")
        } else {
            if (isUpdateOrDelete) {
                subscribersUpdateOrDelete.name = inputName.value
                subscribersUpdateOrDelete.mail = inputMail.value
                update(subscribersUpdateOrDelete)

                deleteOrUpdateActionCompleted()
            } else {
                insert(Subcribers(name = inputName.value, mail = inputMail.value))
            }
        }
    }

    fun clearAllSubscriber() {
        if (isUpdateOrDelete) {
            delete(subscribersUpdateOrDelete)

            deleteOrUpdateActionCompleted()
        } else {
            clearAll()
        }
    }

    private fun insert(subcribers: Subcribers) = viewModelScope.launch {
        subscriberRepository.insert(subcribers)
    }

    private fun delete(subcribers: Subcribers) = viewModelScope.launch {
        subscriberRepository.delete(subcribers)
    }

    private fun update(subcribers: Subcribers) = viewModelScope.launch {
        subscriberRepository.update(subcribers)
    }

    fun clearAll() = viewModelScope.launch {
        subscriberRepository.deleteAll()
    }

    fun subscribeUpdateOrDelete(subcribers: Subcribers) {
        isUpdateOrDelete = true
        saveOrUpdateButtonText.value = "Update"
        deleteAllButtonText.value = "Delete"
        subscribersUpdateOrDelete = subcribers
    }

    private fun deleteOrUpdateActionCompleted() {
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        deleteAllButtonText.value = "Clear all"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}