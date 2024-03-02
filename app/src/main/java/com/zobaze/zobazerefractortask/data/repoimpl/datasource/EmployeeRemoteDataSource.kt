package com.zobaze.zobazerefractortask.data.repoimpl.datasource

import com.zobaze.zobazerefractortask.data.models.GetAllEmployeeDetails
import retrofit2.Response

interface EmployeeRemoteDataSource {

    suspend fun getAllEmployeeDetails(): Response<GetAllEmployeeDetails>


}