package com.azmin.skelton.itemDetail

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


@Module(includes = [ItemDetailModule::class])
abstract class ItemDetailBuilderModule {

    @ContributesAndroidInjector(modules = [InjectorViewModel::class])
    abstract fun bind(): ItemDetailFragment

    /*Inject ViewModel started*/
    @Module
    class InjectorViewModel {
        @Provides
        fun provideViewModel(
            factory: ViewModelProvider.Factory,
            target: ItemDetailFragment
        ) = ViewModelProvider(target, factory).get(ItemDetailVM::class.java)
    }

}