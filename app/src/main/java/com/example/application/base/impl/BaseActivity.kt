package com.example.application.base.impl

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<Binding : ViewBinding> : FragmentActivity() {

    protected lateinit var binding: Binding

    abstract val bindingInflater: (LayoutInflater) -> Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(javaClass.simpleName, "onCreate")

        binding = bindingInflater(layoutInflater)
        setContentView(binding.root)
    }
}