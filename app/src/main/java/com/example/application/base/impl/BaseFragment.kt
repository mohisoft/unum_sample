package com.example.application.base.impl

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.application.base.BaseViewModel

abstract class BaseFragment<B: ViewBinding> : Fragment() {

    protected lateinit var binding: B

    abstract val bindingInflater: (LayoutInflater) -> B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        Log.d(javaClass.simpleName, "onCreateView")

        binding = bindingInflater(layoutInflater)
        return binding.root
    }

}