package com.jwar.github_repo.core.shared

import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.*

fun Date.toStringFormat(pattern: String = "dd/MM/yyyy HH:mm"): String {
    return SimpleDateFormat(pattern, Locale.getDefault())
            .format(this)
}

fun ImageView.load(url: String) {
    Glide.with(context)
            .load(url)
            .apply(RequestOptions().circleCrop())
            .into(this)
}

fun Fragment.toast(message: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(requireContext(), message, length).show()
}