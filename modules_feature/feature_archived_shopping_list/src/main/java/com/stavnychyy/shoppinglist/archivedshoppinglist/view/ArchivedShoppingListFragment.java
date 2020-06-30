package com.stavnychyy.shoppinglist.archivedshoppinglist.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.stavnychyy.shoppinglist.archivedshoppinglist.R;
import com.stavnychyy.shoppinglist.archivedshoppinglist.di.ArchivedShoppingListComponentFactoryProvider;
import com.stavnychyy.shoppinglist.archivedshoppinglist.presenter.ArchivedShoppingListPresenter;
import com.stavnychyy.shoppinglist.archivedshoppinglist.presenter.ArchivedShoppingListViewEntity;
import com.stavnychyy.shoppinglist.archivedshoppinglist.view.adapter.ArchivedShoppingListAdapter;
import com.stavnychyy.shoppinglist.common.navigation.AppNavigator;
import dagger.Lazy;
import javax.inject.Inject;

public class ArchivedShoppingListFragment extends Fragment implements ArchivedShoppingListPresenter.View {

  @Inject
  Lazy<ArchivedShoppingListPresenter> presenter;

  @Inject
  Lazy<AppNavigator> appNavigator;

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

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
                           @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.msla_archived_shopping_list_fragment, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    archivedShoppingList = view.findViewById(R.id.view_archived_list);
    emptyListView = view.findViewById(R.id.view_empty_list);
    presenter.get().loadData();
    presenter.get().setAppNavigator(appNavigator.get());
    changeFragmentTitle();
    initRecyclerView();
  }

  @Override
  public void submitArchivedShoppingList(PagedList<ArchivedShoppingListViewEntity> list) {
    showContentView(!list.isEmpty());
    adapter.submitList(list);
  }

  @Override
  public void onDestroy() {
    presenter.get().onDestroy();
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

  private void changeFragmentTitle() {
    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.msla_archived_shopping_list_fragment_title);
  }
}