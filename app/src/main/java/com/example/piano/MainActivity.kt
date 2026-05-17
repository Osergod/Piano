package com.example.piano

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
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

    // Inicializar el SoundPool con los atributos de audio
    private fun setupSoundPool() {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(10)
            .setAudioAttributes(audioAttributes)
            .build()
    }

    // Cargar todos los sonidos de cada tecla
    private fun loadSounds() {
        val keySoundPairs = listOf(
            R.id.c2Btn to R.raw.c2,
            R.id.c2SharpBtn to R.raw.c22,
            R.id.d2Btn to R.raw.d2,
            R.id.d2SharpBtn to R.raw.d22,
            R.id.e2Btn to R.raw.e2,
            R.id.f2Btn to R.raw.f2,
            R.id.f2SharpBtn to R.raw.f22,
            R.id.g2Btn to R.raw.g2,
            R.id.g2SharpBtn to R.raw.g22,
            R.id.a2Btn to R.raw.a2,
            R.id.a2SharpBtn to R.raw.a22,
            R.id.b2Btn to R.raw.b2,
            R.id.c3Btn to R.raw.c3,
            R.id.c3SharpBtn to R.raw.c33,
            R.id.d3Btn to R.raw.d3,
            R.id.d3SharpBtn to R.raw.d33,
            R.id.e3Btn to R.raw.e3,
            R.id.f3Btn to R.raw.f3,
            R.id.f3SharpBtn to R.raw.f33,
            R.id.g3Btn to R.raw.g3,
            R.id.g3SharpBtn to R.raw.g33,
            R.id.a3Btn to R.raw.a3,
            R.id.a3SharpBtn to R.raw.a33,
            R.id.b3Btn to R.raw.b3,
            R.id.c4Btn to R.raw.c4
        )

        for ((keyId, rawId) in keySoundPairs) {
            soundMap[keyId] = soundPool.load(this, rawId, 1)
        }
    }

    // Asignar el listener tactil a cada tecla
    private fun setupKeys() {
        for (keyId in soundMap.keys) {
            val btn = findViewById<ImageButton>(keyId)
            btn.setOnTouchListener(createKeyTouchListener(keyId))
        }
    }

    // Crear el listener para gestionar el toque y multi-touch
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

    // Reproducir el sonido de la tecla
    private fun playSound(keyId: Int) {
        val soundId = soundMap[keyId] ?: return
        soundPool.play(soundId, 1f, 1f, 1, 0, 1f)
    }

    // Liberar recursos al cerrar la app
    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}
