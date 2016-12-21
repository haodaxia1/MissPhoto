package com.example.administrator.missphoto;

/**
 * Created by Administrator on 2016/12/10.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

public class TutorialIntroPageActivity extends Activity implements OnTouchListener {
    private static final float LIMIT_ANGLE_TAN = 1.5f;

    private ImageView mTutorialImage;

    private ImageView mIndicator1;
    private ImageView mIndicator2;
    private ImageView mIndicator3;
    private ImageView mIndicator4;

    private Button mSkipButton;
    private Button mDoneButton;

    private GestureDetector mDetector = null;
    private int mStep = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tutorial_info_page);

        mIndicator1 = (ImageView)findViewById(R.id.tutorial_indicator1);
        mIndicator2 = (ImageView)findViewById(R.id.tutorial_indicator2);
        mIndicator3 = (ImageView)findViewById(R.id.tutorial_indicator3);
        mIndicator4 = (ImageView)findViewById(R.id.tutorial_indicator4);

        mTutorialImage = (ImageView)findViewById(R.id.image_tutorial);
        mDetector = new GestureDetector(this, new TutorialImageGesture());
        mTutorialImage.setOnTouchListener(this);

        mSkipButton = (Button) findViewById(R.id.skip_button);
        mSkipButton.setOnClickListener(mOnSkipOrDoneButtonClickListener);
        mDoneButton = (Button) findViewById(R.id.done_button);
        mDoneButton.setOnClickListener(mOnSkipOrDoneButtonClickListener);

        if(savedInstanceState != null)
        {
            mStep = savedInstanceState.getInt("pageStep");
        }

        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        switch(mStep){
            case 0:
                break;
            case 1:
                mIndicator1.setBackgroundResource(R.drawable.indicator_dot);
                mIndicator2.setBackgroundResource(R.drawable.indicator_page);
                if(isLandscape)
                    mTutorialImage.setBackgroundResource(R.drawable.image2);
                else
                    mTutorialImage.setBackgroundResource(R.drawable.image2);
                break;
            case 2:
                mIndicator1.setBackgroundResource(R.drawable.indicator_dot);
                mIndicator3.setBackgroundResource(R.drawable.indicator_page);
                if(isLandscape)
                    mTutorialImage.setBackgroundResource(R.drawable.image3);
                else
                    mTutorialImage.setBackgroundResource(R.drawable.image3);
                break;
            case 3:
                mIndicator1.setBackgroundResource(R.drawable.indicator_dot);
                mIndicator4.setBackgroundResource(R.drawable.indicator_page);
                if(isLandscape)
                    mTutorialImage.setBackgroundResource(R.drawable.image4);
                else
                    mTutorialImage.setBackgroundResource(R.drawable.image4);
                mDoneButton.setVisibility(View.VISIBLE);
                mSkipButton.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pageStep", mStep);
    }

    private void showPrePage(){
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if(mStep == 3){
            mIndicator3.setBackgroundResource(R.drawable.indicator_page);
            mIndicator4.setBackgroundResource(R.drawable.indicator_dot);
            if(isLandscape)
                mTutorialImage.setBackgroundResource(R.drawable.image3);
            else
                mTutorialImage.setBackgroundResource(R.drawable.image3);
            mDoneButton.setVisibility(View.GONE);
            mSkipButton.setVisibility(View.VISIBLE);
            mStep--;
        }else if(mStep == 2){
            mIndicator2.setBackgroundResource(R.drawable.indicator_page);
            mIndicator3.setBackgroundResource(R.drawable.indicator_dot);
            if(isLandscape)
                mTutorialImage.setBackgroundResource(R.drawable.image2);
            else
                mTutorialImage.setBackgroundResource(R.drawable.image2);
            mStep--;
        }else if(mStep == 1){
            mIndicator1.setBackgroundResource(R.drawable.indicator_page);
            mIndicator2.setBackgroundResource(R.drawable.indicator_dot);
            if(isLandscape)
                mTutorialImage.setBackgroundResource(R.drawable.image1);
            else
                mTutorialImage.setBackgroundResource(R.drawable.image1);
            mStep--;
        }
    }

    private void showNextPage(){
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if(mStep == 0){
            mIndicator1.setBackgroundResource(R.drawable.indicator_dot);
            mIndicator2.setBackgroundResource(R.drawable.indicator_page);
            if(isLandscape)
                mTutorialImage.setBackgroundResource(R.drawable.image2);
            else
                mTutorialImage.setBackgroundResource(R.drawable.image2);
            mStep++;
        }else if(mStep == 1){
            mIndicator2.setBackgroundResource(R.drawable.indicator_dot);
            mIndicator3.setBackgroundResource(R.drawable.indicator_page);
            if(isLandscape)
                mTutorialImage.setBackgroundResource(R.drawable.image3);
            else
                mTutorialImage.setBackgroundResource(R.drawable.image3);
            mStep++;
        }else if(mStep == 2){
            mIndicator3.setBackgroundResource(R.drawable.indicator_dot);
            mIndicator4.setBackgroundResource(R.drawable.indicator_page);
            if(isLandscape)
                mTutorialImage.setBackgroundResource(R.drawable.image4);
            else
                mTutorialImage.setBackgroundResource(R.drawable.image4);
            mDoneButton.setVisibility(View.VISIBLE);
            mSkipButton.setVisibility(View.GONE);
            mStep++;
        }
    }

    private OnClickListener mOnSkipOrDoneButtonClickListener = new OnClickListener() {

        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(TutorialIntroPageActivity.this, FirstActivity.class);
            if(getIntent().getParcelableExtra(Intent.EXTRA_INTENT) != null){
                intent.putExtra(Intent.EXTRA_INTENT, getIntent().getParcelableExtra(Intent.EXTRA_INTENT));
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            //finish();
        }

    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return true;
    }

    public class TutorialImageGesture implements OnGestureListener {

        @Override
        public boolean onDown(MotionEvent arg0) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float ver = Math.abs(e1.getY() - e2.getY());
            float hor = Math.abs(e1.getX() - e2.getX());
            if ( ver / hor > LIMIT_ANGLE_TAN || Math.abs(velocityX)<500) {
                return false;
            }

            if (e2.getX() - e1.getX() < 0) {
                showNextPage();
            }
            else {
                showPrePage();
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
                                float arg3)
        {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void onShowPress(MotionEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean onSingleTapUp(MotionEvent arg0) {
            // TODO Auto-generated method stub
            return false;
        }

    }

    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {
        // TODO Auto-generated method stub
        return false;
    }
}