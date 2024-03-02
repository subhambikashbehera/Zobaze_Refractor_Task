package com.zobaze.zobazerefractortask.presenter.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zobaze.zobazerefractortask.data.models.GetAllEmployeeDetails
import com.zobaze.zobazerefractortask.data.utils.NetworkResource
import com.zobaze.zobazerefractortask.domain.usecase.GetAllEmployeeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BaseViewModel(private val getAllEmployeeUseCase: GetAllEmployeeUseCase): ViewModel() {

    /** employee related api calls**/
    private val _getAllEmployeeDetails: MutableLiveData<NetworkResource<GetAllEmployeeDetails>> = MutableLiveData() // this private
    val getAllEmployeeDetails : MutableLiveData<NetworkResource<GetAllEmployeeDetails>> = _getAllEmployeeDetails // this is public we can access
    fun getAllEmployeeDetailsRequest() = viewModelScope.launch(Dispatchers.IO) {
        try {
            //first we send the loading state
            _getAllEmployeeDetails.postValue(NetworkResource.Loading())
            //then made api call and waiting for the response
            _getAllEmployeeDetails.postValue(getAllEmployeeUseCase.execute())
        } catch (e: Exception) {
            //if any error happened it will send the details
            _getAllEmployeeDetails.postValue(NetworkResource.Error(e.message.toString()))
        }
    }
}