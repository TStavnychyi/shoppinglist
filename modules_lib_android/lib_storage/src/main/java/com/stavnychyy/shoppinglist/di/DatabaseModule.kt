package com.stavnychyy.shoppinglist.di

import android.content.Context
import androidx.room.Room
import com.stavnychyy.shoppinglist.AppDatabase
import com.stavnychyy.shoppinglist.DatabaseProvider
import com.stavnychyy.shoppinglist.shopping_list.ShoppingListDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabaseProvider(context: Context): DatabaseProvider {
        return Room.databaseBuilder(context, AppDatabase::class.java, "shoppingListDB")
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideShoppingListDao(databaseProvider: DatabaseProvider): ShoppingListDao {
        return databaseProvider.createShoppingListDao()
    }
}
