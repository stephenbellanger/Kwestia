package com.sbellanger.arch.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Issue(
    @PrimaryKey
    var id: Int,
    var name: String,
    val number: Int,
    val description: String,
    val openedBy: String
)
