package com.apps.alan.agenda.presentation.ui.lista_fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.apps.alan.agenda.R
import com.apps.alan.agenda.adapter.ContactoAdapter
import kotlinx.android.synthetic.main.fragment_list_agenda.*

class ListAgendaFragment : Fragment() {

    private lateinit var viewModel : ListContactosViewModel
    private lateinit var adapterC : ContactoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_list_agenda, container, false)

        viewModel = ViewModelProviders.of(this).get(ListContactosViewModel::class.java)

        //Set adapter
        adapterC = ContactoAdapter(viewModel, activity!!)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_contactos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterC
            setHasFixedSize(true)
        }

        fab.setOnClickListener{
            val action = ListAgendaFragmentDirections.actionListAgendaFragmentToContactoFragment()
            Navigation.findNavController(it).navigate(action)
        }

        viewModel.getAllContactos().observe(this, Observer{
            adapterC.addContactList(it)
        })
    }
}
