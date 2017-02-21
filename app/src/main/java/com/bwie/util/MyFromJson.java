package com.bwie.util;


import android.content.Context;

import android.widget.Toast;


import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * 类描述:解析数据
 * 作者：陈文梦
 * 时间:2017/2/7 10:06
 * 邮箱:18310832074@163.com
 */

public
abstract class MyFromJson {

    public void getdate(String s){

        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, s, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {


                String result = responseInfo.result;
                myfromjson_String(result);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }

        public  abstract void myfromjson_String(String s);

}
