package com.example.activitystarwapp.presentation.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.activitystarwapp.R
import com.example.activitystarwapp.databinding.ActivityGriditemBinding

class GridAdapter(private val context: Context, private val arrayName: ArrayList<Int>, private val arrayImage : ArrayList<Int>): BaseAdapter() {

    private  lateinit var binding:ActivityGriditemBinding

    override fun getCount(): Int = arrayImage.size

    override fun getItem(p0: Int): Any = arrayImage[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        binding = ActivityGriditemBinding.inflate(LayoutInflater.from(parent?.context),parent,false)
        with(binding){
            imvGridicon.setImageResource(arrayImage[position])
            tvTitlegrid.text = context.getString(arrayName[position])
            tvBusca.text = context.getString(R.string.busca)
            if (position == 3){
                root.background = context.getDrawable(R.drawable.roundedbtnrand)
                tvTitlegrid.setTextColor(context.getColor(R.color.white))
                tvBusca.setTextColor(context.getColor(R.color.white))
            }
        }
        return binding.root
    }
}