package com.example.administrator.missphoto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class TakephotoActivity extends Activity {

    private GridView gridView1;              //网格显示缩略图
    private Button buttonPublish;            //发布按钮
    private final int IMAGE_OPEN = 1;        //打开图片标记
    private String pathImage;                //选择图片路径
    private Bitmap bmp;                      //导入临时图片
    private ArrayList<HashMap<String, Object>> imageItem;
    private SimpleAdapter simpleAdapter;     //适配器
    private ImageButton imagebutton1;
    private ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takephoto);
        imagebutton1=(ImageButton)findViewById(R.id.btn_post_request) ;
        imagebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(TakephotoActivity.this,GettimeActivity.class);
                startActivity(intent);
            }
        });
        btn_back=(ImageButton)findViewById(R.id.btn_back1);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setClass(TakephotoActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        /*
         * 防止键盘挡住输入框
         * 不希望遮挡设置activity属性 android:windowSoftInputMode="adjustPan"
         * 希望动态调整高度 android:windowSoftInputMode="adjustResize"
         */
       getWindow().setSoftInputMode(WindowManager.LayoutParams.
                SOFT_INPUT_ADJUST_PAN);

        //锁定屏幕
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //setContentView(R.layout.activity_main);
        //获取控件对象
        gridView1 = (GridView) findViewById(R.id.gridView1);

        /*
         * 载入默认图片添加图片加号
         * 通过适配器实现
         * SimpleAdapter参数imageItem为数据源 R.layout.griditem_addpic为布局
         */
        //获取资源图片加号
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.gridview_addpic);
        imageItem = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("itemImage", bmp);
        imageItem.add(map);
        simpleAdapter = new SimpleAdapter(this,
                imageItem, R.layout.griditem_addpic,
                new String[] { "itemImage"}, new int[] { R.id.imageView1});
        /*
         * HashMap载入bmp图片在GridView中不显示,但是如果载入资源ID能显示 如
         * map.put("itemImage", R.drawable.img);
         * 解决方法:
         *              1.自定义继承BaseAdapter实现
         *              2.ViewBinder()接口实现
         *  参考 http://blog.csdn.net/admin_/article/details/7257901
         */
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                // TODO Auto-generated method stub
                if(view instanceof ImageView && data instanceof Bitmap){
                    ImageView i = (ImageView)view;
                    i.setImageBitmap((Bitmap) data);
                    return true;
                }
                return false;
            }
        });
        gridView1.setAdapter(simpleAdapter);

        /*
         * 监听GridView点击事件
         * 报错:该函数必须抽象方法 故需要手动导入import android.view.View;
         */
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                if( imageItem.size() == 10) { //第一张为默认图片
                    Toast.makeText(TakephotoActivity.this, "图片数9张已满", Toast.LENGTH_SHORT).show();
                }
                else if(position == 0) { //点击图片位置为+ 0对应0张图片
                    Toast.makeText(TakephotoActivity.this, "添加图片", Toast.LENGTH_SHORT).show();
                    //选择图片
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, IMAGE_OPEN);
                    //通过onResume()刷新数据
                }
                else {
                    dialog(position);
                    //Toast.makeText(MainActivity.this, "点击第"+(position + 1)+" 号图片",
                    //      Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //获取图片路径 响应startActivityForResult
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //打开图片
        if(resultCode==RESULT_OK && requestCode==IMAGE_OPEN) {
            Uri uri = data.getData();
            if (!TextUtils.isEmpty(uri.getAuthority())) {
                //查询选择图片
                Cursor cursor = getContentResolver().query(
                        uri,
                        new String[] { MediaStore.Images.Media.DATA },
                        null,
                        null,
                        null);
                //返回 没找到选择图片
                if (null == cursor) {
                    return;
                }
                //光标移动至开头 获取图片路径
                cursor.moveToFirst();
                pathImage = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media.DATA));
            }
        }  //end if 打开图片
    }

    //刷新图片
    @Override
    protected void onResume() {
        super.onResume();
        if(!TextUtils.isEmpty(pathImage)){
            Bitmap addbmp=BitmapFactory.decodeFile(pathImage);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", addbmp);
            imageItem.add(map);
            simpleAdapter = new SimpleAdapter(this,
                    imageItem, R.layout.griditem_addpic,
                    new String[] { "itemImage"}, new int[] { R.id.imageView1});
            simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Object data,
                                            String textRepresentation) {
                    // TODO Auto-generated method stub
                    if(view instanceof ImageView && data instanceof Bitmap){
                        ImageView i = (ImageView)view;
                        i.setImageBitmap((Bitmap) data);
                        return true;
                    }
                    return false;
                }
            });
            gridView1.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();
            //刷新后释放防止手机休眠后自动添加
            pathImage = null;
        }
    }

    /*
     * Dialog对话框提示用户删除操作
     * position为删除图片位置
     */
    protected void dialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(TakephotoActivity.this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                imageItem.remove(position);
                simpleAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    //监听需求按钮，跳转到需求提交页面
  /*  imagebutton1=(ImageButton)findViewById(R.id.imagebutton1);
    imagebutton1.setOnClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onClick(View v) {
            //在按钮响应函数中添加如下两句话就ok了
            Intent intent=new Intent(MainActivity.this,GettimeActivity.class);
            startActivity(intent);

        }
    });*/
}

