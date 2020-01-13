package com.apps.alan.agenda.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.apps.alan.agenda.R
import com.mavi.core.data.Contacto
import com.apps.alan.agenda.presentation.ui.lista_fragment.ListAgendaFragmentDirections
import com.apps.alan.agenda.presentation.ui.lista_fragment.ListContactosViewModel


class ContactoAdapter(private val viewModel: ListContactosViewModel, c:Context) : RecyclerView.Adapter<ContactoAdapter.HolderContacto>() {


    private var listContactos: ArrayList<Contacto> = ArrayList()
    private var context = c

    fun addContactList(contactList:List<Contacto>){
        listContactos.clear()
        listContactos.addAll(contactList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderContacto {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contacto, parent, false)
        return HolderContacto(view)
    }

    override fun onBindViewHolder(holder: HolderContacto, position: Int) {

        holder.nombre.text = listContactos.get(position).nombre
        holder.telefono.text = listContactos.get(position).telefono.toString()
        holder.cumple.text = listContactos.get(position).cumple
        holder.nota.text = listContactos.get(position).nota

        holder.itemView.setOnClickListener{
            val action = ListAgendaFragmentDirections.actionListAgendaFragmentToContactoFragment()
            action.id = listContactos.get(position).id
            Navigation.findNavController(it).navigate(action)
        }

        holder.itemView.setOnLongClickListener{
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle(R.string.preguntar_eliminar)

            dialog.setPositiveButton(R.string.eliminar) { _, i ->
                viewModel.deleteContacto(listContactos.get(position).id)
            }
            dialog.show()
            true

        }

    }

    override fun getItemCount(): Int {
        return listContactos.size
    }

    interface OnClick{
        fun setOnLongClick()
    }

    inner class HolderContacto(view: View) : RecyclerView.ViewHolder(view) {

        val nombre:TextView = view.findViewById(R.id.nombre_contacto_item)
        val telefono:TextView = view.findViewById(R.id.telefono_contacto_item)
        val cumple:TextView = view.findViewById(R.id.cumple_contacto_item)
        val nota:TextView = view.findViewById(R.id.nota_contacto_item)

        val card:CardView = view.findViewById(R.id.card_item_contacto)

    }
}