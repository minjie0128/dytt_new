package com.minj.common;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.aladingEE.R;
import com.minj.util.LogX;

/**
 * 作者：minj on 2016/4/15 14:07
 */
public class BaseActivity extends Activity{

    protected String TAG = "Alading-BaseActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogX.trace(TAG, getClassName() + " - onCreate");
        setContentView(R.layout.aty_mainlayout);
    }

    protected  void initView(){}
    protected  void bindEvent(){}

    protected void setMainContentView(int resId){
        View view=getLayoutInflater().inflate(resId,null);
        ((FrameLayout)findViewById(R.id.main_content)).addView(view);
        initView();
        bindEvent();
    }


    public String getClassName() {
        String clazzName = getClass().getName();
        return clazzName;
    }


    protected  void setBarTitle(String title){
        setTxtViewText(R.id.txt_title,title);
    }


    protected void setTxtViewText(int resId,String text){
        TextView txt=(TextView) findViewById(resId);
        txt.setText(text);
    }

//    private long mkeyTime;
//    @Override
//    public void onBackPressed() {
//        // TODO Auto-generated method stub
////    	super.onBackPressed();
//
////    	getSystemService(ACTIVITY_SERVICE)
//
//        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//        List<RunningTaskInfo> taskInfoList = am.getRunningTasks(1);
//
//        if(taskInfoList.get(0).topActivity.getClassName().equals("com.alading.ui.common.MainActivity")){
//            if((System.currentTimeMillis() - mkeyTime) > 1500){
//                mkeyTime = System.currentTimeMillis();
//                Toast.makeText(this, "再按一次退出阿拉订", Toast.LENGTH_SHORT).show();
//            }else{
//                finish();
//            }
//        }else{
//            finish();
//        }
//
//
//    }


    @Override
    protected void onPause() {
        LogX.trace(TAG, getClassName() + " - onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        LogX.trace(TAG, getClassName() + " - onResume");
        super.onResume();
    }
    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        LogX.trace(TAG, getClassName() + " - onRestart");
        super.onRestart();
    }
    @Override
    protected void onDestroy() {
        LogX.trace(TAG, getClassName() + " - onDestroy");
        super.onDestroy();
    }

    protected void hideInputMethod() {
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (im != null && getCurrentFocus() != null) {
            im.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /** 点击空白隐藏软键盘 */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();

            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {
                    0, 0
            };
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top  && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
