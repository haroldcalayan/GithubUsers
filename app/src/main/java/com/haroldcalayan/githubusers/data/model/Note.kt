/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/29/2021
 */

package com.haroldcalayan.githubusers.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey val userId: Int,
    val notes: String?
)