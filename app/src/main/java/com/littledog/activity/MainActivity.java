package com.littledog.activity;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;

import com.littledog.bitsandpizzsa.R;

public class MainActivity extends Activity {
    private String[] titles;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int currentPosition=0;
    private ShareActionProvider shareActionProvider;
    /*内部类，一个监听器,点击抽屉的一项，调用监听器*/
    private class DrawerItemClickListener implements  ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view,int position,long id){
            selectItem(position);/*选择fragment实例*/
        }
    }//方法1
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titles=getResources().getStringArray(R.array.titles);
        drawerList=(ListView)findViewById(R.id.drawer);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawerList.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                titles));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());//方法1实现

        if(savedInstanceState!=null){
            currentPosition=savedInstanceState.getInt("position");
            setActionBarTitle(currentPosition);
        }else{
            selectItem(0);
        }

        drawerToggle =new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer){
            @Override
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerOpened(View view){
                super.onDrawerOpened(view);
                invalidateOptionsMenu();
            }

        };
        drawerLayout.setDrawerListener(drawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        getFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener(){
                    @Override
                    public void onBackStackChanged() {
                        FragmentManager fragMan=getFragmentManager();
                        Fragment fragment=fragMan.findFragmentByTag("visible_fragment");
                        if(fragment instanceof TopFragment){
                            currentPosition=0;
                        }
                        if(fragment instanceof PizzaMaterialFragment){
                            currentPosition=1;
                        }
                        if(fragment instanceof PastaFragment){
                            currentPosition=2;
                        }
                        if(fragment instanceof StoresFragment){
                            currentPosition=3;
                        }
                        setActionBarTitle(currentPosition);
                        drawerList.setItemChecked(currentPosition,true);
                    }
                }
        );
    }
    @Override
    protected void onPostCreate(Bundle saveInstanceState){
        super.onPostCreate(saveInstanceState);
        drawerToggle.syncState();//同步状态
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);//接收参数
    }
    public boolean onPrepareOptionsMenu(Menu menu){
        boolean drawerOpen=drawerLayout.isDrawerOpen(drawerList);
        menu.findItem(R.id.action_share).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    /*选择fragment*/
    private void selectItem(int position){
        currentPosition=position;
        Fragment fragment;
        switch(position){
            case 1:
                fragment= new PizzaMaterialFragment();
                break;
            case 2:
                fragment=new PastaFragment();
                break;
            case 3:
                fragment=new StoresFragment();
                break;
            default:
                fragment=new TopFragment();
        }
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame,fragment,"visible_fragment");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        setActionBarTitle(position);
        drawerLayout.closeDrawer(drawerList);
    }
    /*设置action bar标题*/
    private void setActionBarTitle(int position){
        String title;
        if(position==0){
            title=getResources().getString(R.string.app_name);
        }else{
            title=titles[position];
        }
        getActionBar().setTitle(title);
    }
    @Override/*给菜单充气*/
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem menuItem=menu.findItem(R.id.action_share);
        shareActionProvider=(ShareActionProvider)menuItem.getActionProvider();
        setIntent("This is a Text!");
        return super.onCreateOptionsMenu(menu);

    }
    private void setIntent(String text){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,text);
        shareActionProvider.setShareIntent(intent);
    }
    @Override/*选择菜单项*/
    public boolean onOptionsItemSelected(MenuItem item){
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch(item.getItemId()){
            case R.id.action_create_order:
                Intent intent=new Intent(this,OrderActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("position",currentPosition);

    }
}
