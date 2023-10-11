package com.example.hw13

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    lateinit var play: Button
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        play = findViewById(R.id.play)
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build())
            setDataSource("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-10.mp3")
            prepare()
        }

        play.setOnClickListener{
            if (isPlaying) {
                // Stop the playback
                mediaPlayer.pause()
                isPlaying = false
                play.text = "Play"
            } else {
                // Start or resume playback
                mediaPlayer.start()
                isPlaying = true
                play.text = "Pause"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}