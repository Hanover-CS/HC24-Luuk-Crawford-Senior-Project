package com.zybooks.hc24_luuk_crawford_senior_project


import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MenuAdapter(private val context : Activity,private val arrayList : ArrayList<MenuOffer>) : ArrayAdapter<MenuOffer>(context,
R.layout.list_item,arrayList){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_item,null)

        val itemName: TextView = view.findViewById(R.id.itemName)
        val sideInfo: TextView = view.findViewById(R.id.foodSideInformation)
        val price: TextView = view.findViewById(R.id.foodPrice)

        //image view
        val imageID: TextView = view.findViewById(R.id.foodPhoto)

        itemName.text = arrayList[position].name
        sideInfo.text = arrayList[position].side
        price.text = arrayList[position].price
        //ADD IMAGE

        return view

    }
}