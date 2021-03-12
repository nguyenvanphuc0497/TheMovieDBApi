package vn.nvp.themoviedbapi.ui

import android.os.Bundle
import vn.nvp.themoviedbapi.R
import vn.nvp.themoviedbapi.extentions.replaceFragment
import vn.nvp.themoviedbapi.ui.base.BaseActivity
import vn.nvp.themoviedbapi.ui.home.HomeFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }


    private fun initView() {
        replaceFragment(HomeFragment(), addBackStack = false)
    }
}
