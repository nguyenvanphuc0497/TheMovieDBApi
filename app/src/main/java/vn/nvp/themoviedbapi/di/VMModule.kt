package vn.nvp.themoviedbapi.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import vn.nvp.themoviedbapi.ui.detail.DetailViewModel
import vn.nvp.themoviedbapi.ui.home.HomeViewModel

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { DetailViewModel() }
}
