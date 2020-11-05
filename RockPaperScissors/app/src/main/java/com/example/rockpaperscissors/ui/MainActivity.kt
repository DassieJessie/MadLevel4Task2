package com.example.rockpaperscissors.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.rockpaperscissors.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        changeMenu()
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.btnMenuHistory -> {
                navController.navigate(
                    R.id.action_gameplayFragment_to_gameHistoryFragment
                )
                return true
            }
            R.id.btnMenuDelete -> {
                return false
            }
            android.R.id.home -> {
                navController.navigate(
                    R.id.action_gameHistoryFragment_to_gameplayFragment
                )
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun changeMenu(){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in arrayOf(R.id.gameplayFragment)) {

                toolbar.title = getString(R.string.gameplay_menu_title)
                toolbar.menu.findItem(R.id.btnMenuHistory)?.isVisible = true
                toolbar.menu.findItem(R.id.btnMenuDelete)?.isVisible = false
                toolbar.menu.findItem(R.id.btnMenuBack)?.isVisible = false
            } else {

                toolbar.title = getString(R.string.history_menu_title)
                toolbar.menu.findItem(R.id.btnMenuHistory).isVisible = false
                toolbar.menu.findItem(R.id.btnMenuDelete)?.isVisible = true
                toolbar.menu.findItem(R.id.btnMenuBack)?.isVisible = true
            }
        }
    }

}