package com.zybooks.hc24_luuk_crawford_senior_project

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.zybooks.hc24_luuk_crawford_senior_project.databinding.ActivitySelectionBinding
import com.zybooks.hc24_luuk_crawford_senior_project.databinding.ActivitySidesBinding

class SidesActivity : AppCompatActivity(){
    private lateinit var binding: ActivitySidesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySidesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val image = intent.getStringExtra("image")
        val side = intent.getStringExtra("side")
        val price = intent.getStringExtra("price")




    }

}
