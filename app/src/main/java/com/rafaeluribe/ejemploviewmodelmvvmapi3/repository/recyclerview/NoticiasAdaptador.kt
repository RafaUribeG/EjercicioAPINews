package com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rafaeluribe.ejemploviewmodelmvvmapi3.R
import com.rafaeluribe.ejemploviewmodelmvvmapi3.repository.retrofit.noticias.Article

class NoticiasAdaptador(var context: Context, var listaDatos: List<Article>) :
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
        holder.titulo.text = listaDatos[position].title
        holder.descripcion.text = listaDatos[position].description

        Glide.with(context)
             .load(listaDatos[position].urlToImage)
             .override(400, 400)
             .into(holder.imagen)
    }

    override fun getItemCount(): Int {
        return listaDatos.size
    }
}