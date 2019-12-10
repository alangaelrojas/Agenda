package com.apps.alan.agenda.ui.agenda_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.apps.alan.agenda.R
import com.apps.alan.agenda.ui.lista_fragment.ListAgendaFragment
import com.apps.alan.agenda.ui.lista_fragment.ListAgendaFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*

class AgendaActivity : AppCompatActivity() {

    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}
