package com.example.mengenalhewan

import android.media.MediaPlayer
import android.os.Bundle
import android.view.Gravity
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    private val daftarHewan = listOf(

        Hewan(
            "Kucing",
            R.drawable.kucing,
            R.raw.kucing
        ),

        Hewan(
            "Anjing",
            R.drawable.anjing,
            R.raw.anjing
        ),

        Hewan(
            "Sapi",
            R.drawable.sapi,
            R.raw.sapi
        ),

        Hewan(
            "Ayam",
            R.drawable.ayam,
            R.raw.ayam
        ),

        Hewan(
            "Burung",
            R.drawable.burung,
            R.raw.burung
        ),

        Hewan(
            "Singa",
            R.drawable.singa,
            R.raw.singa
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val gridHewan =
            findViewById<GridLayout>(R.id.gridHewan)

        daftarHewan.forEach { hewan ->

            val card = buatCardHewan(hewan)

            gridHewan.addView(card)
        }
    }

    private fun buatCardHewan(
        hewan: Hewan
    ): LinearLayout {

        val card = LinearLayout(this)

        card.orientation = LinearLayout.VERTICAL
        card.gravity = Gravity.CENTER

        card.setPadding(
            16,
            16,
            16,
            16
        )

        card.setBackgroundResource(
            android.R.drawable.dialog_holo_light_frame
        )

        val params = GridLayout.LayoutParams()

        params.width = 0

        params.height =
            LinearLayout.LayoutParams.WRAP_CONTENT

        params.columnSpec =
            GridLayout.spec(
                GridLayout.UNDEFINED,
                1f
            )

        params.setMargins(
            12,
            12,
            12,
            12
        )

        card.layoutParams = params

        val imageView = ImageView(this)

        imageView.setImageResource(
            hewan.gambar
        )

        imageView.layoutParams =
            LinearLayout.LayoutParams(
                220,
                220
            )

        imageView.scaleType =
            ImageView.ScaleType.CENTER_CROP

        val textView = TextView(this)

        textView.text = hewan.nama

        textView.textSize = 20f

        textView.setTextColor(
            android.graphics.Color.parseColor(
                "#1A237E"
            )
        )

        textView.gravity = Gravity.CENTER

        textView.setPadding(
            0,
            12,
            0,
            8
        )

        card.addView(imageView)
        card.addView(textView)

        card.setOnClickListener {

            putarSuara(hewan.suara)
        }

        return card
    }

    private fun putarSuara(suara: Int) {

        mediaPlayer?.stop()
        mediaPlayer?.release()

        mediaPlayer =
            MediaPlayer.create(this, suara)

        // Volume maksimal
        mediaPlayer?.setVolume(
            1.0f,
            1.0f
        )

        mediaPlayer?.start()

        mediaPlayer?.setOnCompletionListener {

            it.release()

            mediaPlayer = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mediaPlayer?.release()

        mediaPlayer = null
    }
}