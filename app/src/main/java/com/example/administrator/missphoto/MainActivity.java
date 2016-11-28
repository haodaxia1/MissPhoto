package com.example.administrator.missphoto;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;


public class MainActivity extends Activity {

    private LinearLayout ll;
    private ImageButton mBtnHome, mBtnCamera, mBtnPost,mBtnRequest,mBtnMine;

    //声明Fragment属性
    private HomeFragment mHome;
    private CameraFragment mCamera;
    private MineFragment mMine;
    private RequestFragment mRequest;
    private PostFragment mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //1. 获取界面的控件
        getViews();

        //2. 注册事件监听器
        setListener();

        switch (Utils.flag){
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
    }

    //获取界面的控件
    private void getViews(){
        ll = (LinearLayout) findViewById(R.id.ll);
        mBtnHome = (ImageButton) findViewById(R.id.btn_home);
        mBtnCamera = (ImageButton) findViewById(R.id.btn_camera);
        mBtnMine = (ImageButton) findViewById(R.id.btn_mine);
        mBtnRequest = (ImageButton) findViewById(R.id.btn_request);
        mBtnPost = (ImageButton) findViewById(R.id.btn_post);

    }

    //注册事件监听器
    private void setListener(){
        MyListener listener = new MyListener();
        mBtnHome.setOnClickListener(listener);
        mBtnCamera.setOnClickListener(listener);
        mBtnMine.setOnClickListener(listener);
        mBtnRequest.setOnClickListener(listener);
        mBtnPost.setOnClickListener(listener);
    }

    //设置默认的页面（homefragment页面）
    private void setDefaultPage(){
        //1. 获取一个FragmentManager对象
        android.app.FragmentManager fm = getFragmentManager();
        //2. 获取FragmentTransaction对象
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(mHome == null) {
            mHome = new HomeFragment();
        }
        //3. 设置页面
        transaction.replace(R.id.contaner, mHome);
        //4. 执行更改
        transaction.commit();
        ll.invalidate();
    }

    //设置默认的页面（camerafragment页面）
    private void setCameraPage(){
        //1. 获取一个FragmentManager对象
        android.app.FragmentManager fm = getFragmentManager();
        //2. 获取FragmentTransaction对象
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(mCamera == null) {
            mCamera = new CameraFragment();
        }
        //3. 设置页面
        transaction.replace(R.id.contaner, mCamera);
        //4. 执行更改
        transaction.commit();
        ll.invalidate();
    }

    //设置默认的页面（postfragment页面）
    private void setPostPage(){
        //1. 获取一个FragmentManager对象
        android.app.FragmentManager fm = getFragmentManager();
        //2. 获取FragmentTransaction对象
        android.app.FragmentTransaction transaction = fm.beginTransaction();

        if(mPost == null){
            mPost = new PostFragment();
        }
        //3. 设置页面
        transaction.replace(R.id.contaner, mPost);
        //4. 执行更改
        transaction.commit();
        ll.invalidate();
    }

    //设置默认的页面（requestfragment页面）
    private void setRequestPage(){
        //1. 获取一个FragmentManager对象
        android.app.FragmentManager fm = getFragmentManager();
        //2. 获取FragmentTransaction对象
        android.app.FragmentTransaction transaction = fm.beginTransaction();

        if(mRequest == null){
            mRequest = new RequestFragment();
        }
        //3. 设置页面
        transaction.replace(R.id.contaner, mRequest);
        //4. 执行更改
        transaction.commit();
        ll.invalidate();
    }

    //设置默认的页面（minefragment页面）
    private void setMinePage(){
        //1. 获取一个FragmentManager对象
        android.app.FragmentManager fm = getFragmentManager();
        //2. 获取FragmentTransaction对象
        android.app.FragmentTransaction transaction = fm.beginTransaction();

        if(mHome == null){
            mHome = new HomeFragment();
        }
        //3. 设置页面
        transaction.replace(R.id.contaner, mHome);
        //4. 执行更改
        transaction.commit();
        ll.invalidate();
    }

    class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //1. 获取一个FragmentManager对象
            android.app.FragmentManager fm = getFragmentManager();
            //2. 获取FragmentTransaction对象
            android.app.FragmentTransaction transaction = fm.beginTransaction();
            switch (v.getId()){
                case R.id.btn_home:
                    if(mHome == null){
                        mHome = new HomeFragment();
                    }
                    //3. 设置页面
                    transaction.replace(R.id.contaner, mHome);
                    break;
                case R.id.btn_camera:
                    if(mCamera == null){
                        mCamera = new CameraFragment();
                    }
                    //3. 设置页面
                    transaction.replace(R.id.contaner, mCamera);
                    break;
                case R.id.btn_post:
                    if(mPost == null){
                        mPost = new PostFragment();
                    }
                    //3. 设置页面
                    transaction.replace(R.id.contaner, mPost);
                    break;
                case R.id.btn_request:
                    if(mRequest == null){
                        mRequest = new RequestFragment();
                    }
                    //3. 设置页面
                    transaction.replace(R.id.contaner, mRequest);
                    break;
                case R.id.btn_mine:
                    if(mMine == null){
                        mMine = new MineFragment();
                    }
                    //3. 设置页面
                    transaction.replace(R.id.contaner, mMine);
                    break;
            }
            //4. 执行更改
            transaction.commit();
            ll.invalidate();
        }
    }
}
