package ru.sfedu.cities2.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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

        val cityName = view.findViewById<TextView>(R.id.text_city)
        val bundle = arguments

        if (bundle != null) {
            val city = bundle.getString("cityName").toString()

            if (city != "lol") {
                activity.lastCity = city
            }
        }

        cityName.text = activity.lastCity
    }
}