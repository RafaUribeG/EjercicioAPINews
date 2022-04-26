package com.rafaeluribe.ejemploviewmodelmvvmapi3.view.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.navigation.NavigationView
import com.rafaeluribe.ejemploviewmodelmvvmapi3.R
import com.rafaeluribe.ejemploviewmodelmvvmapi3.databinding.ActivityMainBinding
import com.rafaeluribe.ejemploviewmodelmvvmapi3.view.fragmentos.Fragmento_TotalNews

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    //ViewBinding
    private lateinit var b : ActivityMainBinding

    //Drawer
    private lateinit var toogle: ActionBarDrawerToggle

    //Toolbar
    private lateinit var myToolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        //
        b.myNavigationView.setNavigationItemSelectedListener(this)

        //Toolbar
        myToolbar = findViewById(R.id.myToolbar)

        setSupportActionBar(myToolbar)

        toogle = setDrawerToogle()

    }

    private fun setDrawerToogle(): ActionBarDrawerToggle {
        return ActionBarDrawerToggle(
            this,
            b.myDrawerLayout,
            myToolbar, R.string.drawer_open,
            R.string.drawer_close
        )
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toogle.onConfigurationChanged(newConfig)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toogle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toogle.onOptionsItemSelected(item)){
            true
        }
        else{
            super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        //para mostrar los fragmentos
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()


        when (item.itemId) {

            R.id.nav_buscar-> ft.replace(R.id.myFrame, Fragmento_TotalNews()).commit()
            R.id.nav_notiger -> ft.replace(R.id.myFrame, Fragmento_TotalNews()).commit()
            R.id.nav_categorias -> ft.replace(R.id.myFrame, Fragmento_TotalNews()).commit()
            R.id.nav_exit -> {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
        }
        title = item.title //para mostrar el título

        b.myDrawerLayout.closeDrawers() //para cerrar drawer

        return true
    }
}