package basea.asheng.cn.baseactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import basea.asheng.cn.baseactivity.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private Context mContext;
    private Button btnNext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setModuleTitle("首页");
        mContext = this;
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
    }

    @Override
    protected void onClickReal(View v) {
        if (v.getId() == R.id.btnNext) {
            Intent i=new Intent(mContext,SecondActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onClickedResetButton(View view) {
        super.onClickedResetButton(view);
    }
}
