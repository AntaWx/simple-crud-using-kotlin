package com.surya_yasa_antariksa.latihancrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.surya_yasa_antariksa.latihancrud.database.UserDatabase
import com.surya_yasa_antariksa.latihancrud.database.entity.User

class UpdateDataActivity : AppCompatActivity() {

    private lateinit var editJudul: EditText
    private lateinit var editIsi: EditText
    private lateinit var buttonUpdate: Button

    private lateinit var database: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)

        editJudul = findViewById(R.id.update_judul_user)
        editIsi = findViewById(R.id.update_catatan_user)
        buttonUpdate = findViewById(R.id.update_button)
        var actionBar = supportActionBar

        when{
            actionBar != null ->{
                actionBar.setDisplayHomeAsUpEnabled(true)
                actionBar.title = "Update Catatan"
            }
        }

        val id = intent.getIntExtra("id", -1)
        val judul = intent.getStringExtra("judul")
        val isi = intent.getStringExtra("isi")

        database = UserDatabase.getInstance(applicationContext)

        editJudul.setText(judul)
        editIsi.setText(isi)

        buttonUpdate.setOnClickListener {
            val newJudul = editJudul.text.toString()
            val newIsi = editIsi.text.toString()

            val user = User(id, newJudul, newIsi)
            database.userDao().update(user)

            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
