package com.itoll.githubusers.base

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    private var isViewCreated = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isViewCreated) {
            initViews()
            clickViews()
            executeInitTask()
            observes()
            isViewCreated = true
        }
    }

    abstract fun initViews()
    abstract fun clickViews()
    abstract fun executeInitTask()
    abstract fun observes()


    fun showProgressBar(progressBar: FrameLayout) {
         progressBar.visibility = View.VISIBLE
    }
    fun hideProgressBar( progressBar: FrameLayout) {
       progressBar.visibility = View.GONE
    }

    fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}