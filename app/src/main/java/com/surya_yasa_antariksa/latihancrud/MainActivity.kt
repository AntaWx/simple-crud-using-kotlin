package com.surya_yasa_antariksa.latihancrud

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.surya_yasa_antariksa.latihancrud.adapter.UserAdapter
import com.surya_yasa_antariksa.latihancrud.database.UserDatabase
import com.surya_yasa_antariksa.latihancrud.database.entity.User
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity(), UserAdapter.OnItemClickListener {

        private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf<User>()
    private lateinit var adapter: UserAdapter
    private lateinit var database: UserDatabase
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.fab)
        searchEditText = findViewById(R.id.cari_judul)
        searchButton = findViewById(R.id.icon_cari_judul)
        var actionBar = supportActionBar

        when{
            actionBar != null -> {
                actionBar.title = "Daftar Catatan"
            }
        }

        database = UserDatabase.getInstance(applicationContext)
        adapter = UserAdapter(list, this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)

        fab.setOnClickListener {
            startActivity(Intent(this, TambahCatatanActivity::class.java))
        }

        searchEditText.setOnClickListener{
            val judul = searchEditText.text.toString().trim()
            searchByJudul(judul)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_bar, menu)
        return true
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

    override fun onJudulClick(position: Int) {
        val user = list[position]
        startDetailActivity(user)
    }

    private fun startUpdateActivity(user: User) {
        val intent = Intent(this, UpdateDataActivity::class.java)
        intent.putExtra("id", user.uid)
        intent.putExtra("judul", user.judulCatatan)
        intent.putExtra("isi", user.isiCatatan)
        startActivity(intent)
    }

    private fun startDetailActivity(user: User) {
        val intent = Intent(this, DetailCatatanActivity::class.java)
        intent.putExtra("id", user.uid)
        intent.putExtra("judul", user.judulCatatan)
        intent.putExtra("isi", user.isiCatatan)
        startActivity(intent)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun searchByJudul(judul: String) {

        val findJudul = database.userDao().getAll().filter { it.judulCatatan!!.contains(judul, true) }

        list.clear()
        list.addAll(findJudul)
        if (findJudul.isEmpty()){
            Toast.makeText(this, "Judul yang dicari tidak ditemukan", Toast.LENGTH_SHORT).show()
        }
        adapter.notifyDataSetChanged()

        // menyembunyikan keyboard
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(searchEditText.windowToken, 0)
        // menghilangkan fokus dari searchEditText
        searchEditText.clearFocus()
    }


}