package com.example.administrator.missphoto;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.CommonDialog;
import cn.smssdk.gui.ContactsPage;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends Activity {

    private LinearLayout ll;
    private ImageButton mBtnHome, mBtnCamera, mBtnPost, mBtnRequest, mBtnMine;

    //声明Fragment属性
    private HomeFragment mHome;
    private CameraFragment mCamera;
    private MineFragment mMine;
    private RequestFragment mRequest;
    private PostFragment mPost;
    private int[] image0 = new int[]{
            R.drawable.fraghome,
            R.drawable.fragcamera,
            R.drawable.fragpost,
            R.drawable.fragrequest,
            R.drawable.fragmine};
    private int[] image1 = new int[]{
            R.drawable.fraghome1,
            R.drawable.fragcamera1,
            R.drawable.fragpost1,
            R.drawable.fragrequest1,
            R.drawable.fragmine1};
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //1. 获取界面的控件
        getViews();

        //2. 注册事件监听器
        setListener();

        switch (Utils.flag) {
            case 1://显示默认页面
                //3.设置默认的页面（homefragment页面）
                setDefaultPage();
                break;
            case 2://摄影作品浏览页面
                setCameraPage();
                break;
            case 3://发布页面
                setPostPage();
                break;
            case 4://需求浏览页面
                setRequestPage();
                break;
            case 5://我的页面
                setMinePage();
                break;
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();



    }

    //获取界面的控件
    private void getViews() {
        ll = (LinearLayout) findViewById(R.id.ll);
        mBtnHome = (ImageButton) findViewById(R.id.btn_home);
        mBtnCamera = (ImageButton) findViewById(R.id.btn_camera);
        mBtnMine = (ImageButton) findViewById(R.id.btn_mine);
        mBtnRequest = (ImageButton) findViewById(R.id.btn_request);
        mBtnPost = (ImageButton) findViewById(R.id.btn_post);

    }

    //注册事件监听器
    private void setListener() {
        MyListener listener = new MyListener();
        mBtnHome.setOnClickListener(listener);
        mBtnCamera.setOnClickListener(listener);
        mBtnMine.setOnClickListener(listener);
        mBtnRequest.setOnClickListener(listener);
        mBtnPost.setOnClickListener(listener);
    }

    //设置默认的页面（homefragment页面）
    private void setDefaultPage() {
        //1. 获取一个FragmentManager对象
        FragmentManager fm = getFragmentManager();
        //2. 获取FragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();
        if (mHome == null) {
            mHome = new HomeFragment();
        }
        //3. 设置页面
        transaction.replace(R.id.contaner, mHome);
        //4. 执行更改
        transaction.commit();
        ll.invalidate();
    }

    //设置默认的页面（camerafragment页面）
    private void setCameraPage() {
        //1. 获取一个FragmentManager对象
        FragmentManager fm = getFragmentManager();
        //2. 获取FragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();
        if (mCamera == null) {
            mCamera = new CameraFragment();
        }
        //3. 设置页面
        transaction.replace(R.id.contaner, mCamera);
        //4. 执行更改
        transaction.commit();
        ll.invalidate();
    }

    //设置默认的页面（postfragment页面）
    private void setPostPage() {
        //1. 获取一个FragmentManager对象
        FragmentManager fm = getFragmentManager();
        //2. 获取FragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();

        if (mPost == null) {
            mPost = new PostFragment();
        }
        //3. 设置页面
        transaction.replace(R.id.contaner, mPost);
        //4. 执行更改
        transaction.commit();
        ll.invalidate();
    }

    //设置默认的页面（requestfragment页面）
    private void setRequestPage() {
        //1. 获取一个FragmentManager对象
        FragmentManager fm = getFragmentManager();
        //2. 获取FragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();

        if (mRequest == null) {
            mRequest = new RequestFragment();
        }
        //3. 设置页面
        transaction.replace(R.id.contaner, mRequest);
        //4. 执行更改
        transaction.commit();
        ll.invalidate();
    }

    //设置默认的页面（minefragment页面）
    private void setMinePage() {
        //1. 获取一个FragmentManager对象
        FragmentManager fm = getFragmentManager();
        //2. 获取FragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();

        if (mHome == null) {
            mHome = new HomeFragment();
        }
        //3. 设置页面
        transaction.replace(R.id.contaner, mHome);
        //4. 执行更改
        transaction.commit();
        ll.invalidate();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.administrator.missphoto/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.administrator.missphoto/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //1. 获取一个FragmentManager对象
            FragmentManager fm = getFragmentManager();
            //2. 获取FragmentTransaction对象
            FragmentTransaction transaction = fm.beginTransaction();
            switch (v.getId()) {
                case R.id.btn_home:
                    if (mHome == null) {
                        mHome = new HomeFragment();
                    }
                    //3. 设置页面
                    mBtnHome.setImageResource(image1[0]);
                    mBtnCamera.setImageResource(image0[1]);
                    mBtnPost.setImageResource(image0[2]);
                    mBtnRequest.setImageResource(image0[3]);
                    mBtnMine.setImageResource(image0[4]);
                    transaction.replace(R.id.contaner, mHome);
                    break;
                case R.id.btn_camera:
                    if (mCamera == null) {
                        mCamera = new CameraFragment();
                    }
                    //3. 设置页面
                    mBtnCamera.setImageResource(image1[1]);
                    mBtnHome.setImageResource(image0[0]);
                    mBtnPost.setImageResource(image0[2]);
                    mBtnRequest.setImageResource(image0[3]);
                    mBtnMine.setImageResource(image0[4]);

                    transaction.replace(R.id.contaner, mCamera);
                    break;
                case R.id.btn_post:
                    Intent intent=new Intent(MainActivity.this,TakephotoActivity.class);
                    startActivity(intent);
//                    if (mPost == null) {
//                        mPost = new PostFragment();
//                    }
//                    //3. 设置页面
//                    mBtnPost.setImageResource(image1[2]);
//                    mBtnHome.setImageResource(image0[0]);
//                    mBtnCamera.setImageResource(image0[1]);
//                    mBtnRequest.setImageResource(image0[3]);
//                    mBtnMine.setImageResource(image0[4]);
//                    transaction.replace(R.id.contaner, mPost);
                    break;
                case R.id.btn_request:
                    if (mRequest == null) {
                        mRequest = new RequestFragment();
                    }
                    //3. 设置页面
                    mBtnRequest.setImageResource(image1[3]);
                    mBtnHome.setImageResource(image0[0]);
                    mBtnCamera.setImageResource(image0[1]);
                    mBtnPost.setImageResource(image0[2]);
                    mBtnMine.setImageResource(image0[4]);
                    transaction.replace(R.id.contaner, mRequest);
                    break;
                case R.id.btn_mine:
                    if (mMine == null) {
                        mMine = new MineFragment();
                    }
                    //3. 设置页面
                    mBtnMine.setImageResource(image1[4]);
                    mBtnHome.setImageResource(image0[0]);
                    mBtnCamera.setImageResource(image0[1]);
                    mBtnPost.setImageResource(image0[2]);
                    mBtnRequest.setImageResource(image0[3]);
                    transaction.replace(R.id.contaner, mMine);
                    break;
            }
            //4. 执行更改
            transaction.commit();
            ll.invalidate();
        }
    }
}
