package com.stavnychyy.shoppinglist.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.stavnychyy.shoppinglist.R
import com.stavnychyy.shoppinglist.ShoppingListApplication
import com.stavnychyy.shoppinglist.di.ArchivedShoppingListComponent
import com.stavnychyy.shoppinglist.di.ArchivedShoppingListComponentFactoryProvider
import com.stavnychyy.shoppinglist.di.ShoppingListComponent
import com.stavnychyy.shoppinglist.di.ShoppingListComponentProvider
import com.stavnychyy.shoppinglist.di.activity.ActivityComponent
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

    NavigationUI.setupActionBarWithNavController(this, navController)

    val destinationsWithNoToolbarButton: Array<Int> = arrayOf(
      R.id.shoppingListDetailsFragment,
      R.id.archived_shopping_list_fragment
    )
    navController.addOnDestinationChangedListener { _, destination, _ ->
      view_toolbar.menu.findItem(
        R.id.archived_shopping_list_fragment
      )?.isVisible = !destinationsWithNoToolbarButton.any { it == destination.id }
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.msla_toolbar_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.archived_shopping_list_fragment) {
      navController.navigate(ShoppingListFragmentDirections.actionShoppingListFragmentToArchivedShoppingListFragment())
      return true
    }
    return super.onOptionsItemSelected(item)
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
