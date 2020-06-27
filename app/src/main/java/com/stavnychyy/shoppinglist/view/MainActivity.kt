package com.stavnychyy.shoppinglist.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.stavnychyy.shoppinglist.R
import com.stavnychyy.shoppinglist.ShoppingListApplication
import com.stavnychyy.shoppinglist.di.ShoppingListComponent
import com.stavnychyy.shoppinglist.di.ShoppingListComponentProvider
import com.stavnychyy.shoppinglist.di.activity.ActivityComponent
import kotlinx.android.synthetic.main.msla_activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ShoppingListComponentProvider {

    @Inject
    lateinit var navController: NavController

    private lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = initializeActivityComponent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.msla_activity_main)
        setSupportActionBar(view_toolbar)

        activityComponent.inject(this)

        view_bottom_nav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun provideShoppingListComponent(): ShoppingListComponent {
        return activityComponent.shoppingListComponentFactory().create()
    }

    private fun initializeActivityComponent(): ActivityComponent {
        return (application as ShoppingListApplication)
            .appComponent
            .activityComponentFactory()
            .create(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}
