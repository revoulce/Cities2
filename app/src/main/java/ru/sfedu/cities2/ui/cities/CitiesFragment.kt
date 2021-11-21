package ru.sfedu.cities2.ui.cities

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sfedu.cities2.NavigationDrawer
import ru.sfedu.cities2.R
import ru.sfedu.cities2.`interface`.RetrofitServices
import ru.sfedu.cities2.adapter.RecyclerAdapter
import ru.sfedu.cities2.common.Common
import ru.sfedu.cities2.databinding.FragmentCitiesBinding
import ru.sfedu.cities2.model.City

class CitiesFragment : Fragment() {

    private lateinit var citiesViewModel: CitiesViewModel
    private var _binding: FragmentCitiesBinding? = null

    private lateinit var activity: NavigationDrawer
    private lateinit var mService: RetrofitServices
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter
    private lateinit var dialog: AlertDialog
    private lateinit var navController: NavController
    private var sort: Int? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        citiesViewModel =
            ViewModelProvider(this)[CitiesViewModel::class.java]

        _binding = FragmentCitiesBinding.inflate(inflater, container, false)

        activity = getActivity() as NavigationDrawer
        mService = Common.retrofitService
        layoutManager = LinearLayoutManager(this.context)
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this.context).build()
        navController = findNavController()

        if (sort != null) {
            sort = 1
        }

        getAllCityList(1)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swiperefresh)
        swipeRefreshLayout.setOnRefreshListener {
            when (sort) {
                1 -> sort = 2
                2 -> sort = 1
                else -> sort = 1
            }

            sort?.let { getAllCityList(it) }
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun getAllCityList(sortType: Int) {
        dialog.show()
        mService.getCityList().enqueue(object : Callback<MutableList<City>> {
            override fun onFailure(call: Call<MutableList<City>>, t: Throwable) {}

            override fun onResponse(
                call: Call<MutableList<City>>,
                response: Response<MutableList<City>>
            ) {
                val cityList = response.body() as MutableList<City>

                when (sortType) {
                    1 -> cityList.sortBy { it.name }
                    2 -> cityList.sortByDescending { it.name }
                }

                adapter = RecyclerAdapter(activity.baseContext, cityList, navController)
                adapter.notifyDataSetChanged()

                val recycleCityList = activity.findViewById<RecyclerView>(R.id.cities_list)
                recycleCityList.layoutManager = layoutManager
                recycleCityList.adapter = adapter
                recycleCityList.setHasFixedSize(true)

                cityList.forEach {
                    it.population?.let { it1 -> Log.i("CitiesFragment", it1.toString()) }
                    it.country?.let { it2 -> Log.i("CitiesFragment", it2) }
                    it.name?.let { it3 -> Log.i("CitiesFragment", it3) }
                    it.language?.let { it4 -> Log.i("CitiesFragment", it4) }
                }

                dialog.dismiss()
            }
        })
    }
}