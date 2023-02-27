package com.surya_yasa_antariksa.latihancrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.TextView
import com.surya_yasa_antariksa.latihancrud.adapter.UserAdapter
import com.surya_yasa_antariksa.latihancrud.database.UserDatabase
import com.surya_yasa_antariksa.latihancrud.database.entity.User
import org.w3c.dom.Text

class DetailCatatanActivity : AppCompatActivity() {

    private lateinit var buttonEdit: Button
    private lateinit var database: UserDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_catatan)

        database = UserDatabase.getInstance(applicationContext)


        buttonEdit = findViewById(R.id.button_back)

        val judul = intent.getStringExtra("judul")
        val isi = intent.getStringExtra("isi")

        val detailJudul = findViewById<TextView>(R.id.detail_judul_user)
        val detailIsi = findViewById<TextView>(R.id.detail_catatan_user)

        detailJudul.text = judul
        detailJudul.inputType = InputType.TYPE_NULL
        detailIsi.text = isi

        buttonEdit.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}