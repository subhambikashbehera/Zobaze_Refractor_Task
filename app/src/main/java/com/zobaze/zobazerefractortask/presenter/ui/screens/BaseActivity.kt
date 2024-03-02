package com.zobaze.zobazerefractortask.presenter.ui.screens

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.zobaze.zobazerefractortask.R
import com.zobaze.zobazerefractortask.data.utils.Constants
import com.zobaze.zobazerefractortask.data.utils.Dialogs
import com.zobaze.zobazerefractortask.data.utils.NetworkResource
import com.zobaze.zobazerefractortask.data.utils.showShortToast
import com.zobaze.zobazerefractortask.databinding.ActivityBaseBinding
import com.zobaze.zobazerefractortask.presenter.ui.base.BaseViewModel
import com.zobaze.zobazerefractortask.presenter.ui.base.BaseViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * date - 3/02/2024 time - 10:06 am
 * Created By - Subham Bikash Behera
 * */

@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {

    /** here adapter object everything injected for the singleton purpose
     * and to make the activity testable and to makes separation concern*/
    @Inject
    lateinit var allEmployeeAdapter: AllEmployeeAdapter

    @Inject
    lateinit var baseViewModelFactory: BaseViewModelFactory


    private lateinit var baseViewModel: BaseViewModel
    private lateinit var binding: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        baseViewModel = ViewModelProvider(this, baseViewModelFactory)[BaseViewModel::class.java]

        /*** here uiOperation function will do the all the ui related function , by doing this we can add global check **/
        uiOperations()
        observeNetworkCalls()
    }


    private fun uiOperations() {
        baseViewModel.getAllEmployeeDetailsRequest()
        binding.employeeRecyclerView.adapter = allEmployeeAdapter

        /*** this will for refreshing for list on swipe ***/
        binding.swipeRefreshLayout.setOnRefreshListener {
            baseViewModel.getAllEmployeeDetailsRequest()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun observeNetworkCalls() {
        /*** observing the api response with all the state ***/
        baseViewModel.getAllEmployeeDetails.observe(this) {
            when (it) {
                is NetworkResource.Loading -> {
                    Dialogs.showLoadingDialog(true, this, Constants.LOADING, false, 20000)
                    baseViewModel.getAllEmployeeDetails.postValue(null)
                }

                is NetworkResource.Success -> {
                    it.data?.let { result ->
                        if (result.status?.contains(Constants.SUCCESS, true) == true) {
                            allEmployeeAdapter.differ.submitList(result.employeeItem)
                            allEmployeeAdapter.notifyItemRangeChanged(
                                0,
                                result.employeeItem?.size ?: 0
                            )
                        }
                        Dialogs.showLoadingDialog(false, this, "", false, 20000)
                        baseViewModel.getAllEmployeeDetails.postValue(null)
                    }
                }

                is NetworkResource.Error -> {
                    Dialogs.showLoadingDialog(false, this,"", false, 20000)
                    baseViewModel.getAllEmployeeDetails.postValue(null)
                    showShortToast(it.message.toString())
                }
            }
        }
    }

}

