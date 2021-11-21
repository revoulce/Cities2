package ru.sfedu.cities2.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import ru.sfedu.cities2.R
import ru.sfedu.cities2.model.City

class RecyclerAdapter(
    private val context: Context,
    private val cityList: MutableList<City>,
    private val navController: NavController
) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemCity: TextView = itemView.findViewById(R.id.city_name)

        fun bind(listItem: City) {
            itemView.setOnClickListener {
                val bundle = Bundle()
                listItem.name?.let { it1 -> bundle.putString("cityName", it1) }
                listItem.country?.let { it2 -> bundle.putString("countryName", it2) }
                listItem.language?.let { it3 -> bundle.putString("languageName", it3) }
                listItem.population?.let { it4 -> bundle.putInt("population", it4) }

                navController.navigate(R.id.action_nav_cities_to_nav_city, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_cities, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = cityList[position]
        holder.bind(listItem)

        listItem.name?.let { it1 -> holder.itemCity.text = it1 }


    }

    override fun getItemCount(): Int {
        return cityList.size
    }
}