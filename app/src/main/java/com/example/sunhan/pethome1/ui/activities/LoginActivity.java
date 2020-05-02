package com.example.sunhan.pethome1.ui.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sunhan.pethome1.R;
import com.example.sunhan.pethome1.listener.HandleResponse;
import com.example.sunhan.pethome1.ui.widget.ConnectDialog;
import com.example.sunhan.pethome1.util.ConnectServer;
import com.example.sunhan.pethome1.util.Constant;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends BaseAvtivity{

    Button submitBtnPost = null;
    EditText nameEdit = null;
    EditText codeEdit = null;
    android.support.design.widget.FloatingActionButton registerButton;
    private HandleResponse handleResponse;
    String name;
    String password;
    ConnectDialog connectDialog;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0 :
                    connectDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    setResult(1,intent);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected View getContentView() {
        return inflateView(R.layout.activity_login);
    }

    @Override
    protected void setContentViewAfter(View contentView) {
        initView();
        initListener();
    }

    private void initView() {
        submitBtnPost = (Button)findView(R.id.bt_go);
        nameEdit = findViewById(R.id.et_username);
        codeEdit = findViewById(R.id.et_password);
        nameEdit.setText("test");
        codeEdit.setText("123");
        registerButton = findViewById(R.id.fab);
    }

    private void initListener() {
        submitBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEdit.getText().toString();
                password = codeEdit.getText().toString();
                String mode = "login";
                List<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
                paramsList.add(new BasicNameValuePair("NAME", name));
                paramsList.add(new BasicNameValuePair("CODE", password));
                paramsList.add(new BasicNameValuePair("MODE", mode));
                new ConnectServer(paramsList, Constant.checkUserUrl, handleResponse).execute();
            }
        });
        handleResponse = new HandleResponse() {
            @Override
            public void getResponse(String response) {
                String res = response.trim();
                if (res.equals("1")) {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新输入后重试！", Toast.LENGTH_SHORT).show();
                } else if (res.equals("-1")) {
                    Toast.makeText(LoginActivity.this, "网络连接不成功或访问数据库出错", Toast.LENGTH_SHORT).show();
                } else if (res.length() == 0) {
                    Toast.makeText(LoginActivity.this, "登录失败，请检查网络是否连接", Toast.LENGTH_SHORT).show();
                } else {
                    Constant.is_login = true;
                    Constant.username = name;
                    Constant.password = password;
                    connectDialog = new ConnectDialog(LoginActivity.this, R.style.dialog);
                    connectDialog.show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                                handler.sendEmptyMessage(0);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        };
    }
}
