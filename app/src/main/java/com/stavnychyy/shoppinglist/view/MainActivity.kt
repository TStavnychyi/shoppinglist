package com.stavnychyy.shoppinglist.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.stavnychyy.shoppinglist.R
import com.stavnychyy.shoppinglist.ShoppingListApplication
import com.stavnychyy.shoppinglist.archivedshoppinglist.di.ArchivedShoppingListComponent
import com.stavnychyy.shoppinglist.archivedshoppinglist.di.ArchivedShoppingListComponentFactoryProvider
import com.stavnychyy.shoppinglist.shoppinglists.di.ShoppingListComponent
import com.stavnychyy.shoppinglist.shoppinglists.di.ShoppingListComponentProvider
import com.stavnychyy.shoppinglist.shoppinglists.di.activity.ActivityComponent
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
    val appBarConfiguration = AppBarConfiguration(
      setOf(R.id.shopping_list_fragment, R.id.archived_shopping_list_fragment)
    )
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
  }

  override fun provideShoppingListComponent(): ShoppingListComponent {
    return activityComponent.shoppingListComponentFactory().create()
  }

  override fun provideArchivedListComponentFactory(): ArchivedShoppingListComponent.Factory {
    return activityComponent.archivedShoppingListComponentFactory()
  }

  override fun onSupportNavigateUp(): Boolean {
    return NavigationUI.navigateUp(navController, null)
  }

  private fun initializeActivityComponent(): ActivityComponent {
    return (application as ShoppingListApplication)
      .appComponent
      .activityComponentFactory()
      .create(this)
  }
}