////其他项目中的功能实例包括评论，点赞，以及图片上传到服务器，仅供参考
//图片上传到服务器的代码如下：
//
//        package com.funOfSchool.ui;
//
//        import android.app.Activity;
//        import android.content.Context;
//        import android.content.DialogInterface;
//        import android.content.Intent;
//        import android.database.Cursor;
//        import android.graphics.Bitmap;
//        import android.graphics.BitmapFactory;
//        import android.net.Uri;
//        import android.os.Bundle;
//        import android.provider.MediaStore;
//        import android.support.v7.app.AlertDialog;
//        import android.text.InputType;
//        import android.text.TextUtils;
//        import android.util.Log;
//        import android.view.Gravity;
//        import android.view.View;
//        import android.view.WindowManager;
//        import android.widget.AdapterView;
//        import android.widget.Button;
//        import android.widget.EditText;
//        import android.widget.GridView;
//        import android.widget.ImageView;
//        import android.widget.RatingBar;
//        import android.widget.RelativeLayout;
//        import android.widget.SimpleAdapter;
//        import android.widget.Toast;
//
//        import com.funOfSchool.R;
//        import com.loopj.android.http.AsyncHttpClient;
//        import com.loopj.android.http.AsyncHttpResponseHandler;
//        import com.loopj.android.http.JsonHttpResponseHandler;
//        import com.loopj.android.http.RequestParams;
//
//        import org.apache.http.Header;
//        import org.json.JSONArray;
//        import org.json.JSONException;
//        import org.json.JSONObject;
//
//        import java.io.File;
//        import java.util.ArrayList;
//        import java.util.HashMap;
//
//public class EvaluateActivity extends Activity{
//    private String guade_evaluate;//带领人评价
//    private String school_evaluate;//学校评价
//    private EditText et1;   //带领者评价输入框
//    private EditText et2;   //学校评价输入框
//    private Button b1;  //提交按钮
//    private GridView gridView;              //网格显示缩略图
//    private final int IMAGE_OPEN = 1;        //打开图片标记
//    public String pathImage;                //选择图片路径
//    ArrayList<String> image_route  = new ArrayList<String> ();
//
//    private Bitmap bmp;                      //导入临时图片
//    private ArrayList<HashMap<String, Object>> imageItem;
//    private SimpleAdapter simpleAdapter;     //适配器
//    private RatingBar guade_ratingbar;//带领人本次评分星星个数
//
//    private RatingBar school_ratingbar;//学校本次评分星星个数
//    private int guade_score;//带领人评分
//    private String guade_sc;
//    private int school_score;//学校评分
//    private String school_sc;
//    private RelativeLayout R1;
//    private int height;
//    private int width;
//    //测试 定义 token
//    private String token = "480476f12c4740749ad01269933c6a7dF166LN";
//    private String keyWord = "河北师范大学";
//    private String provinceID = "130000";
//    private String portraiturl;
//    private String schoolbadge = null;
//
//
//    //根据url加载图片
//    public ImageView Iv ;
//    public ImageView Iv2;
//
//
//    //测试上传评论
//    private EditText Ed1;
//    private String recond;
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_evaluate);
//        Iv = (ImageView)findViewById(R.id.Iv_evaluate_portrait);
//        Iv2 = (ImageView)findViewById(R.id.Iv_evaluate_college);
//        Ed1 = (EditText)findViewById(R.id.Et_evaluate_et1);
//
//        //测试获取头像url
//        getPortrait();
//        getschoolbadge();
//        //使用普通加载网络方式通过url加载图片
//        new NormalLoadPictrue().getPicture(portraiturl,Iv);
//        new NormalLoadPictrue().getPicture(schoolbadge,Iv2);
//        //多行输入
//        Edittext_input();
//        //GridView设定
//        GridViewsetting();
//        //点击事件
//        b1_Onclick();
//        //设置按钮大小
//        setTitlehigh();
//
//
//    }
//    //图片上传
//    private void photouploading(){
//        int a = image_route.size();
//        if(a >=3){
//            a=3;
//        }
//        for(int i=0;i< a;i++){
//            if (TextUtils.isEmpty(image_route.get(i).trim())) {
//                Toast.makeText(this, "上次文件路径不能为空", Toast.LENGTH_SHORT).show();
//            } else {
//                //异步的客户端对象
//                AsyncHttpClient client = new AsyncHttpClient();
//                //指定url路径http://192.168.1.103:8080/api/fs/upload?token=f51d03971db845db9e95444b37ea14693uKLdX
//                String url = "http://10.7.88.110:8080/api/fs/upload?token=8e09d5b2dcf345f38f340a9e4a6a1e54aazlQI";
//                //封装文件上传的参数
//                RequestParams params = new RequestParams();
//                //根据路径创建文件
//                File file = new File(image_route.get(i));
//                try {
//                    //放入文件
//                    params.put("profile_picture", file);
//                } catch (Exception e) {
//                    // TODO: handle exception
//                    System.out.println("文件不存在----------");
//                }
//                //执行post请求
//                client.post(url,params, new AsyncHttpResponseHandler() {
//
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers,
//                                          byte[] responseBody) {
//                        if (statusCode == 200) {
//                            Toast.makeText(getApplicationContext(), "上次成功", Toast.LENGTH_SHORT)
//                                    .show();
//                        }
//                        Log.i("tag",new String(responseBody));
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers,
//                                          byte[] responseBody, Throwable error) {
//                        error.printStackTrace();
//                    }
//                });
//
//            }
//        }
//    }
//
//    //测试：上传学校评分及学校评价:未成功
//
//    private void submitcolloge(){
//        AsyncHttpClient client = new AsyncHttpClient();
//        String url = "http://10.7.88.110:8080/api/comment/setCollegeComment";
//        //请求参数：关键词
//        RequestParams param = new RequestParams();
//        param.put("collegeId","10001");
//        param.put("score",school_sc);
//        param.put("comment", school_evaluate);
//        param.put("token",token);
//        client.get(url, param, new JsonHttpResponseHandler(){
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
//                try {
//                    recond = null;
//                    JSONObject profile2 = null;
//                    profile2 = new JSONObject(response.toString());
//                    recond = profile2.getString("message");
//                    Log.e( "学校评价", recond);
//                }catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    //测试：上传带领人评分及带领人评论
//    private void submitman(){
//        AsyncHttpClient client = new AsyncHttpClient();
//        String url = "http://10.7.88.110:8080/api/comment/setUserComment";
//        // 请求参数：关键词
//        RequestParams param = new RequestParams();
//        param.put("userId","dcc2d7bf7f2a4c089f142a35af2f1318");
//        param.put("comment",guade_evaluate);
//        param.put("score",guade_sc);
//        param.put("token",token);
//        client.get(url, param, new JsonHttpResponseHandler(){
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
//                try {
//                    JSONObject profile2 = null;
//                    profile2 = new JSONObject(response.toString());
//                    recond = profile2.getString("message");
//                    Log.e( "带领者评价", recond);
//                }catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//
//
//
//
//
//    //测试：获取带领人头像url
//    private void getPortrait(){
//        AsyncHttpClient client = new AsyncHttpClient();
//        String url = "http://10.7.88.109:8080/api/account/profile/getProfile";
//        RequestParams param = new RequestParams();
//        param.put("token",token);
//        client.get(url, param, new JsonHttpResponseHandler(){
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
//                try {
//                    JSONObject profile = null;
//                    profile = new JSONObject(response.toString());
//                    JSONObject profile1 = profile.getJSONObject("datum");
//                    portraiturl = profile1.getString("profileImage");
//                    Log.e( portraiturl, portraiturl);
//                }catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//
//    }
//
//    //测试：获取学校校徽url
//    private void getschoolbadge(){
//        AsyncHttpClient client = new AsyncHttpClient();
//        String url = "http://localhost:8080/api/college/searchCollege";
//        RequestParams param = new RequestParams();
//        param.put("keyWord",keyWord);
//        client.get(url, param, new JsonHttpResponseHandler(){
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
//                try {
//                    JSONObject profile = null;
//                    profile = new JSONObject(response.toString());
//                    JSONArray profile2 = profile.getJSONArray("datum");
//                    int iSize = profile2.length();
//                    for (int i = 0; i < iSize; i++) {
//                        String schoolid = profile2.getJSONObject(i).getString("provinceID");
//                        if (schoolid == provinceID){
//                            schoolbadge = profile2.getJSONObject(i).getString("schoolLogo");
//                        }
//                    }
//                    Log.e( schoolbadge, schoolbadge);
//                }catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//
//
//    //设置按钮大小
//    private void setTitlehigh(){
//        R1 = (RelativeLayout) findViewById(R.id.RL_evaluate_button);
//        //获取高度
//        WindowManager wm = (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//        height = wm.getDefaultDisplay().getHeight();
//        width = wm.getDefaultDisplay().getWidth();
//        //设置标题栏高度
//        android.view.ViewGroup.LayoutParams lp = R1.getLayoutParams();
//        lp.height = (height/16);
//        lp.width = (2*width/3);
//    }
//
//    //提交按钮点击事件处理
//    private void b1_Onclick(){
//        b1=(Button)findViewById(R.id.Bt_evaluate_submit);
//        //RatingBar星级评分
//        guade_ratingbar = (RatingBar)findViewById(R.id.rtb_guadeRatingBar);
//        school_ratingbar = (RatingBar)findViewById(R.id.rtb_schoolRatingBar);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //获取输入框内容存入字符串内
//                guade_evaluate = et1.getText().toString();
//                school_evaluate = et2.getText().toString();
//                //获取星级评分
//                RatingBardeal();
//                //测试：发送消息
//                submitman();
//                submitcolloge();
//                photouploading();
//                for(int i = 0;i<image_route.size();i++){
//                    Log.e("路径-----",image_route.get(i));
//                }
////                //跳转
////                Intent intent = new Intent(EvaluateActivity.this,GGL_Activity.class);
////                startActivity(intent);
////                finish();
//            }
//        });
//    }
//    //获取星级评价的评分
//    private void RatingBardeal(){
//        //获取进度
////        int guade_result = guade_ratingbar.getProgress();
////        int school_result = school_ratingbar.getProgress();
//        //获取星级
//        float guade_rating = guade_ratingbar.getRating()*20;
//        float school_rating = school_ratingbar.getRating()*20;
//        //获取每次至少需改变多少个星级
////        float guade_step = guade_ratingbar.getStepSize();
////        float school_step = school_ratingbar.getStepSize();
//        //获取学校及带领人的评分
//        guade_score = (int)guade_rating;
//        guade_sc = guade_score + "";
//        school_score = (int)school_rating;
//        school_sc = school_score + "";
//    }
//    //设置Edittext为多行输入
//    private void Edittext_input(){
//        et1 = (EditText)findViewById(R.id.Et_evaluate_et1);
//        et2 = (EditText)findViewById(R.id.Et_evaluate_et2);
//        //设置EditText的显示方式为多行文本输入
//        et1.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
//        et2.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
//        //文本显示的位置在EditText的最上方
//        et1.setGravity(Gravity.TOP);
//        et2.setGravity(Gravity.TOP);
//        //改变默认的单行模式
//        et1.setSingleLine(false);
//        et2.setSingleLine(false);
//        //水平滚动设置为False
//        et1.setHorizontallyScrolling(false);
//        et2.setHorizontallyScrolling(false);
//    }
//    //获取图片路径 响应startActivityForResult
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //打开图片
//        if(resultCode==RESULT_OK && requestCode==IMAGE_OPEN) {
//            Uri uri = data.getData();
//            if (!TextUtils.isEmpty(uri.getAuthority())) {
//                //查询选择图片
//                Cursor cursor = getContentResolver().query(uri, new String[] { MediaStore.Images.Media.DATA }, null, null, null);
//                //返回 没找到选择图片
//                if (null == cursor) {
//                    return;
//                }
//                //光标移动至开头 获取图片路径
//                cursor.moveToFirst();
//                pathImage = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//                image_route.add(pathImage);
//                Log.e( pathImage, pathImage);
//            }
//        }  //end if 打开图片
//
//    }
//    //GridView设定
//    protected void GridViewsetting(){
//        /*
//         * 防止键盘挡住输入框
//         * 不希望遮挡设置activity属性 android:windowSoftInputMode="adjustPan"
//         * 希望动态调整高度 android:windowSoftInputMode="adjustResize"
//         */
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//
//        //获取控件对象
//        gridView = (GridView) findViewById(R.id.gridView);
//
//        /*
//         * 载入默认图片添加图片加号
//         * 通过适配器实现
//         * SimpleAdapter参数imageItem为数据源 R.layout.griditem_addpic为布局
//         */
//        //获取资源图片加号
//        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.evaluate_photo);
//        imageItem = new ArrayList<HashMap<String, Object>>();
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        map.put("itemImage", bmp);
//        imageItem.add(map);
//
//        simpleAdapter = new SimpleAdapter(this, imageItem, R.layout.evaluate_griditem_addpic, new String[] { "itemImage"}, new int[] { R.id.imageView});
//        /*
//         * HashMap载入bmp图片在GridView中不显示,但是如果载入资源ID能显示 如
//         * map.put("itemImage", R.drawable.img);
//         */
//        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
//            @Override
//            public boolean setViewValue(View view, Object data,
//                                        String textRepresentation) {
//                if(view instanceof ImageView && data instanceof Bitmap){
//                    ImageView i = (ImageView)view;
//                    i.setImageBitmap((Bitmap) data);
//                    return true;
//                }
//                return false;
//            }
//        });
//        gridView.setAdapter(simpleAdapter);
//
//        /*
//         * 监听GridView点击事件
//         * 报错:该函数必须抽象方法 故需要手动导入import android.view.View;
//         */
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
//            {
//                if( imageItem.size() == 4 && position ==0 ) { //第一张为默认图片
//                    Toast.makeText(EvaluateActivity.this, "图片数3张已满:)", Toast.LENGTH_SHORT).show();
//                }
//                else if(position == 0) { //点击图片位置为+ 0对应0张图片
//                    //选择图片
//                    Intent intent = new Intent(Intent.ACTION_PICK,
//                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(intent, IMAGE_OPEN);
//                    //通过onResume()刷新数据
//                }
//                else {
//                    dialog(position);
//                }
//            }
//        });
//    }
//    //刷新图片
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(!TextUtils.isEmpty(pathImage)){
//            Bitmap addbmp= BitmapFactory.decodeFile(pathImage);
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("itemImage", addbmp);
//            imageItem.add(map);
//            simpleAdapter = new SimpleAdapter(this,
//                    imageItem, R.layout.evaluate_griditem_addpic,
//                    new String[] { "itemImage"}, new int[] { R.id.imageView});
//            simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
//                @Override
//                public boolean setViewValue(View view, Object data,
//                                            String textRepresentation) {
//                    // TODO Auto-generated method stub
//                    if(view instanceof ImageView && data instanceof Bitmap){
//                        ImageView i = (ImageView)view;
//                        i.setImageBitmap((Bitmap) data);
//                        return true;
//                    }
//                    return false;
//                }
//            });
//            gridView.setAdapter(simpleAdapter);
//            simpleAdapter.notifyDataSetChanged();
//            //刷新后释放防止手机休眠后自动添加
//            pathImage = null;
//        }
//    }
//
//    /*
//     * Dialog对话框提示用户删除操作
//     * position为删除图片位置
//     */
//    protected void dialog(final int position) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(EvaluateActivity.this);
//        builder.setMessage("确认移除已添加图片吗？");
//        builder.setTitle("提示");
//        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                imageItem.remove(position);
//                simpleAdapter.notifyDataSetChanged();
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.create().show();
//    }
//
//    //返回按钮操作
//    protected void back_dialog(final int position){
//        AlertDialog.Builder builder = new AlertDialog.Builder(EvaluateActivity.this);
//        builder.setMessage("评价还未完成，您确定要退出吗？");
//        builder.setTitle("提示");
//        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                imageItem.remove(position);
//                simpleAdapter.notifyDataSetChanged();
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.create().show();
//    }
//
//}
