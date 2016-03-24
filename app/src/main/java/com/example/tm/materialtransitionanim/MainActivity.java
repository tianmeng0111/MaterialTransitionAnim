package com.example.tm.materialtransitionanim;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private int[] pic = new int[]{
            R.drawable.drive, R.drawable.fengkuang, R.drawable.fox, R.drawable.hu, R.drawable.ogmf,
            R.drawable.panda, R.drawable.zhudi, R.drawable.shulan, R.drawable.tdikd
    };
    private List<AnimalBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initData();
    }

    private void initData() {
        for (int i = 0; i < pic.length; i++) {
            AnimalBean bean = new AnimalBean();
            bean.setImage(pic[i]);
            bean.setName("pic" + i);
            list.add(bean);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("image", data);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.setTransitionName(getString(R.string.transition_name));
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                            view, view.getTransitionName());
                    startActivityForResult(intent, 1, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
