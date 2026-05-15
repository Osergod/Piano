package com.example.piano

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var soundPool: SoundPool
    private val soundMap = mutableMapOf<Int, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupSoundPool()
        loadSounds()
        setupKeys()
    }

    private fun setupSoundPool() {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(16)
            .setAudioAttributes(audioAttributes)
            .build()
    }

    private fun loadSounds() {
        soundMap[R.id.d2Btn] = soundPool.load(this, R.raw.d2, 1)
        soundMap[R.id.d2SharpBtn] = soundPool.load(this, R.raw.d22, 1)
        soundMap[R.id.e2Btn] = soundPool.load(this, R.raw.e2, 1)
        soundMap[R.id.f2Btn] = soundPool.load(this, R.raw.f2, 1)
        soundMap[R.id.f2SharpBtn] = soundPool.load(this, R.raw.f22, 1)
        soundMap[R.id.g2Btn] = soundPool.load(this, R.raw.g2, 1)
        soundMap[R.id.g2SharpBtn] = soundPool.load(this, R.raw.g22, 1)
        soundMap[R.id.a2Btn] = soundPool.load(this, R.raw.a2, 1)
        soundMap[R.id.a2SharpBtn] = soundPool.load(this, R.raw.a22, 1)
        soundMap[R.id.b2Btn] = soundPool.load(this, R.raw.b2, 1)
        soundMap[R.id.c3Btn] = soundPool.load(this, R.raw.c3, 1)
        soundMap[R.id.c3SharpBtn] = soundPool.load(this, R.raw.c33, 1)
        soundMap[R.id.d3Btn] = soundPool.load(this, R.raw.d3, 1)
        soundMap[R.id.d3SharpBtn] = soundPool.load(this, R.raw.d33, 1)
        soundMap[R.id.e3Btn] = soundPool.load(this, R.raw.e3, 1)
        soundMap[R.id.f3Btn] = soundPool.load(this, R.raw.f3, 1)
        soundMap[R.id.f3SharpBtn] = soundPool.load(this, R.raw.f33, 1)
        soundMap[R.id.g3Btn] = soundPool.load(this, R.raw.g3, 1)
        soundMap[R.id.g3SharpBtn] = soundPool.load(this, R.raw.g33, 1)
        soundMap[R.id.a3Btn] = soundPool.load(this, R.raw.a3, 1)
        soundMap[R.id.a3SharpBtn] = soundPool.load(this, R.raw.a33, 1)
        soundMap[R.id.b3Btn] = soundPool.load(this, R.raw.b3, 1)
        soundMap[R.id.c4Btn] = soundPool.load(this, R.raw.c4, 1)
    }

    private fun setupKeys() {
        val keyIds = listOf(
            R.id.d2Btn, R.id.d2SharpBtn,
            R.id.e2Btn,
            R.id.f2Btn, R.id.f2SharpBtn,
            R.id.g2Btn, R.id.g2SharpBtn,
            R.id.a2Btn, R.id.a2SharpBtn,
            R.id.b2Btn,
            R.id.c3Btn, R.id.c3SharpBtn,
            R.id.d3Btn, R.id.d3SharpBtn,
            R.id.e3Btn,
            R.id.f3Btn, R.id.f3SharpBtn,
            R.id.g3Btn, R.id.g3SharpBtn,
            R.id.a3Btn, R.id.a3SharpBtn,
            R.id.b3Btn,
            R.id.c4Btn
        )

        keyIds.forEach { keyId ->
            val keyButton = findViewById<Button>(keyId)
            keyButton.setOnTouchListener(createKeyTouchListener(keyId))
        }
    }

    private fun createKeyTouchListener(keyId: Int): View.OnTouchListener {
        return View.OnTouchListener { view, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN,
                MotionEvent.ACTION_POINTER_DOWN -> {
                    playSound(keyId)
                    view.isPressed = true
                    view.performClick()
                    true
                }

                MotionEvent.ACTION_UP,
                MotionEvent.ACTION_POINTER_UP,
                MotionEvent.ACTION_CANCEL -> {
                    view.isPressed = false
                    true
                }

                else -> false
            }
        }
    }

    private fun playSound(keyId: Int) {
        val soundId = soundMap[keyId] ?: return
        soundPool.play(soundId, 1f, 1f, 1, 0, 1f)
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}