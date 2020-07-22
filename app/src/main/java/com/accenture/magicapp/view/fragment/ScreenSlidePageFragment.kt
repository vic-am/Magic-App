package com.accenture.magicapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.accenture.magicapp.R
import com.accenture.magicapp.viewmodel.DetailViewModel

class ScreenSlidePageFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_screen_slide_page, container, false)

        return root
    }
}