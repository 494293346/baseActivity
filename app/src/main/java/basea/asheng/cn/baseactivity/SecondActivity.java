package basea.asheng.cn.baseactivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import basea.asheng.cn.baseactivity.base.BaseActivity;

public class SecondActivity extends BaseActivity {
    private Context mContext;
    private Button btnHideHead, btnShowHead, btnSetTitle, btnTopLeft, btnTopRight, btnShowLoad, btnShowLoadDialog, btnShowEror, btnShowLoadDialogCancle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setModuleTitle("第二页");
        mContext = this;
        initView();
    }

    private void initView() {
        btnHideHead = (Button) findViewById(R.id.btnHideHead);
        btnHideHead.setOnClickListener(this);
        btnShowHead = (Button) findViewById(R.id.btnShowHead);
        btnShowHead.setOnClickListener(this);
        btnSetTitle = (Button) findViewById(R.id.btnSetTitle);
        btnSetTitle.setOnClickListener(this);
        btnTopLeft = (Button) findViewById(R.id.btnTopLeft);
        btnTopLeft.setOnClickListener(this);
        btnTopRight = (Button) findViewById(R.id.btnTopRight);
        btnTopRight.setOnClickListener(this);
        btnShowLoad = (Button) findViewById(R.id.btnShowLoad);
        btnShowLoad.setOnClickListener(this);
        btnShowLoadDialog = (Button) findViewById(R.id.btnShowLoadDialog);
        btnShowLoadDialog.setOnClickListener(this);
        btnShowEror = (Button) findViewById(R.id.btnShowEror);
        btnShowEror.setOnClickListener(this);
        btnShowLoadDialogCancle = (Button) findViewById(R.id.btnShowLoadDialogCancle);
        btnShowLoadDialogCancle.setOnClickListener(this);
    }

    @Override
    protected void onClickReal(View v) {
        if (v.getId() == R.id.btnHideHead) {
            hideHeadView();
        } else if (v.getId() == R.id.btnShowHead) {
            showHeadView();
        } else if (v.getId() == R.id.btnSetTitle) {
            setModuleTitle("新标题");
        } else if (v.getId() == R.id.btnTopLeft) {
            //只显示默认返回箭头
            showTopLeftButton();
            //文字
//            showTopLeftButton("返回");
            //也可以自己设置返回箭头
//            showTopLeftButton("返回", R.mipmap.ic_launcher);
        } else if (v.getId() == R.id.btnTopRight) {
            //设置文字
            showTopRightButtonText("右上方");
            //文字+图片
//            showTopRightButtonSec("右上方", R.mipmap.ic_launcher);
            //图片
//            showTopRightButtonImg(R.mipmap.ic_launcher);
        } else if (v.getId() == R.id.btnShowLoadDialog) {
            showLoadingDialog();
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SecondActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideLoadingDialog();
                        }
                    });
                }
            }.start();
        } else if (v.getId() == R.id.btnShowLoadDialogCancle) {
            showLoadingDialogCancle();
        } else if (v.getId() == R.id.btnShowLoad) {
            showLoadingBar();
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SecondActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideLoadingBar();
                        }
                    });
                }
            }.start();
        } else if (v.getId() == R.id.btnShowEror) {
            showErrorLayout();
        }
    }

    @Override
    protected void onClickedResetButton(View view) {
        super.onClickedResetButton(view);
        Toast.makeText(mContext, "点击重新加载", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onClickedTopRightButtton(View view) {
        super.onClickedTopRightButtton(view);
        Toast.makeText(mContext, "右上角 无图片时", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onClickedtopRightButtonSec(View view) {
        super.onClickedtopRightButtonSec(view);
        Toast.makeText(mContext, "右上角 有图片时", Toast.LENGTH_SHORT).show();
    }
}
