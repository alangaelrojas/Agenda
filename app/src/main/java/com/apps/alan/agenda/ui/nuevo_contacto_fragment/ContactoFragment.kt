package com.apps.alan.agenda.ui.nuevo_contacto_fragment


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.apps.alan.agenda.R
import com.apps.alan.agenda.db.Contacto
import kotlinx.android.synthetic.main.fragment_contacto.*

class ContactoFragment : Fragment() {

    private lateinit var viewModel : ContactoViewModel

    private lateinit var nombreText: TextView
    private lateinit var telefonoText: TextView
    private lateinit var cumpleText: TextView
    private lateinit var notaText: TextView

    private var idContacto = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contacto, container, false)

        viewModel = ViewModelProviders.of(this).get(ContactoViewModel::class.java)

        //Casting
        nombreText = view.findViewById(R.id.nombre_edit_contact)
        telefonoText = view.findViewById(R.id.telefono_edit_contact)
        cumpleText = view.findViewById(R.id.cumple_edit_contact)
        notaText = view.findViewById(R.id.nota_edit_contact)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            idContacto = ContactoFragmentArgs.fromBundle(it).id
        }

        if(idContacto != 0){
            retrieveContacto(idContacto)
        }

        fab_guardar_cambios.setOnClickListener {
            if(idContacto == 0){
                nuevoContacto()
            }else if (idContacto != 0){
                updateContacto()
            }
        }
    }
    private fun retrieveContacto(id:Int) {
        viewModel.getContacto(id).observe(this, Observer {
            nombreText.setText(it.nombre)
            telefonoText.setText(it.telefono.toString())
            cumpleText.setText(it.cumple)
            notaText.setText(it.nota)
        })
    }

    fun nuevoContacto(){
        if(!TextUtils.isEmpty(nombre_edit_contact.text.toString()) && !TextUtils.isEmpty(telefono_edit_contact.text.toString())
            && !TextUtils.isEmpty(cumple_edit_contact.text.toString())) {

            val contacto = Contacto(nombre_edit_contact.text.toString(), telefono_edit_contact.text.toString().toInt(), cumple_edit_contact.text.toString(), nota_edit_contact.text.toString())
            try{
                viewModel.insertContacto(contacto)
                Toast.makeText(context!!.applicationContext, "Guardado", Toast.LENGTH_SHORT).show()
            }catch (e:Exception){
                Toast.makeText(context!!.applicationContext, "Error al guardar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateContacto(){
        if(!TextUtils.isEmpty(nombre_edit_contact.text.toString()) && !TextUtils.isEmpty(telefono_edit_contact.text.toString())
            && !TextUtils.isEmpty(cumple_edit_contact.text.toString())){

            val contacto = Contacto(nombre_edit_contact.text.toString(), telefono_edit_contact.text.toString().toInt(), cumple_edit_contact.text.toString(), nota_edit_contact.text.toString())

            try{
                viewModel.updateContacto(contacto, idContacto)
                Toast.makeText(context!!.applicationContext, "Actualizado", Toast.LENGTH_SHORT).show()
            }catch (e:Exception){
                Toast.makeText(context!!.applicationContext, "Error al actualizar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
