package com.orbitalsonic.speechrecognition


interface onRecognitionListener {
    fun onReadyForSpeech()
    fun onBeginningOfSpeech()
    fun onEndOfSpeech()
    fun onError(error: String)
    fun onResults(results: String)
}