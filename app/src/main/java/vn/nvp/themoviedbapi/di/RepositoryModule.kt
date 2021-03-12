package vn.nvp.themoviedbapi.di

import org.koin.dsl.module
import vn.nvp.themoviedbapi.data.repository.MovieRepository

val repositoryModule = module {
    single { MovieRepository(get()) }
}
