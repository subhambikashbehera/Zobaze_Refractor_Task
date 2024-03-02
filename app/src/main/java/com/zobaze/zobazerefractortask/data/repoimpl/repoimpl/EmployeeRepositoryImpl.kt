package com.zobaze.zobazerefractortask.data.repoimpl.repoimpl

import com.zobaze.zobazerefractortask.data.models.GetAllEmployeeDetails
import com.zobaze.zobazerefractortask.data.repoimpl.datasource.EmployeeRemoteDataSource
import com.zobaze.zobazerefractortask.data.utils.NetworkResource
import com.zobaze.zobazerefractortask.domain.repo.EmployeeRepository
import org.json.JSONObject
import retrofit2.Response

class EmployeeRepositoryImpl(private val employeeRemoteDataSource: EmployeeRemoteDataSource) : EmployeeRepository {
    // this class is applicable for tp process the data like for one screen three api calls required we will calls the three api calls and store the result and return from one channel

    override suspend fun getAllEmployeeDetails(): NetworkResource<GetAllEmployeeDetails> {
        return responseToResource(employeeRemoteDataSource.getAllEmployeeDetails())
    }


    // this is generic function which will convert the response type to resource type for api calls applicable for all api calls
    private fun <T> responseToResource(response: Response<T>): NetworkResource<T> {
        var networkResource: NetworkResource<T> = NetworkResource.Loading()
        try {
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    networkResource = NetworkResource.Success(result)
                }
            } else {
                networkResource = try {
                    val jsonObject = JSONObject(response.errorBody()!!.string())
                    if (jsonObject.has("message")) {
                        NetworkResource.Error(jsonObject.getString("message"))
                    } else {
                        NetworkResource.Error(response.code().toString() + " error")
                    }
                } catch (e: Exception) {
                    NetworkResource.Error(response.code().toString() + " error")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return networkResource
    }

}