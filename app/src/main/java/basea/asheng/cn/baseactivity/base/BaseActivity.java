package basea.asheng.cn.baseactivity.base;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import basea.asheng.cn.baseactivity.R;
import basea.asheng.cn.baseactivity.Utils.DialogUtil;
import basea.asheng.cn.baseactivity.Utils.StringUtil;
import basea.asheng.cn.baseactivity.view.ProgressBarLayout;

/**
 * 功能：activity基类
 *
 * @author by lee
 * @Version :
 * @date :
 */
public abstract class BaseActivity extends AppCompatActivity implements OnClickListener {
    private RelativeLayout relTitleBar;// 顶部导航栏
    private TextView moduleTextView;
    private Button topLeftButton;
    private TextView topRightText;
    private ImageView topRightImg;
    private FrameLayout mFraLayoutContent;
    private FrameLayout mFraLayoutHeadView;
    private RelativeLayout mRelLayoutBase;
    private ProgressBarLayout mLoadingBar;
    private RelativeLayout errorLayout;
    private RelativeLayout emptyLayout;
    private TextView tvEmtyHit;
    private TextView mResetButton;
    private Dialog mProgressDialog;//不可取消框
    private Dialog mProgressDialogCancle;//可取消加载框
    private int headViewResId = R.layout.layout_header_base;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);
        mRelLayoutBase = (RelativeLayout) findViewById(R.id.relLayoutBase);
        mFraLayoutContent = (FrameLayout) findViewById(R.id.fraLayoutContent);
        mFraLayoutHeadView = (FrameLayout) findViewById(R.id.fraLayoutHeadView);

        LayoutInflater.from(this).inflate(layoutResID, mFraLayoutContent, true);
        LayoutInflater.from(this).inflate(headViewResId, mFraLayoutHeadView, true);
        mLoadingBar = (ProgressBarLayout) findViewById(R.id.load_bar_layout);
        errorLayout = (RelativeLayout) findViewById(R.id.errorLayout);
        emptyLayout = (RelativeLayout) findViewById(R.id.emptyLayout);
        mResetButton = (TextView) findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(this);

        //如果有使用黄油刀，请在这边加入即可
//        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        //如果有使用黄油刀，请在这边加入即可
//        ButterKnife.bind(this);

    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        //如果有使用黄油刀，请在这边加入即可
