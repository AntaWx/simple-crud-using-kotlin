package com.surya_yasa_antariksa.latihancrud.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid: Int?,

    @ColumnInfo(name = "judul_catatan")
    val judulCatatan: String?,

    @ColumnInfo(name = "isi_catatan")
    val isiCatatan: String?,
)