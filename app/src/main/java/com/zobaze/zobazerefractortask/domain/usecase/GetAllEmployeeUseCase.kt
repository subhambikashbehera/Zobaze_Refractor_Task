package com.zobaze.zobazerefractortask.domain.usecase

import com.zobaze.zobazerefractortask.data.models.GetAllEmployeeDetails
import com.zobaze.zobazerefractortask.data.utils.NetworkResource
import com.zobaze.zobazerefractortask.domain.repo.EmployeeRepository

class GetAllEmployeeUseCase(private val employeeRepository: EmployeeRepository) {

    /** here all the business logics will be there like data processing , network availability checks , any validation required for api calls*/
    suspend fun execute(): NetworkResource<GetAllEmployeeDetails> = employeeRepository.getAllEmployeeDetails()


}