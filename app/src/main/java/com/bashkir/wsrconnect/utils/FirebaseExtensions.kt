package com.bashkir.wsrconnect.utils

import com.google.firebase.database.DatabaseReference

const val usersPath = "users"

fun DatabaseReference.users(): DatabaseReference = this.child(usersPath)

fun DatabaseReference.user(userId: String): DatabaseReference = this.users().child(userId)