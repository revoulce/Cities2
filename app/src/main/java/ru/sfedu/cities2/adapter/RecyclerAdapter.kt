package ru.sfedu.cities2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.sfedu.cities2.model.City
import ru.sfedu.cities2.ui.cities.CitiesFragment

class RecyclerAdapter(private val context: Context, private val cityList: MutableList<City>, private val fragment: Fragment) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemCity: TextView = itemView.findViewById(R.id.city_name)

        fun bind(listItem: City) {
            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("cityName", itemCity.text.toString())

                fragment.findNavController().navigate(R.id.action_nav_cities_to_nav_city, bundle)
            }
        }

//        init {
//            itemView.setOnClickListener {
//                val bundle = Bundle()
//                bundle.putString("cityName", itemCity.text.toString())
//
//                fragment.findNavController()
//                    .navigate(R.id.action_nav_cities_to_nav_city, bundle)
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_cities, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = cityList[position]
        holder.bind(listItem)

        holder.itemCity.text = cityList[position].name
    }

    override fun getItemCount(): Int {
        return cityList.size
    }
}