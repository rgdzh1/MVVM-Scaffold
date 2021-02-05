package com.student.drop.hefanjiami.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyStringCallBack<T> implements Callback {
    String TAG = "MyStringCallBack";
    Context mContext;
    ResPonse resPonse;

    public MyStringCallBack(Context context, ResPonse resPonse2) {
        this.resPonse = resPonse2;
        this.mContext = context;
    }

    public ResPonse getResPonse() {
        return this.resPonse;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, final IOException iOException) {
        ((Activity) this.mContext).runOnUiThread(new Runnable() {
            /* class com.hefan.advertutils.utils.MyStringCallBack.AnonymousClass1 */

            public void run() {
                String str = MyStringCallBack.this.TAG;
                Log.e(str, iOException.getLocalizedMessage() + " \\n" + iOException.getMessage() + " \\n" + iOException.toString());
                if (MyStringCallBack.this.resPonse != null) {
                    MyStringCallBack.this.resPonse.doError("101");
                }
            }
        });
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) throws IOException {
        final int code = response.code();
        String str = this.TAG;
        Log.i(str, "response:" + code);
        final String str2 = new String(response.body().bytes());
        ((Activity) this.mContext).runOnUiThread(new Runnable() {
            /* class com.hefan.advertutils.utils.MyStringCallBack.AnonymousClass2 */

            /* JADX DEBUG: Multi-variable search result rejected for r1v7, resolved type: com.hefan.advertutils.utils.ResPonse */
            /* JADX DEBUG: Multi-variable search result rejected for r2v6, resolved type: com.hefan.advertutils.utils.ResPonse */
            /* JADX DEBUG: Multi-variable search result rejected for r2v11, resolved type: com.hefan.advertutils.utils.ResPonse */
            /* JADX WARN: Multi-variable type inference failed */
            @SuppressLint("WrongConstant")
            public void run() {
                String str;
                if (code != 200) {
                    Context context = MyStringCallBack.this.mContext;
                    Toast.makeText(context, code + "", 0).show();
                    if (MyStringCallBack.this.resPonse != null) {
                        MyStringCallBack.this.resPonse.doError("103");
                        return;
                    }
                    return;
                }
                if (str2.indexOf("{") > 0) {
                    str = str2.substring(str2.indexOf("{"));
                } else {
                    str = str2;
                }
                Map<String, Object> parseJsonToMap = ParseUtils.parseJsonToMap(str);
                if (parseJsonToMap == null || parseJsonToMap.size() == 0) {
                    Toast.makeText(MyStringCallBack.this.mContext, "未获取任何数据", 0).show();
                    if (MyStringCallBack.this.resPonse != null) {
                        MyStringCallBack.this.resPonse.doError("103");
                        return;
                    }
                    return;
                }
                String str3 = MyStringCallBack.this.TAG;
                Log.i(str3, "data:" + parseJsonToMap.get("data"));
                if (Configuration.KEY_CODE_SUCCESS.equals(parseJsonToMap.get(Configuration.KEY_MES_CODE)) || parseJsonToMap.get(Configuration.KEY_MES_CODE).equals("200")) {
                    if (MyStringCallBack.this.resPonse != null) {
                        MyStringCallBack.this.resPonse.doSuccess(new Gson().toJson(parseJsonToMap.get("data")));
                    }
                } else if (parseJsonToMap.get(Configuration.KEY_MES_CODE).equals("617")) {
                    Toast.makeText(MyStringCallBack.this.mContext, (String) parseJsonToMap.get("msg"), 0).show();
                    if (MyStringCallBack.this.resPonse != null) {
                        MyStringCallBack.this.resPonse.doError(parseJsonToMap.get(Configuration.KEY_MES_CODE));
                    }
                } else {
                    Toast.makeText(MyStringCallBack.this.mContext, (String) parseJsonToMap.get("msg"), 0).show();
                    if (MyStringCallBack.this.resPonse != null) {
                        MyStringCallBack.this.resPonse.doError(parseJsonToMap.get(Configuration.KEY_MES_CODE));
                    }
                }
            }
        });
    }
}
