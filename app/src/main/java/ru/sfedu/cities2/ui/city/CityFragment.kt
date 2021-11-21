package ru.sfedu.cities2.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.sfedu.cities2.NavigationDrawer
import ru.sfedu.cities2.R
import ru.sfedu.cities2.databinding.FragmentCityBinding

class CityFragment : Fragment() {

    private lateinit var cityViewModel: CityViewModel
    private var _binding: FragmentCityBinding? = null

    private lateinit var activity: NavigationDrawer

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cityViewModel =
            ViewModelProvider(this).get(CityViewModel::class.java)

        _binding = FragmentCityBinding.inflate(inflater, container, false)
        val root: View = binding.root

        activity = getActivity() as NavigationDrawer

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cityNameView = view.findViewById<TextView>(R.id.text_city)
        val countryNameView = view.findViewById<TextView>(R.id.text_country)
        val languageNameView = view.findViewById<TextView>(R.id.text_language)
        val populationView = view.findViewById<TextView>(R.id.text_population)

        val bundle = arguments

        if (bundle != null) {
            val city = bundle.getString("cityName").toString()
            val country = bundle.getString("countryName").toString()
            val language = bundle.getString("languageName").toString()
            val population = bundle.getInt("population")

            if (city != "lol") {
                activity.lastCity = city
            }

            if (country != "lol") {
                activity.lastCountry = country
            }

            if (language != "lol") {
                activity.lastLanguage = language
            }

            if (population != 420) {
                activity.lastPopulation = population
            }
        }

        cityNameView.text = activity.lastCity
        countryNameView.text = getString(R.string.country_name, activity.lastCountry)
        languageNameView.text = getString(R.string.language_name, activity.lastLanguage)
        populationView.text = getString(R.string.population, activity.lastPopulation)
    }
}