package ru.stas.viewmodeldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.stas.viewmodeldemo.databinding.ActivityMainBinding
import ru.stas.viewmodeldemo.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}