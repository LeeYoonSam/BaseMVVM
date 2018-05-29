package com.ys.base.basemvvm.ViewModel

import android.databinding.*
import android.text.TextUtils
import android.util.Patterns
import android.widget.RatingBar


class InputViewModel: BaseViewModel() {

    var name = ObservableField<String>()
    val email = ObservableField<String>()
    val score = ObservableInt()
    val isValid = ObservableBoolean()

    override fun onCreate() {
        super.onCreate()

        score.set(0)
        isValid.set(false)
    }

    fun validation() {
        val isValidName = !TextUtils.isEmpty(name.get())
        val isValidEmail = !TextUtils.isEmpty(Patterns.EMAIL_ADDRESS.matcher(email.get()).matches().toString())
        val isValidScore = score.get() > 0

        isValid.set(isValidName && isValidEmail && isValidScore)
    }

    val scoreChangeListener: RatingBar.OnRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { ratingBar, rating, fromUser ->
        run {
            score.set(rating.toInt())
            validation()
        }
    }
}