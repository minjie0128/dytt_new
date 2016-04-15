package com.aladingEE.view;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aladingEE.R;
import com.minj.common.BaseActivity;
import com.minj.util.VUtils;


public class LoginActivity extends BaseActivity {

    private Context mContext;

    private Button btn_Login;
    private EditText editUserNo;
    private EditText editUserPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.aty_login);
        setBarTitle("阿拉订");
        mContext=this;
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"a",Toast.LENGTH_SHORT).show();
            }
        });

        editUserNo.addTextChangedListener(mTextWatcher);
        editUserPwd.addTextChangedListener(mTextWatcher);

    }

    TextWatcher mTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            Log.i("bb","editUserPwd="+editUserPwd.getText()+"===="+editUserPwd.getText().equals(""));
            Log.i("bb","no="+editUserNo.getText()+"==="+editUserNo.getText().equals(""));

            if(!editUserPwd.getText().toString().equals("") && !editUserNo.getText().toString().equals("")){
                VUtils.enableView(btn_Login);
            }else{
                VUtils.disableView(btn_Login);
            }
        }
    };


    @Override
    protected void initView() {
        super.initView();
        btn_Login=(Button) findViewById(R.id.btn_login);
        VUtils.disableView(btn_Login);

        editUserNo=(EditText)findViewById(R.id.ed_userNo);
        editUserPwd=(EditText)findViewById(R.id.ed_userPwd);
    }
}
