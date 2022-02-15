package com.example.activitystarwapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.activitystarwapp.databinding.ActivityGriditemBinding

class GridAdapter(context: Context, private val arrayList: ArrayList<String>): BaseAdapter() {

    private  lateinit var binding:ActivityGriditemBinding

    override fun getCount(): Int = arrayList.size

    override fun getItem(p0: Int): Any = arrayList[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        binding = ActivityGriditemBinding.inflate(LayoutInflater.from(parent?.context),parent,false)
        return binding.root
    }
}