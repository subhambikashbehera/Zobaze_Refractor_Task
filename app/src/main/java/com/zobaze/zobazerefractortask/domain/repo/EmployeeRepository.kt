package com.zobaze.zobazerefractortask.domain.repo

import com.zobaze.zobazerefractortask.data.models.GetAllEmployeeDetails
import com.zobaze.zobazerefractortask.data.utils.NetworkResource



interface EmployeeRepository {
    //here we are clubbing all the features related to employee
    suspend fun getAllEmployeeDetails(): NetworkResource<GetAllEmployeeDetails>
}