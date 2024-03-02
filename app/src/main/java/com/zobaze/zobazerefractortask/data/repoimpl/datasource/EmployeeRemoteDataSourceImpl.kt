package com.zobaze.zobazerefractortask.data.repoimpl.datasource

import com.zobaze.zobazerefractortask.data.api.ZobazeApiService
import com.zobaze.zobazerefractortask.data.models.GetAllEmployeeDetails
import retrofit2.Response

class EmployeeRemoteDataSourceImpl(private val zobazeApiService: ZobazeApiService) : EmployeeRemoteDataSource {

    // here we made remote service to calls apis from server in future if required local caching we will create the local caching

    override suspend fun getAllEmployeeDetails(): Response<GetAllEmployeeDetails> {
       return zobazeApiService.getAllEmployeeDetails()
    }
}