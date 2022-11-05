package mx.com.idel.my.diplomado.ejerciciosclase.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import mx.com.idel.my.diplomado.R

class TapsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taps)

        var taps = findViewById<TabLayout>(R.id.c4tab)

        taps.addTab(taps.newTab().setIcon(android.R.drawable.btn_star))
        taps.addTab(taps.newTab().setText("Opción 1"))
        taps.addTab(taps.newTab().setText("Opción 2"))

        taps.tabGravity = TabLayout.GRAVITY_FILL
    }
}