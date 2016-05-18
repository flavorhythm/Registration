package com.example.zyuki.echoregistration;

import android.accounts.AccountManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.AccountPicker;

import java.util.List;

import adapter.CardsHolder;
import adapter.PagerAdapter;
import data.Card;
import data.DataAccessObject;
import fragment.DialogConfirm;
import fragment.DialogRouter;
import fragment.FragmentMain;
import util.Constant;

public class MainActivity extends AppCompatActivity implements FragmentMain.Callback,
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,
        DialogConfirm.Callback {

    public static final int EMAIL_REQUEST_CODE = 1;

    private MaterialViewPager materialHeader;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    private DataAccessObject dataAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataAccess = new DataAccessObject();

        setupHeader();

        try {
            Intent intent = AccountPicker.newChooseAccountIntent(
                    null,
                    null,
                    new String[] {GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE},
                    false,
                    null,
                    null,
                    null,
                    null
            );

            startActivityForResult(intent, EMAIL_REQUEST_CODE);
        } catch(ActivityNotFoundException e) {
            e.printStackTrace();
        }

        FloatingActionButton floatingBtn = (FloatingActionButton) findViewById(R.id.main_btn_floating);
        if (floatingBtn != null) {floatingBtn.setOnClickListener(this);}
        else {displayErrorBar();}

        RelativeLayout settings = (RelativeLayout)findViewById(R.id.main_btn_settings);
        if (settings != null) {settings.setOnClickListener(this);}
        else {displayErrorBar();}
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.main_btn_floating:
                DialogRouter.showAddDialog(MainActivity.this);
                break;
            case R.id.main_btn_settings:
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                intent.putExtra(Constant.BUNDLE_TITLE, Constant.SETTINGS);
                startActivity(intent);

                drawer.closeDrawer(GravityCompat.START);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public List<Card> getCards() {return dataAccess.getCards();}

    @Override
    public View.OnClickListener getListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                intent.putExtra(Constant.BUNDLE_TITLE, ((CardsHolder)v.getTag()).getCardTitle());
                Log.v("bundleExtra", ((CardsHolder)v.getTag()).getCardTitle());

                startActivity(intent);
            }
        };
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showSnackbar(boolean success) {
        String displayText = success ? getResources().getString(R.string.register_OK) :
                getResources().getString(R.string.register_NG);

        //move FAB with this
        Snackbar.make(drawer, displayText, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == EMAIL_REQUEST_CODE && resultCode == RESULT_OK) {
            TextView email = (TextView)findViewById(R.id.drawer_text_email);
            if(email != null) {
                email.setText(data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME));
            }
        }
    }

    private void displayErrorBar() {
        Snackbar.make(materialHeader, "Issues loading some views", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void setupHeader() {
        //TODO: Add tabs back in when more content is available
        materialHeader = (MaterialViewPager)findViewById(R.id.main_header_material);

        if(materialHeader != null) {
            materialHeader.getViewPager().setAdapter(new PagerAdapter(getSupportFragmentManager()));

            toolbar = materialHeader.getToolbar();
        }
//        materialHeader.getPagerTitleStrip().setViewPager(materialHeader.getViewPager());
//        materialHeader.getPagerTitleStrip().setVisibility(View.GONE);

        if(toolbar != null) {
            toolbar.setTitleTextColor(
                    ContextCompat.getColor(getApplicationContext(), R.color.white)
            );

            setSupportActionBar(toolbar);

            setupDrawer();

            ActionBar actionBar = getSupportActionBar();
            if(actionBar != null) {
                Log.v("AndroidTest", "set Actionbar");

                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);
            }
        }

        //materialHeader.getHeaderBackgroundContainer().setBackgroundResource(R.drawable.header);
    }

    private void setupDrawer() {
        drawer = (DrawerLayout)findViewById(R.id.main_drawer);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainActivity.this,
                drawer,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        );

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView drawerNav = (NavigationView)findViewById(R.id.main_nav);
        drawerNav.setNavigationItemSelectedListener(this);
    }
}
