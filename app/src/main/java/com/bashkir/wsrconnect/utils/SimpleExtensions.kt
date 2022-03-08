package com.bashkir.wsrconnect.utils

import android.util.Patterns

fun String.isBlankOrEmpty(): Boolean = isEmpty() && isBlank()

fun String.emailIsValid(): Boolean =
    !isBlankOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun isValid(email: String? = null, vararg fields: String): Boolean =
    fields.any { !it.isBlankOrEmpty() } && email?.emailIsValid() ?: true