package com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.recyclerview

import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.rafaeluribe.ejemploviewmodelmvvmapi3.R
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.noticias.Data
import com.rafaeluribe.ejemploviewmodelmvvmapi3.view.activities.VistaDetalleNoticias

class NoticiasAdaptador(var context: Context,
                        var listaDatos: List<Data>) :
    RecyclerView.Adapter<NoticiasAdaptador.ViewHolderDatos>()  {


    class ViewHolderDatos(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imagen: ImageView
        var titulo: TextView
        var descripcion: TextView

        init {
            titulo = itemView.findViewById(R.id.txtTitulo)
            descripcion = itemView.findViewById(R.id.txtDes)
            imagen = itemView.findViewById(R.id.im_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDatos {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, null, false)
        return ViewHolderDatos(view)
    }

    override fun onBindViewHolder(holder: ViewHolderDatos, position: Int) {
        holder.itemView.animation =
            AnimationUtils.loadAnimation(context, R.anim.fade_transition)
        holder.titulo.text = Html.fromHtml(listaDatos[position].title)
        holder.descripcion.text = Html.fromHtml(listaDatos[position].description)

        Glide.with(context)
             .load(listaDatos[position].url)
             .error(R.drawable.noimage)
             .override(200, 200)
             .into(holder.imagen)

        holder.itemView.setOnClickListener {
            var detail = Gson().toJson(listaDatos[holder.layoutPosition])
            var intent = Intent(context, VistaDetalleNoticias::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("data", detail)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listaDatos.size
    }
}