package com.project.segunfrancis.roomwordsamplekotlin

import android.app.Activity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.floatingactionbutton.FloatingActionButton

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navController = this.findNavController(R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        navController
            .addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.newWordFragment) {
                    hideFAB(fab)
                } else {
                    showFAB(fab)
                }
            }

        fab.setOnClickListener {
            navController.navigate(R.id.newWordFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        hideSoftKeyboard(this)
        return navController.navigateUp()
    }

    private fun hideFAB(floatingActionButton: FloatingActionButton) {
        floatingActionButton.visibility = View.GONE
    }

    private fun showFAB(floatingActionButton: FloatingActionButton) {
        floatingActionButton.visibility = View.VISIBLE
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager =
            this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        } else {
            view = View(activity)
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
