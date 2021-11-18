package ru.sfedu.cities2

import android.os.Bundle
import android.view.Menu
import android.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sfedu.cities2.`interface`.RetrofitServieces
import ru.sfedu.cities2.common.Common
import ru.sfedu.cities2.databinding.ActivityNavigationDrawerBinding
import ru.sfedu.cities2.model.City

class NavigationDrawer : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavigationDrawerBinding

    lateinit var mService: RetrofitServieces
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: RecyclerAdapter
    lateinit var dialog: AlertDialog

    public var lastCity: String = "lol"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarNavigationDrawer.toolbar)

        binding.appBarNavigationDrawer.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_navigation_drawer)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_cities, R.id.nav_city,
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        mService = Common.retrofitService
        layoutManager = LinearLayoutManager(this)
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation_drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_navigation_drawer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun getAllCityList() {
        dialog.show()
        mService.getCityList().enqueue(object: Callback<MutableList<City>> {
            override fun onFailure(call: Call<MutableList<City>>, t: Throwable) {}

            override fun onResponse(call: Call<MutableList<City>>, response: Response<MutableList<City>) {
                adapter = RecyclerAdapter(baseContext, response.body() as MutableList<City>)
                adapter.notifyDataSetChanged()

                dialog.dismiss()
            }
        })
    }
}