//        ButterKnife.bind(this);
    }

    /**
     * 功能：如果自定义headview,一定要在setContentView前调用,否则无效
     *
     * @param layoutResID
     * @author:
     */
    protected void setHeadView(int layoutResID) {
        this.headViewResId = layoutResID;
    }

    //隐藏头部
    protected void hideHeadView() {
        mFraLayoutHeadView.setVisibility(View.GONE);
    }

    //显示头部
    protected void showHeadView() {
        mFraLayoutHeadView.setVisibility(View.VISIBLE);
    }

    //显示加载布局
    protected void showLoadingBar() {
        showLoadingBar(false);
    }

    //隐藏加载布局
    public void hideLoadingBar() {
        mLoadingBar.hide();
    }

    public void showLoadingBar(boolean transparent) {
        mLoadingBar.setBackgroundColor(transparent ? Color.TRANSPARENT : getResources().getColor(R.color.main_bg));
        mLoadingBar.show();
    }

    //加载布局是否显示
    public boolean isLoadingBarShow() {
        return mLoadingBar.getVisibility() == View.VISIBLE;
    }
    //显示无网络布局
    protected void showErrorLayout() {
        errorLayout.setVisibility(View.VISIBLE);
    }
    //隐藏无网络布局
    protected void hideErrorLayout() {
        errorLayout.setVisibility(View.GONE);
    }

    //显示空页面
    public void showEmptyLayout(String emptyHit) {
        emptyLayout.setVisibility(View.VISIBLE);
        if (tvEmtyHit == null) {
            tvEmtyHit = (TextView) findViewById(R.id.tvEmtyHit);
        }
        tvEmtyHit.setText(emptyHit);
    }

    //隐藏空页面
    public void hideEmptyLayout() {
        emptyLayout.setVisibility(View.GONE);
    }
    protected RelativeLayout getErrorLayout() {
        return errorLayout;
    }
    //设置头部颜色
    protected void setTitleBarBackgroundColor(int color) {
        if (relTitleBar == null) {
            relTitleBar = (RelativeLayout) findViewById(R.id.relTitleBar);
        }
        relTitleBar.setBackgroundColor(getResources().getColor(color));
    }
    //设置头部文字颜色
    protected void setModuleTitleColor(int resourceId) {
        if (moduleTextView == null) {
            moduleTextView = (TextView) findViewById(R.id.module_title_text_view);
        }
        moduleTextView.setTextColor(getResources().getColor(resourceId));
    }
    //设置头部局部
    protected void setModuleTitle(int resourceId) {
        if (moduleTextView == null) {
            moduleTextView = (TextView) findViewById(R.id.module_title_text_view);
        }
        moduleTextView.setText(resourceId);
    }
    //设置头部文字
    protected void setModuleTitle(String text) {
        if (moduleTextView == null) {
            moduleTextView = (TextView) findViewById(R.id.module_title_text_view);
        }
        moduleTextView.setVisibility(View.VISIBLE);
        moduleTextView.setText(text);
    }
    //设置头部图片
    protected void setModuleTitleImg(int resId) {
        if (moduleTextView == null) {
            moduleTextView = (TextView) findViewById(R.id.module_title_text_view);
        }
        moduleTextView.setVisibility(View.VISIBLE);
        moduleTextView.setText("");
        moduleTextView.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
    }
    //隐藏头部文字
    protected void hideModuleTitle() {
        if (moduleTextView == null) {
            moduleTextView = (TextView) findViewById(R.id.module_title_text_view);
        }
        moduleTextView.setVisibility(View.GONE);
    }
    //隐藏左上
    protected void hideTopLeftButton() {
        if (topLeftButton == null) {
            topLeftButton = (Button) findViewById(R.id.top_left_button);
        }
        topLeftButton.setVisibility(View.GONE);
    }
    //显示左上，默认为箭头
    protected void showTopLeftButton() {
        showTopLeftButton("", R.drawable.btn_back);
    }
    //显示左上，可添加文字 +箭头
    protected void showTopLeftButton(String text) {
        showTopLeftButton(text, R.drawable.btn_back);
    }
    //显示左上，无箭头
    protected void showTopLeftText(String text) {
        showTopLeftButton(text, 0);
    }

    protected void showTopLeftButton(String text, int resId) {

        if (topLeftButton == null) {
            topLeftButton = (Button) findViewById(R.id.top_left_button);
            topLeftButton.setOnClickListener(this);
        }
        topLeftButton.setVisibility(View.VISIBLE);
        topLeftButton.setText(StringUtil.isEmpty(text) ? "" : text);
        topLeftButton.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
    }

    protected RelativeLayout getlayoutBase() {
        return mRelLayoutBase;
    }

    protected View getHeadView() {
        return mFraLayoutHeadView;
    }

    protected Button getTopLeftButton() {

        if (topLeftButton == null) {
            topLeftButton = (Button) findViewById(R.id.top_left_button);
        }
        return topLeftButton;
    }

    //显示右上文字
    protected void showTopRightText(String string) {
        if (topRightText == null) {
            topRightText = (TextView) findViewById(R.id.top_right_text);
            topRightText.setOnClickListener(this);
        }
        topRightText.setVisibility(View.VISIBLE);
        topRightText.setText(string);
    }

    //隐藏右上文字
    protected void hideTopRightText() {
        if (topRightText == null) {
            topRightText = (TextView) findViewById(R.id.top_right_text);
        }
        topRightText.setVisibility(View.INVISIBLE);
    }

    //显示右上图片，只有图片 不含文字
    protected void showTopRightImg(int img) {
        if (topRightImg == null) {
            topRightImg = (ImageView) findViewById(R.id.top_right_img);
            topRightImg.setOnClickListener(this);
        }
        topRightImg.setVisibility(View.VISIBLE);
        topRightImg.setImageResource(img);
    }

    //隐藏右上图片，只有图片 不含文字
    protected void hideTopRightImg() {
        if (topRightImg == null) {
            topRightImg = (ImageView) findViewById(R.id.top_right_img);
        }
        topRightImg.setVisibility(View.INVISIBLE);
    }

    protected void showLoadingDialogCancle() {
        if (mProgressDialogCancle == null) {
            mProgressDialogCancle = DialogUtil.createProgressDiaolg(this, "加载中...", true);
        }
        if (!mProgressDialogCancle.isShowing()) {
            mProgressDialogCancle.show();
        }
    }

    protected void hideLoadingDialogCancle() {
        if (mProgressDialogCancle == null) {
            mProgressDialogCancle = DialogUtil.createProgressDiaolg(this, "加载中...", true);
        }
        if (mProgressDialogCancle.isShowing()) {
            mProgressDialogCancle.dismiss();
        }
    }

    protected void showLoadingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = DialogUtil.createProgressDiaolg(this, "加载中...", false);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    protected void hideLoadingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = DialogUtil.createProgressDiaolg(this, "加载中...", false);
        }
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public View getmFraLayoutContent() {
        return mFraLayoutContent;
    }

    @Override
    public final void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_left_button:
                onClickedTopLeftButtton(v);
                break;
            case R.id.top_right_text:
                onClickTopRightText(v);
                break;
            case R.id.top_right_img:
                onClickToprightImg(v);
                break;
            case R.id.reset_button:
                onClickedResetButton(v);
                break;
            //如果使用黄油刀，请注释掉这里
            default:
                onClickReal(v);
                break;
        }
    }

    //如果使用黄油刀，请注释掉这里
    protected abstract void onClickReal(View v);

    protected void onClickedTopLeftButtton(View view) {
        finish();
    }


    protected void onClickedResetButton(View view) {

    }

    protected void onClickTopRightText(View view) {

    }

    protected void onClickToprightImg(View view) {

    }
}
