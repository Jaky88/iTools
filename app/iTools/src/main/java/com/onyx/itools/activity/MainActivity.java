package com.onyx.itools.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.onyx.itools.R;
import com.onyx.itools.fragment.MainFragment;
import com.onyx.itools.fragment.MenuFragment;

import java.util.ArrayList;

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
    private FragmentManager mManager;
    private Fragment mCurrentFragment;
    public static String MAIN_FRAGMENT ="main_fragment";
    public static String MENU_FRAGMENT ="menu_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);
        mTitle = (String) getTitle();
        ImageView headerView = (ImageView) getLayoutInflater().inflate(R.layout.drawer_list_header_view, null);
        LinearLayout footerView = (LinearLayout) getLayoutInflater().inflate(R.layout.drawer_list_footer_view, null);
        mDrawerList.addHeaderView(headerView);
        mDrawerList.addFooterView(footerView);
        setDefaultFragment();
    }

    private void initData() {
        menuLists = new ArrayList<String>();
        for (int i = 0; i < 5; i++) menuLists.add("菜单0" + i);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menuLists);
        mDrawerList.setAdapter(adapter);
    }

    private void initEvent() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.drawable.ic_drawer, R.string.drawer_open,R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mActionBar.setTitle("请选择");
                supportInvalidateOptionsMenu(); // Call onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mActionBar.setTitle(mTitle);
                supportInvalidateOptionsMenu();
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
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
        if(position ==0 || position == menuLists.size()+1) return;
        showSpecifiedFragment(MENU_FRAGMENT, position);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    private void setDefaultFragment() {
        mManager = getSupportFragmentManager();
        mCurrentFragment = new MainFragment();
        mManager.beginTransaction().replace(R.id.main_container, mCurrentFragment,MAIN_FRAGMENT)
                .commit();
    }

    public MainFragment getMainFragment(){
        return  (MainFragment) mManager.findFragmentByTag(MAIN_FRAGMENT);
    }

    public MenuFragment getMenuFragment(int pos){
        return  (MenuFragment) mManager.findFragmentByTag(MENU_FRAGMENT+pos);
    }

    public Fragment showSpecifiedFragment(String tag,int pos){
        mManager = getSupportFragmentManager();
        if(mCurrentFragment !=null){
            mManager.beginTransaction().hide(mCurrentFragment).commit();
        }
        mCurrentFragment = mManager.findFragmentByTag(tag);
        if(mCurrentFragment == null){
            if(MAIN_FRAGMENT.equals(tag)){
                mCurrentFragment = new MainFragment();
            }else if(tag!=null && tag.contains(MENU_FRAGMENT+pos)){
                mCurrentFragment = new MenuFragment();
                Bundle args = new Bundle();
                args.putString("text", menuLists.get(pos-1));
                mCurrentFragment.setArguments(args);
            }
            mManager.beginTransaction()
                    .add(R.id.main_container, mCurrentFragment,tag)
                    .commit();
        }
        mManager.beginTransaction().show(mCurrentFragment).commit();
        return  mCurrentFragment;
    }

}
