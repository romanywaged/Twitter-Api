package com.romanydev.uipackage

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.card.MaterialCardView
import com.romanydev.twittertask.R
import com.romanydev.twittertask.databinding.CustomCardViewRoundedBinding


class CustomCardViewRounded @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val titleTextView: TextView
    private val bodyTextView: TextView
    private lateinit var binding:CustomCardViewRoundedBinding

    init {
        // Inflate the custom view layout
        binding = CustomCardViewRoundedBinding.inflate(LayoutInflater.from(context), this)

        // Find the TextViews
        titleTextView = findViewById(R.id.titleTextView)
        bodyTextView = findViewById(R.id.bodyTextView)

        titleTextView.text = context.getString(R.string.app_name)

        // Set CardView properties
        radius = 32f
        cardElevation = 8f
        strokeWidth = 10
        strokeColor = context.getColor(R.color.whiteBlue)
    }

    // Function to set the title text
    fun setTitleText(title: String) {
        titleTextView.text = title
    }

    // Function to set the body text
    fun setBodyText(body: String, isRemaining:Boolean = false, isInitial:Boolean = false) {
        val charCount = countTweetCharacters(body)
        bodyTextView.text = "$charCount/280"

        if (isRemaining){
            val charCountDiff = 280 - charCount
            bodyTextView.text = "$charCountDiff"
        }

        if (isInitial){
            bodyTextView.text = body
        }
    }

    // Function to count characters considering Twitter's criteria
    fun countTweetCharacters(text: String): Int {
        // Count URLs as 23 characters
        val urlRegex = "https?://\\S+".toRegex()
        var characterCount = text.replace(urlRegex, " ".repeat(23)).length

        // Count emojis and other special characters properly
        characterCount += text.count { it.toString().codePointCount(0, it.toString().length) > 1 }

        return characterCount
    }

}