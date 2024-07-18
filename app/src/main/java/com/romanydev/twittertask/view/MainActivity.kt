package com.romanydev.twittertask.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.romanydev.twittertask.R
import com.romanydev.twittertask.data.model.TweetPayload
import com.romanydev.twittertask.databinding.ActivityMainBinding
import com.romanydev.twittertask.utlis.DataState
import com.romanydev.twittertask.utlis.showIndefiniteSnackBar
import com.romanydev.twittertask.viewmodel.TwitterViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val twitterViewModel: TwitterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val builder: StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        initView()

        getAccessToken()

    }

    private fun initView() {

        binding.cardTypedChar.setTitleText(getString(R.string.charTyped))
        binding.cardRemainingChar.setTitleText(getString(R.string.remainingChar))
        binding.cardTypedChar.setBodyText(getString(R.string.initialCount), isInitial = true)
        binding.cardRemainingChar.setBodyText(getString(R.string.initialCountRemaining), isInitial = true)


        binding.postContentText.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do something before text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    val tweetText = it.toString()
                    binding.cardTypedChar.setBodyText(tweetText)
                    binding.cardRemainingChar.setBodyText(tweetText, isRemaining = true)
                }
            }
        })

        binding.postTweet.setOnClickListener {
            val tweetText = binding.postContentText.text.toString()
            if (binding.postContentText.text!!.isNotEmpty()) {
                postTweet(tweetText)
                showIndefiniteSnackBar(getString(R.string.problem))
            } else {
                showIndefiniteSnackBar(getString(R.string.pleaseFillData))
            }
        }

        binding.copyButton.setOnClickListener {
            if (binding.postContentText.text!!.isNotEmpty()){
                copyTextToClipboard()
            }
            else{
                showIndefiniteSnackBar(getString(R.string.noDataToCopy))
            }
        }

        binding.clearText.setOnClickListener {
            if (binding.postContentText.text!!.isNotEmpty()){
                binding.postContentText.text!!.clear()
            }else{
                showIndefiniteSnackBar(getString(R.string.noDataToClear))
            }
        }

        binding.backNav.setOnClickListener {
            finish()
        }
    }

    private fun postTweet(tweetText: String) {
        val payload = TweetPayload(tweetText)
        twitterViewModel.postTweet(payload)
        lifecycleScope.launchWhenStarted {
            twitterViewModel.tweetResponse.collect{
                when (it) {
                    is DataState.Loading -> {

                    }

                    is DataState.Empty ->{

                    }

                    is DataState.Success<*> -> {

                    }

                    is DataState.Error -> {

                    }
                }
            }
        }

    }

    private fun getAccessToken() {
        twitterViewModel.getAccessToken()
        lifecycleScope.launchWhenStarted {
            twitterViewModel.accessTokenResponse.collect {
                when (it) {
                    is DataState.Loading -> {

                    }

                    is DataState.Empty ->{

                    }

                    is DataState.Success<*> -> {

                    }

                    is DataState.Error -> {

                    }
                }
            }
        }
    }

    private fun copyTextToClipboard() {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", binding.postContentText.text.toString())
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
    }
}