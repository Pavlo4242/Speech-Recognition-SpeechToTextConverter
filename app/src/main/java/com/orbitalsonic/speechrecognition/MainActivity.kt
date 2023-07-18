package com.orbitalsonic.speechrecognition

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.orbitalsonic.speechrecognition.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),onRecognitionListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var speechToTextConverter: SpeechToTextConverter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        speechToTextConverter = SpeechToTextConverter(this,this)

        // Request microphone permission if not granted already
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.RECORD_AUDIO),
                1
            )
        }
        binding.btnSpeak.setOnClickListener {
            // Start listening
//            speechToTextConverter.startListening("en-US")
//            speechToTextConverter.startListening("es-ES")
//            speechToTextConverter.startListening("hi")
//            speechToTextConverter.startListening("ar")
//            speechToTextConverter.startListening("ur")
            speechToTextConverter.startListening("en")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop listening
        speechToTextConverter.stopListening()
    }

    override fun onReadyForSpeech() {
        binding.btnSpeak.visibility = View.GONE
        binding.tvResult.visibility = View.GONE
        binding.tvMessage.visibility = View.VISIBLE
        binding.lavMicAnimation.visibility = View.VISIBLE
    }
    override fun onBeginningOfSpeech() {}
    override fun onEndOfSpeech() {}
    override fun onError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        binding.btnSpeak.visibility = View.VISIBLE
        binding.tvResult.visibility = View.VISIBLE
        binding.tvResult.text = "Sorry, Please try again"
        binding.tvMessage.visibility = View.GONE
        binding.lavMicAnimation.visibility = View.GONE
    }

    override fun onResults(results: String) {
        binding.btnSpeak.visibility = View.VISIBLE
        binding.tvResult.visibility = View.VISIBLE
        binding.tvMessage.visibility = View.GONE
        binding.lavMicAnimation.visibility = View.GONE
        binding.tvResult.text = results
    }


}
