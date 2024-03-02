package com.zobaze.zobazerefractortask.presenter.di

import com.zobaze.zobazerefractortask.data.api.ZobazeApiService
import com.zobaze.zobazerefractortask.data.repoimpl.datasource.EmployeeRemoteDataSource
import com.zobaze.zobazerefractortask.data.repoimpl.datasource.EmployeeRemoteDataSourceImpl
import com.zobaze.zobazerefractortask.data.repoimpl.repoimpl.EmployeeRepositoryImpl
import com.zobaze.zobazerefractortask.domain.repo.EmployeeRepository
import com.zobaze.zobazerefractortask.domain.usecase.GetAllEmployeeUseCase
import com.zobaze.zobazerefractortask.presenter.ui.screens.AllEmployeeAdapter
import com.zobaze.zobazerefractortask.presenter.ui.base.BaseViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * date - 3/02/2024 time - 10:06 am
 * Created By - Subham Bikash Behera
 * */

@Module
@InstallIn(SingletonComponent::class)
class Module {

    // here we are providing the EmployeeRemoteDataSource for the singleton use
    @Singleton
    @Provides
    fun provideEmployeeDataSource(zobazeApiService: ZobazeApiService): EmployeeRemoteDataSource {
        return EmployeeRemoteDataSourceImpl(zobazeApiService)
    }
    // here we are providing the EmployeeRepository for the singleton use
    @Singleton
    @Provides
    fun provideEmployeeRepository(employeeRemoteDataSource: EmployeeRemoteDataSource): EmployeeRepository {
        return EmployeeRepositoryImpl(employeeRemoteDataSource)
    }
    // here we are providing the GetAllEmployeeUseCase for the singleton use
    @Singleton
    @Provides
    fun provideGetAllEmployeeUseCase(getAllEmployeeUseCase: EmployeeRepository): GetAllEmployeeUseCase {
        return GetAllEmployeeUseCase(getAllEmployeeUseCase)
    }
    // here we are providing the BaseViewModelFactory for the singleton use
    @Singleton
    @Provides
    fun provideBaseViewModelFactory(getAllEmployeeUseCase: GetAllEmployeeUseCase): BaseViewModelFactory{
        return BaseViewModelFactory(getAllEmployeeUseCase)
    }
    // here we are providing the AllEmployeeAdapter for the singleton use
    @Singleton
    @Provides
    fun provideAllEmployeeAdapterAdapter(): AllEmployeeAdapter {
        return AllEmployeeAdapter()
    }


}