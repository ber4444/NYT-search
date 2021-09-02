package com.example.gabor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gabor.databinding.ActivityMainBinding
import com.example.gabor.fragments.NewsListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: NewsListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragment = NewsListFragment()

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, fragment, "news_list")
            transaction.commit()
        }
    }
}