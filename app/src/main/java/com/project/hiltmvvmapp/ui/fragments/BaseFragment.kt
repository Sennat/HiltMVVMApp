package com.project.hiltmvvmapp.ui.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.hiltmvvmapp.viewModels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment: Fragment() {

    protected val appViewModel by lazy {
        ViewModelProvider(this)[AppViewModel::class.java]
    }

}
