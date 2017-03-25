package com.onyx.itools.activity;

import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.onyx.itools.Fragment.ContentFragment;
import com.onyx.itools.R;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/3/25 0025,2:05
 * @Version: V1.0
 * @Description:
 */
public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ArrayList<String> menuLists;
    private ArrayAdapter<String> adapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mTitle;
    ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        menuLists = new ArrayList<String>();
        for (int i = 0; i < 5; i++) menuLists.add("菜单0" + i);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menuLists);
        mDrawerList.setAdapter(adapter);
    }

    private void initView() {
        mActionBar = getSupportActionBar();
        mTitle = (String) getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.mipmap.ic_launcher, R.string.drawer_open,R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mActionBar.setTitle("请选择");
                invalidateOptionsMenu(); // Call onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mActionBar.setTitle(mTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if(mActionBar != null){
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
        }
    }

    private void initEvent() {
        mDrawerList.setOnItemClickListener(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean isDrawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.menu_search).setVisible(!isDrawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()) {
            case R.id.menu_search:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri uri = Uri.parse("http://www.baidu.com");
                intent.setData(uri);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
        Fragment contentFragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString("text", menuLists.get(position));
        contentFragment.setArguments(args);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, contentFragment).commit();
        mDrawerLayout.closeDrawer(mDrawerList);
    }

}
