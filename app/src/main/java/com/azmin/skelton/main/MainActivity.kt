package com.azmin.skelton.main

import android.os.Bundle
import androidx.navigation.findNavController
import com.azmin.skelton.R
import core.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findNavController(R.id.fragment).navigate(R.id.fragmentHome)
    }

}
