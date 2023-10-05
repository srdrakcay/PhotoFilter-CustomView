package com.serdar.photofilter_customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.serdar.photofilter_customview.data.FilteredImages
import com.serdar.photofilter_customview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.customFilterView.setFilter(FilteredImages(
            imageView = R.drawable.un_filtered,
            filtered1Value = 1f,
            filtered2Value = 1f,
            filtered3Value = 2f))
    }
}