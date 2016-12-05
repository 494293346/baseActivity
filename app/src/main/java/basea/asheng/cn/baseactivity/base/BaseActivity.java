package basea.asheng.cn.baseactivity.base;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
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
public abstract class BaseActivity extends FragmentActivity implements OnClickListener {
    private RelativeLayout relTitleBar;// 顶部导航栏
    private TextView moduleTextView;
    private Button topLeftButton;
    private Button topRightButton;
    private Button topRightButtonSec;
    private FrameLayout mFraLayoutContent;
    private FrameLayout mFraLayoutHeadView;
    private RelativeLayout mRelLayoutBase;
    private ProgressBarLayout mLoadingBar;
    private RelativeLayout errorLayout;
    private TextView mResetButton;
    private Dialog mProgressDialog;//不可取消框
    private Dialog mProgressDialogCancle;//可取消加载框
    private int headViewResId = R.layout.layout_header_base;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        mRelLayoutBase = (RelativeLayout) findViewById(R.id.relLayoutBase);
        mFraLayoutContent = (FrameLayout) findViewById(R.id.fraLayoutContent);
        mFraLayoutHeadView = (FrameLayout) findViewById(R.id.fraLayoutHeadView);
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, mFraLayoutContent, true);
        LayoutInflater.from(this).inflate(headViewResId, mFraLayoutHeadView, true);
        mLoadingBar = (ProgressBarLayout) findViewById(R.id.load_bar_layout);
        errorLayout = (RelativeLayout) findViewById(R.id.errorLayout);
        mResetButton = (TextView) findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(this);
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

    protected void hideHeadView() {
        mFraLayoutHeadView.setVisibility(View.GONE);
    }

    protected void showHeadView() {
        mFraLayoutHeadView.setVisibility(View.VISIBLE);
    }

    protected void showLoadingBar() {
        showLoadingBar(false);
    }

    public void showLoadingBar(boolean transparent) {
        mLoadingBar.setBackgroundColor(transparent ? Color.TRANSPARENT : getResources().getColor(R.color.main_bg));
        mLoadingBar.show();
    }

    public void hideLoadingBar() {
        mLoadingBar.hide();
    }

    public boolean isLoadingBarShow() {
        return mLoadingBar.getVisibility() == View.VISIBLE;
    }

    protected void showErrorLayout() {
        errorLayout.setVisibility(View.VISIBLE);
    }

    protected void hideErrorLayout() {
        errorLayout.setVisibility(View.GONE);
    }

    protected RelativeLayout getErrorLayout() {
        return errorLayout;
    }

    protected void setTitleBarBackgroundColor(int color) {
        if (relTitleBar == null) {
            relTitleBar = (RelativeLayout) findViewById(R.id.relTitleBar);
        }
        if (relTitleBar != null) {
            relTitleBar.setBackgroundColor(getResources().getColor(color));
        }
    }

    protected void setModuleTitleColor(int resourceId) {
        if (moduleTextView == null) {
            moduleTextView = (TextView) findViewById(R.id.module_title_text_view);
        }

        if (moduleTextView != null) {
            moduleTextView.setTextColor(getResources().getColor(resourceId));
        }
    }

    protected void setModuleTitle(int resourceId) {

        if (moduleTextView == null) {
            moduleTextView = (TextView) findViewById(R.id.module_title_text_view);
        }

        if (moduleTextView != null) {
            moduleTextView.setText(resourceId);
        }
    }

    protected void setModuleTitle(String text) {

        if (moduleTextView == null) {
            moduleTextView = (TextView) findViewById(R.id.module_title_text_view);
        }

        if (moduleTextView != null) {
            moduleTextView.setVisibility(View.VISIBLE);
            moduleTextView.setText(text);
        }
    }

    protected void setModuleTitleImg(int resId) {

        if (moduleTextView == null) {
            moduleTextView = (TextView) findViewById(R.id.module_title_text_view);
        }

        if (moduleTextView != null) {
            moduleTextView.setVisibility(View.VISIBLE);
            moduleTextView.setText("");
            moduleTextView.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
        }
    }

    protected void hiddenModuleTitle() {

        if (moduleTextView == null) {
            moduleTextView = (TextView) findViewById(R.id.module_title_text_view);
        }

        if (moduleTextView != null) {
            moduleTextView.setVisibility(View.GONE);
        }
    }

    protected void hiddenTopLeftButton() {

        if (topLeftButton == null) {
            topLeftButton = (Button) findViewById(R.id.top_left_button);
        }

        if (topLeftButton != null) {
            topLeftButton.setVisibility(View.GONE);
        }
    }

    protected void showTopLeftButton() {
        showTopLeftButton("", R.drawable.btn_back);
    }

    protected void showTopLeftButton(String text) {
        showTopLeftButton(text, R.drawable.btn_back);
    }

    protected void showTopLeftText(String text) {
        showTopLeftButton(text, 0);
    }

    protected void showTopLeftButton(String text, int resId) {

        if (topLeftButton == null) {
            topLeftButton = (Button) findViewById(R.id.top_left_button);
            topLeftButton.setOnClickListener(this);
        }
        if (topLeftButton != null) {
            topLeftButton.setVisibility(View.VISIBLE);
            topLeftButton.setText(StringUtil.isEmpty(text) ? "" : text);
            topLeftButton.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
        }
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

    protected void hiddenTopRightButton() {

        if (topRightButton == null) {
            topRightButton = (Button) findViewById(R.id.top_right_button);
        }

        if (topRightButton != null) {
            topRightButton.setVisibility(View.INVISIBLE);
        }
    }

    protected void showTopRightButtonText(String text) {
        showTopRightButtonTextAndImg(text, 0);
    }

    protected void showTopRightButtonImg(int img) {
        showTopRightButtonTextAndImg("", img);
    }

    protected Button getTopRightButton() {
        if (topRightButton == null) {
            topRightButton = (Button) findViewById(R.id.top_right_button);
            topRightButton.setOnClickListener(this);
        }
        return topRightButton;
    }

    protected void showTopRightButtonTextAndImg(String text, int img) {

        if (topRightButton == null) {
            topRightButton = (Button) findViewById(R.id.top_right_button);
            topRightButton.setOnClickListener(this);
        }
        if (topRightButton != null) {
            topRightButton.setVisibility(View.VISIBLE);
            topRightButton.setCompoundDrawablesWithIntrinsicBounds(img, 0, 0, 0);
            topRightButton.setText(StringUtil.isEmpty(text) ? "" : text);
        }
    }

    protected void hiddenTopRightButtonSec() {

        if (topRightButtonSec == null) {
            topRightButtonSec = (Button) findViewById(R.id.top_right_button_sec);
        }

        if (topRightButtonSec != null) {
            topRightButtonSec.setVisibility(View.INVISIBLE);
        }
    }

    protected void showTopRightButtonSecText(String text) {
        showTopRightButtonSec(text, 0);
    }

    protected void showTopRightButtonSecImg(int img) {
        showTopRightButtonSec("", img);
    }

    protected void showTopRightButtonSec(String text, int img) {

        if (topRightButtonSec == null) {
            topRightButtonSec = (Button) findViewById(R.id.top_right_button_sec);
            topRightButtonSec.setOnClickListener(this);
        }

        if (topRightButtonSec != null) {
            topRightButtonSec.setVisibility(View.VISIBLE);
            topRightButtonSec.setCompoundDrawablesWithIntrinsicBounds(0, 0, img, 0);
            topRightButtonSec.setText(StringUtil.isEmpty(text) ? "" : text);
        }
    }

    protected Button getTopRightButtonSec() {
        if (topRightButtonSec == null) {
            topRightButtonSec = (Button) findViewById(R.id.top_right_button_sec);
            topRightButtonSec.setOnClickListener(this);
        }
        return topRightButtonSec;
    }
    protected void showLoadingDialogCancle(){
        if (mProgressDialogCancle == null) {
            mProgressDialogCancle = DialogUtil.createProgressDiaolg(this, "加载中...",true);
        }
        if (!mProgressDialogCancle.isShowing()) {
            mProgressDialogCancle.show();
        }
    }
    protected void hideLoadingDialogCancle() {
        if (mProgressDialogCancle == null) {
            mProgressDialogCancle = DialogUtil.createProgressDiaolg(this, "加载中...",true);
        }
        if(mProgressDialogCancle.isShowing()){
            mProgressDialogCancle.dismiss();
        }
    }
    protected void showLoadingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = DialogUtil.createProgressDiaolg(this, "加载中...",false);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    protected void hideLoadingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = DialogUtil.createProgressDiaolg(this, "加载中...",false);
        }
        if(mProgressDialog.isShowing()){
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
            case R.id.top_right_button:
                onClickedTopRightButtton(v);
                break;
            case R.id.top_right_button_sec:
                onClickedtopRightButtonSec(v);
                break;
            case R.id.reset_button:
                onClickedResetButton(v);
                break;
            default:
                onClickReal(v);
                break;
        }
    }

    protected abstract void onClickReal(View v);

    protected void onClickedTopLeftButtton(View view) {
        finish();
    }

    protected void onClickedTopRightButtton(View view) {

    }

    protected void onClickedtopRightButtonSec(View view) {

    }

    protected void onClickedResetButton(View view) {

    }
}
