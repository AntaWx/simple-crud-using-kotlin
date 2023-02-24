package com.surya_yasa_antariksa.latihancrud

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.surya_yasa_antariksa.latihancrud.adapter.UserAdapter
import com.surya_yasa_antariksa.latihancrud.database.UserDatabase
import com.surya_yasa_antariksa.latihancrud.database.entity.User

class MainActivity : AppCompatActivity(), UserAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf<User>()
    private lateinit var adapter: UserAdapter
    private lateinit var database: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.fab)

        database = UserDatabase.getInstance(applicationContext)
        adapter = UserAdapter(list, this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        fab.setOnClickListener {
            startActivity(Intent(this, TambahCatatanActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData() {
        list.clear()
        list.addAll(database.userDao().getAll())
        adapter.notifyDataSetChanged()
    }

    override fun onUpdateClick(position: Int) {
        val user = list[position]
        startUpdateActivity(user)
    }

    override fun onDeleteClick(position: Int) {
        val user = list[position]
        AlertDialog.Builder(this)
            .setTitle("Hapus Catatan")
            .setMessage("Apakah Anda yakin ingin menghapus catatan ini?")
            .setPositiveButton("Ya") { _, _ ->
                database.userDao().delete(user)
                getData()
            }
            .setNegativeButton("Tidak", null)
            .create()
            .show()
    }

    private fun startUpdateActivity(user: User) {
        val intent = Intent(this, UpdateDataActivity::class.java)
        intent.putExtra("id", user.uid)
        intent.putExtra("judul", user.judulCatatan)
        intent.putExtra("isi", user.isiCatatan)
        startActivity(intent)
    }

}