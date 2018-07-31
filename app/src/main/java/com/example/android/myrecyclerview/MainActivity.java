package com.example.android.myrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCategory;
    private ArrayList<President>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = (RecyclerView)findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(PresidentData.getListData());

        showRecyclerList();
    } // mengatur file main.xml


//---------------------------------------------------------------------------------------------------------
    private void showSelectedPresident(President president){
        Toast.makeText(this, "Kamu memilih "+president.getName(), Toast.LENGTH_SHORT).show();
    }// utk membuat tost pada tampilan List dan Grid
    // ketika akan memilih president yang diinginkan, melibatkan ItemClickSupport.java

    private void showRecyclerList(){
        //------------------------------------------------------------------------------------------------------------------
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        //kita hanya perlu set nilai pada method setLayoutManager() saja untuk menentukan bagaimana RecyclerView ditampilkan.
        ListPresidentAdapter listPresidentAdapter = new ListPresidentAdapter(this);
        listPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(listPresidentAdapter);
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedPresident(list.get(position));
            }
        });
    } /* untuk menampilkan RecyclerView dalam bentuk list, melibatkan file ListPresidentAdapter.java*/

    private void showRecyclerGrid(){
        //------------------------------------------------------------------------------------------------------------------
        rvCategory.setLayoutManager(new GridLayoutManager(this, 3));
        //kita hanya perlu set nilai pada method setLayoutManager() saja untuk menentukan bagaimana RecyclerView ditampilkan.
        GridPresidentAdapter gridPresidentAdapter = new GridPresidentAdapter(this);
        gridPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(gridPresidentAdapter);
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedPresident(list.get(position));
            }
        });
    }// untuk menampilkan RecyclerView dalam bentuk grid, melibatkan file GridPresidentAdapter.java

    //-----------------------------------------------------------------------------------------------------------------

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewPresidentAdapter cardViewPresidentAdapter = new CardViewPresidentAdapter(this);
        cardViewPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(cardViewPresidentAdapter);
    }// untuk menampilkan RecyclerView dalam bentuk grid, melibatkan file GridPresidentAdapter.java
    // dan CustomOnItemClickListener.java saat tombol favorit dan share ditekan




    //-----------------------------------------------------------------------
    //pada bagian bawah ini untuk menatur, menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = null;
        switch (item.getItemId()){
            case R.id.action_list:
                showRecyclerList();
                title = "Mode List";
                break;
            case R.id.action_grid:
                showRecyclerGrid();
                title = "Mode Grid";
                break;
            case R.id.action_cardview:
                title = "Mode CardView";
                showRecyclerCardView();
                break;
        }
        setActionBarTitle(title);
        return super.onOptionsItemSelected(item);
    }



    //------------------------------------------
    //method utk mengganti titleBar
    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

}


