package com.ihediohachinedu.roomdb.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.ihediohachinedu.roomdb.R
import com.ihediohachinedu.roomdb.db.PersonalData
import com.ihediohachinedu.roomdb.databinding.RecyclerViewRowBinding

class PersonalDataAdapter(private val onDeleteClick: (PersonalData) -> Unit)
    : RecyclerView.Adapter<PersonalDataAdapter.DataViewHolder>() {

    private var personDataList = mutableListOf<PersonalData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<PersonalData>) {
        personDataList.clear()
        personDataList.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = RecyclerViewRowBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            return DataViewHolder(binding)
       {
            onDeleteClick.invoke(it)
        }
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.setData(personDataList[position])
    }

    override fun getItemCount(): Int {
       return personDataList.size
    }

    inner class DataViewHolder(
        private val binding: RecyclerViewRowBinding, val onDeleteClick: (PersonalData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(personData: PersonalData) {
            binding.apply {
                binding.tvName.text= "Name : ${personData.fullName}"
                binding.tvAge.text = "Age : ${personData.ageTotal.toString()}"
                binding.deleteUserID.setOnClickListener {
                    onDeleteClick(personData)
                }
            }

        }

    }
}
