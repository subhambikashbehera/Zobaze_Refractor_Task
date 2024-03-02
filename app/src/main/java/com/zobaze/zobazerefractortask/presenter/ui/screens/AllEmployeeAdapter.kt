package com.zobaze.zobazerefractortask.presenter.ui.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zobaze.zobazerefractortask.R
import com.zobaze.zobazerefractortask.data.models.EmployeeItem
import com.zobaze.zobazerefractortask.databinding.EmployeeItemLayoutBinding

/**
 * date - 3/02/2024 time - 10:06 am
 * Created By - Subham Bikash Behera
 * */

class AllEmployeeAdapter : RecyclerView.Adapter<AllEmployeeAdapter.AllEmployeeViewHolder>() {

    /** to make the recycler view more scalable i have used here diff util*/
    private val callBack = object : DiffUtil.ItemCallback<EmployeeItem>() {
        override fun areItemsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callBack)

    inner class AllEmployeeViewHolder(private val binding: EmployeeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employeeItem: EmployeeItem?) {
            binding.employeeId.text = employeeItem?.id.toString()
            binding.employeeName.text = employeeItem?.employeeName.toString()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllEmployeeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: EmployeeItemLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.employee_item_layout, parent, false)
        return AllEmployeeViewHolder(view)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: AllEmployeeViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }


}