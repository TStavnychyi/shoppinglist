package com.stavnychyy.shoppinglist.view

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.stavnychyy.shoppinglist.R
import com.stavnychyy.shoppinglist.ShoppingListApplication
import com.stavnychyy.shoppinglist.di.ArchivedShoppingListComponent
import com.stavnychyy.shoppinglist.di.ArchivedShoppingListComponentFactoryProvider
import com.stavnychyy.shoppinglist.di.ShoppingListComponent
import com.stavnychyy.shoppinglist.di.ShoppingListComponentProvider
import com.stavnychyy.shoppinglist.di.activity.ActivityComponent
import com.stavnychyy.shoppinglist.extensions.visibleOrGone
import kotlinx.android.synthetic.main.msla_activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ShoppingListComponentProvider,
  ArchivedShoppingListComponentFactoryProvider {

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

    onBackPressedDispatcher.addCallback {
      NavigationUI.navigateUp(navController, null)
    }

    val destinationsWithNoBottomNavigation: Array<Int> = arrayOf(
      R.id.shoppingListDetailsFragment
    )
    navController.addOnDestinationChangedListener { _, destination, arguments ->
      view_bottom_nav.visibleOrGone(destinationsWithNoBottomNavigation.all { it != destination.id })
    }
  }

  override fun provideShoppingListComponent(): ShoppingListComponent {
    return activityComponent.shoppingListComponentFactory().create()
  }

  override fun provideArchivedListComponentFactory(): ArchivedShoppingListComponent.Factory {
    return activityComponent.archivedShoppingListComponentFactory()
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
