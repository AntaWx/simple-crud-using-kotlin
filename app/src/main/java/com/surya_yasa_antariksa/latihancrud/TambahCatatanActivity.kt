package com.surya_yasa_antariksa.latihancrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.jakewharton.threetenabp.AndroidThreeTen
import com.surya_yasa_antariksa.latihancrud.database.UserDatabase
import com.surya_yasa_antariksa.latihancrud.database.entity.User
import java.time.LocalDateTime

class TambahCatatanActivity : AppCompatActivity() {

    private lateinit var judul: EditText
    private lateinit var catatan: EditText
    private lateinit var saveButton: Button
    private lateinit var database: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_catatan)

        AndroidThreeTen.init(this)

        judul = findViewById(R.id.judul_user)
        catatan = findViewById(R.id.catatan_user)
        saveButton = findViewById(R.id.save_button)
        database = UserDatabase.getInstance(applicationContext)

        saveButton.setOnClickListener {
            if(judul.text.isNotEmpty() && catatan.text.isNotEmpty()){
                database.userDao().insertAll(User(
                    null,
                    judul.text.toString(),
                    catatan.text.toString()
                ))
                finish()
            }else{
                Toast.makeText(
                    applicationContext,
                    "Silahkan isi semua kolom terlebih dahulu",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}