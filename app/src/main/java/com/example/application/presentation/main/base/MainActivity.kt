package com.example.application.presentation.main.base

import android.view.LayoutInflater
import com.example.application.base.impl.BaseActivity
import com.example.application.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding = { inflater ->
        ActivityMainBinding.inflate(inflater)
    }

}