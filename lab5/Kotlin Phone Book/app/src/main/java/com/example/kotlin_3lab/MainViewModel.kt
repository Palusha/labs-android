package com.example.kotlin_3lab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
private val NO_BUZZ_PATTERN = longArrayOf(0)

enum class BuzzType(val pattern: LongArray) {
    CORRECT(CORRECT_BUZZ_PATTERN),
    GAME_OVER(GAME_OVER_BUZZ_PATTERN),
    COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
    NO_BUZZ(NO_BUZZ_PATTERN)
}

class MainViewModel(username: String) : ViewModel(){
    private var _user_name = MutableLiveData<String>()
    val user_name: LiveData<String>
    get() = _user_name

    private var _phone_number = MutableLiveData<String>()
    val phone_number: LiveData<String>
        get() = _phone_number

    private var _emailM = MutableLiveData<String>()
    val emailM: LiveData<String>
        get() = _emailM

    private var _go_to = MutableLiveData<String>()
    val go_to: LiveData<String>
        get() = _go_to

    private var _user_count = MutableLiveData<Int>()
    val user_count: LiveData<Int>
        get() = _user_count

    private val _eventUserSaved = MutableLiveData<Boolean>()
    val eventUserSaved: LiveData<Boolean>
        get() = _eventUserSaved

    private val _eventBuzz = MutableLiveData<BuzzType>()
    val eventBuzz: LiveData<BuzzType>
        get() = _eventBuzz

    init {
        Timber.i("MainViewModel created")
        _eventUserSaved.value = false
        _user_name.value = username
        _phone_number.value = ""
        _emailM.value = ""
        _go_to.value = ""
    }
    fun setDataInfo(username: String, phoneNumber: String, email :String, goTo: String){
//        _user_name.value = username
        _phone_number.value = phoneNumber
        _emailM.value = email
        _go_to.value = goTo
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("MainViewModel destroyed")
    }
    fun saveUser(){
        _user_count.value = 1
        if(_user_count.value == 1){
            _eventUserSaved.value = true
            _eventBuzz.value = BuzzType.GAME_OVER
        }
    }

    fun onSaveUserComplete(){
        _eventUserSaved.value = false
    }

    fun onBuzzComplete() {
        _eventBuzz.value = BuzzType.NO_BUZZ
    }
}