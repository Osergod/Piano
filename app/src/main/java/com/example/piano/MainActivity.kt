package com.example.piano

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // MutableList = tipo "push"
    private val sonidos = mutableListOf<MediaPlayer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // PUSH de sonidos
        sonidos.add(MediaPlayer.create(this, R.raw.do))
        sonidos.add(MediaPlayer.create(this, R.raw.re))
        sonidos.add(MediaPlayer.create(this, R.raw.mi))
        sonidos.add(MediaPlayer.create(this, R.raw.fa))
        sonidos.add(MediaPlayer.create(this, R.raw.sol))
        sonidos.add(MediaPlayer.create(this, R.raw.la))
        sonidos.add(MediaPlayer.create(this, R.raw.si))
        sonidos.add(MediaPlayer.create(this, R.raw.do2))

        sonidos.add(MediaPlayer.create(this, R.raw.dos))
        sonidos.add(MediaPlayer.create(this, R.raw.res))
        sonidos.add(MediaPlayer.create(this, R.raw.fas))
        sonidos.add(MediaPlayer.create(this, R.raw.sols))
        sonidos.add(MediaPlayer.create(this, R.raw.las))

        // Blancas
        findViewById<Button>(R.id.doBtn).setOnClickListener {
            reproducir(0)
        }

        findViewById<Button>(R.id.reBtn).setOnClickListener {
            reproducir(1)
        }

        findViewById<Button>(R.id.miBtn).setOnClickListener {
            reproducir(2)
        }

        findViewById<Button>(R.id.faBtn).setOnClickListener {
            reproducir(3)
        }

        findViewById<Button>(R.id.solBtn).setOnClickListener {
            reproducir(4)
        }

        findViewById<Button>(R.id.laBtn).setOnClickListener {
            reproducir(5)
        }

        findViewById<Button>(R.id.siBtn).setOnClickListener {
            reproducir(6)
        }

        findViewById<Button>(R.id.do2Btn).setOnClickListener {
            reproducir(7)
        }

        // Negras
        findViewById<Button>(R.id.dosBtn).setOnClickListener {
            reproducir(8)
        }

        findViewById<Button>(R.id.resBtn).setOnClickListener {
            reproducir(9)
        }

        findViewById<Button>(R.id.fasBtn).setOnClickListener {
            reproducir(10)
        }

        findViewById<Button>(R.id.solsBtn).setOnClickListener {
            reproducir(11)
        }

        findViewById<Button>(R.id.lasBtn).setOnClickListener {
            reproducir(12)
        }
    }

    private fun reproducir(indice: Int) {
        val sonido = sonidos[indice]

        sonido.seekTo(0)
        sonido.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        for (sonido in sonidos) {
            sonido.release()
        }
    }
}