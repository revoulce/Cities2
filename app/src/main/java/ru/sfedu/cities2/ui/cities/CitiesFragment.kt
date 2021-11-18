package ru.sfedu.cities2.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.sfedu.cities2.R
import ru.sfedu.cities2.RecyclerAdapter
import ru.sfedu.cities2.databinding.FragmentCitiesBinding

class CitiesFragment : Fragment() {

    private lateinit var citiesViewModel: CitiesViewModel
    private var _binding: FragmentCitiesBinding? = null

    private lateinit var recycleView: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        citiesViewModel =
            ViewModelProvider(this).get(CitiesViewModel::class.java)

        _binding = FragmentCitiesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cities = resources.getTextArray(R.array.cities)
        val currentFragment = this

        recycleView = view.findViewById<RecyclerView>(R.id.cities_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = RecyclerAdapter(cities, currentFragment)
        }
    }
}