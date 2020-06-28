package com.stavnychyy.shoppinglist.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.stavnychyy.shoppinglist.R;
import com.stavnychyy.shoppinglist.di.ArchivedShoppingListComponentFactoryProvider;
import com.stavnychyy.shoppinglist.fragment.BaseFragment;
import com.stavnychyy.shoppinglist.presenter.ArchivedShoppingListPresenter;
import com.stavnychyy.shoppinglist.presenter.ArchivedShoppingListViewEntity;
import javax.inject.Inject;

public class ArchivedShoppingListFragment extends BaseFragment implements ArchivedShoppingListPresenter.View {

  public ArchivedShoppingListFragment() {
    super(R.layout.msla_archived_shopping_list_fragment);
  }

  @Inject
  ArchivedShoppingListPresenter presenter;

  private RecyclerView archivedShoppingList;

  private ArchivedShoppingListAdapter adapter = new ArchivedShoppingListAdapter();

  private View emptyListView;

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    ArchivedShoppingListComponentFactoryProvider provider =
      (ArchivedShoppingListComponentFactoryProvider) requireActivity();
    provider.provideArchivedListComponentFactory().create(this).inject(this);
  }

  @Override
  public void onCreate(@org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.loadData();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    changeToolbarTitle(getString(R.string.msla_archived_shopping_list_fragment_title));
    archivedShoppingList = view.findViewById(R.id.view_archived_list);
    emptyListView = view.findViewById(R.id.view_empty_list);
    initRecyclerView();
  }

  @Override
  public void submitArchivedShoppingList(PagedList<ArchivedShoppingListViewEntity> list) {
    showContentView(!list.isEmpty());
    adapter.submitList(list);
  }

  @Override
  public void onDestroy() {
    presenter.onDestroy();
    super.onDestroy();
  }

  private void initRecyclerView() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    archivedShoppingList.setLayoutManager(layoutManager);
    archivedShoppingList.setAdapter(adapter);
  }

  private void showContentView(boolean showContent) {
    if (showContent) {
      emptyListView.setVisibility(View.GONE);
      archivedShoppingList.setVisibility(View.VISIBLE);
    } else {
      emptyListView.setVisibility(View.VISIBLE);
      archivedShoppingList.setVisibility(View.GONE);
    }
  }
}