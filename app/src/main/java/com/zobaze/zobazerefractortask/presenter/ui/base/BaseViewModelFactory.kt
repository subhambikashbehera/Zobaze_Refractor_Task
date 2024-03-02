package com.zobaze.zobazerefractortask.presenter.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zobaze.zobazerefractortask.domain.usecase.GetAllEmployeeUseCase

class BaseViewModelFactory(private val allEmployeeUseCase: GetAllEmployeeUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BaseViewModel(allEmployeeUseCase) as T
    }

}