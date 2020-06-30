package com.stavnychyy.shoppinglist.di.activity

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.stavnychyy.shoppinglist.R
import com.stavnychyy.shoppinglist.common.navigation.AppNavigator
import com.stavnychyy.shoppinglist.navigation.InstalledAppNavigator
import com.stavnychyy.shoppinglist.view.MainActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun provideAppNavigator(navController: NavController): AppNavigator {
        return InstalledAppNavigator(navController)
    }

    @Provides
    @ActivityScope
    fun provideNavigationController(activity: MainActivity): NavController {
        return activity.findNavController(R.id.view_host_fragment)
    }
}
