package com.stavnychyy.shoppinglist.presenter;

import androidx.paging.PagedList;
import androidx.paging.RxPagedListBuilder;
import com.stavnychyy.shoppinglist.ShoppingList;
import com.stavnychyy.shoppinglist.ShoppingListId;
import com.stavnychyy.shoppinglist.model.ArchivedShoppingListRepository;
import com.stavnychyy.shoppinglist.navigation.AppNavigator;
import com.stavnychyy.shoppinglist.view.ArchivedShoppingListViewHolder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;

public class ArchivedShoppingListPresenter implements ArchivedShoppingListViewHolder.ArchiveShoppingListClickListeners {

  private View view;

  private ArchivedShoppingListRepository repository;

  private CompositeDisposable disposables = new CompositeDisposable();

  private AppNavigator appNavigator;

  @Inject
  public ArchivedShoppingListPresenter(
    View view,
    ArchivedShoppingListRepository repository,
    AppNavigator appNavigator
  ) {
    this.view = view;
    this.repository = repository;
    this.appNavigator = appNavigator;
  }

  public void loadData() {
    Disposable disposable = new RxPagedListBuilder<>(
      repository.getArchivedShoppingList().map(list -> ArchivedShoppingListViewEntity.create(list, this)), 10)
      .buildObservable()
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(list -> view.submitArchivedShoppingList(list));
    disposables.add(disposable);
  }

  public void onDestroy() {
    disposables.dispose();
    view = null;
  }

  @Override
  public void onRemoveArchiveShoppingListClick(ShoppingList shoppingList) {
    Disposable disposable = repository.deleteShoppingListWithItems(shoppingList)
      .subscribe();
    disposables.add(disposable);
  }

  @Override
  public void onUnarchiveShoppingListClick(ShoppingList shoppingList) {
    Disposable disposable = repository.updateShoppingList(shoppingList.changeArchiveStatus(false))
      .subscribe();
    disposables.add(disposable);
  }

  @Override
  public void onArchiveShoppingListClick(ShoppingListId shoppingListId) {
    appNavigator.opeShoppingListDetailsScreen(shoppingListId, true);
  }

  public interface View {
    void submitArchivedShoppingList(PagedList<ArchivedShoppingListViewEntity> list);
  }
}
