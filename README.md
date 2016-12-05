# baseActivity
Activity项目一个基类
##简介
 ```
 该类是activity的一个基类，里面封装了一些常用activity的布局：顶部栏，加载框（嵌套在布局内），加载框（以对话框的形式展示），错误重新加载布局等
 具体效果如下图：
```
##效果图展示
![图片描述](https://github.com/494293346/baseActivity/blob/master/images/image.gif)
##用法
 ```
1、activity继承该项目中的BaseActivity
public class MainActivity extends BaseActivity {
      @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onClickReal(View v) {
      
    }
}
 重写父类onCreate方法和onClickReal方法，onClickReal方法是点击Activity控件点击的监听，由于baseactivity中封装了一些事件点击的监听，如顶部栏的
 左上返回键、右上角按钮等，所以在这个方法中监听点击其他控件
2、重写你需要的，封装在Bseactivity内的点击控件监听
 它们分别是
 数据错误重新加载：
     @Override
    protected void onClickedResetButton(View view) {
        super.onClickedResetButton(view);
       
    }
    右上角：
    @Override
    protected void onClickedTopRightButtton(View view) {
        super.onClickedTopRightButtton(view);
        
    }
    左上角：
      @Override
    protected void onClickedTopLeftButtton(View view) {
        super.onClickedTopLeftButtton(view);
    }
    ········
    还有其他监听请看demo
3、各种放在baseactivity中的控件的操作：
顶部栏操作：   显示：showHeadView() 隐藏： hideHeadView();  默认隐藏
显示返回箭头： showTopLeftButton();
设置右上文字： showTopRightButtonText("右上方");
显示加载布局： showLoadingBar(); 隐藏： hideLoadingBar();
显示加载对话框： showLoadingDialog(); 隐藏：  hideLoadingDialog();
显示错误：  showErrorLayout(); 隐藏: hideErrorLayout();
 还有更多用法，请参考demo
 
4、如果你需要更换ui效果，请到对应的xml文件或者java文件自行修改
```
