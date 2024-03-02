package com.zobaze.zobazerefractortask.data.models

import com.google.gson.annotations.SerializedName

data class EmployeeItem(
    @SerializedName("employee_age")
    val employeeAge: Int?=null,
    @SerializedName("employee_name")
    val employeeName: String?=null,
    @SerializedName("employee_salary")
    val employeeSalary: Int?=null,
    @SerializedName("id")
    val id: Int?=null,
    @SerializedName("profile_image")
    val profileImage: String?=null
)