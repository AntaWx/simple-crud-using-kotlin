package com.surya_yasa_antariksa.latihancrud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.surya_yasa_antariksa.latihancrud.R
import com.surya_yasa_antariksa.latihancrud.database.entity.User

class UserAdapter(var list: List<User>, val listener: OnItemClickListener): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var judul: TextView
        var catatan: TextView
        var btnUpdate: ImageButton
        var btnDelete: ImageButton
        init {
            judul = view.findViewById(R.id.judul)
            catatan = view.findViewById(R.id.catatan)
            btnUpdate = view.findViewById(R.id.edit_button)
            btnDelete = view.findViewById(R.id.delete_button)

            btnUpdate.setOnClickListener {
                listener.onUpdateClick(adapterPosition)
            }

            btnDelete.setOnClickListener {
                listener.onDeleteClick(adapterPosition)
            }

            judul.setOnClickListener {
                listener.onJudulClick(adapterPosition)
            }
        }
    }

    interface OnItemClickListener {
        fun onUpdateClick(position: Int)
        fun onDeleteClick(position: Int)
        fun onJudulClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.judul.text = list[position].judulCatatan
        holder.catatan.text = list[position].isiCatatan
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
