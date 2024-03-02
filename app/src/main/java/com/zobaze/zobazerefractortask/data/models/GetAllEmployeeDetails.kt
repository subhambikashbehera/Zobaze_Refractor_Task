package com.zobaze.zobazerefractortask.data.models

import com.google.gson.annotations.SerializedName

data class GetAllEmployeeDetails(
    @SerializedName("data")
    val employeeItem: List<EmployeeItem>?=null,
    @SerializedName("message")
    val message: String?=null,
    @SerializedName("status")
    val status: String?=null
)