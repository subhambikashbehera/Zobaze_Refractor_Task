package com.zobaze.zobazerefractortask.data.api

import com.zobaze.zobazerefractortask.data.models.GetAllEmployeeDetails
import retrofit2.Response
import retrofit2.http.GET

/**
 * date - 3/02/2024 time - 10:06 am
 * Created By - Subham Bikash Behera
 * */

interface ZobazeApiService {
    // here we are adding all the endpoints of apis


    @GET("api/v1/employees")
    suspend fun getAllEmployeeDetails():Response<GetAllEmployeeDetails>




}