package com.example.myapplication

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

class MyViewModel(): ViewModel(){

    private val _tempLiveData: MutableLiveData<String> = MutableLiveData()

    fun checkTemp(): LiveData<String>{
        viewModelScope.launch(Dispatchers.IO){
            while (true){
                try {
                    val array = arrayOf(
                        "su", "-c", "busybox stty 4800 -F /dev/ttyS1"
                    )
                    val array2 = arrayOf(
                        "su", "-c", "busybox head -n 1 /dev/ttyS1"
                    )

                    var process: Process = Runtime.getRuntime().exec(array)
                    process = Runtime.getRuntime().exec(array2)
                    val result = process.inputStream.bufferedReader().readLine().trim()
                    _tempLiveData.postValue(result)

                }catch (e: IOException){
                    Log.e(MainActivity.TAG, e.message!!)
                    return@launch
                }catch (e: InterruptedException){
                    Log.e(MainActivity.TAG, e.message!!)
                    return@launch
                }
                delay(1000)
            }
        }

        return _tempLiveData
    }
}