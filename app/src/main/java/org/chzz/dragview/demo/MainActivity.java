package org.chzz.dragview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.chzz.dragview.model.DragChildInfo;
import org.chzz.dragview.model.DragIconInfo;
import org.chzz.dragview.view.CHZZDragView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CHZZDragView.DragOnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CHZZDragView CHZZDragView = (CHZZDragView) findViewById(R.id.cg_View);
        CHZZDragView.setAllInfoList(initAllOriginalInfo());
        CHZZDragView.initIconInfo();
        CHZZDragView.setDragOnClickListener(this);
    }

    private ArrayList<DragIconInfo> initAllOriginalInfo() {
        ArrayList<DragIconInfo> iconInfoList = new ArrayList<DragIconInfo>();
        ArrayList<DragChildInfo> childList = initChildList();
        iconInfoList.add(new DragIconInfo(1, "日常评", R.mipmap.ic_evaluation, DragIconInfo.CATEGORY_ONLY, new ArrayList<DragChildInfo>()));
        iconInfoList.add(new DragIconInfo(2, "第二", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_ONLY, new ArrayList<DragChildInfo>()));
        iconInfoList.add(new DragIconInfo(3, "第三", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_ONLY, new ArrayList<DragChildInfo>()));
        iconInfoList.add(new DragIconInfo(4, "第四", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_ONLY, new ArrayList<DragChildInfo>()));
        iconInfoList.add(new DragIconInfo(4, "第四", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_ONLY, new ArrayList<DragChildInfo>())); iconInfoList.add(new DragIconInfo(4, "第四", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_ONLY, new ArrayList<DragChildInfo>()));

        iconInfoList.add(new DragIconInfo(4, "第四", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_ONLY, new ArrayList<DragChildInfo>()));


        iconInfoList.add(new DragIconInfo(5, "第五", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_ONLY, new ArrayList<DragChildInfo>()));
        iconInfoList.add(new DragIconInfo(5, "第五", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_ONLY, new ArrayList<DragChildInfo>()));
        iconInfoList.add(new DragIconInfo(5, "第五", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_ONLY, new ArrayList<DragChildInfo>()));
        iconInfoList.add(new DragIconInfo(5, "第五", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_ONLY, new ArrayList<DragChildInfo>()));
        iconInfoList.add(new DragIconInfo(6, "展开", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_EXPAND, childList));
        iconInfoList.add(new DragIconInfo(6, "展开", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_EXPAND, childList));
        iconInfoList.add(new DragIconInfo(6, "展开", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_EXPAND, childList));
        iconInfoList.add(new DragIconInfo(6, "展开", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_EXPAND, childList));
        iconInfoList.add(new DragIconInfo(6, "展开", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_EXPAND, childList));
        iconInfoList.add(new DragIconInfo(6, "展开", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_EXPAND, childList));
        iconInfoList.add(new DragIconInfo(6, "展开", R.mipmap.ic_launcher, DragIconInfo.CATEGORY_EXPAND, childList));

        return iconInfoList;
    }

    private ArrayList<DragChildInfo> initChildList() {
        ArrayList<DragChildInfo> childList = new ArrayList<DragChildInfo>();
        childList.add(new DragChildInfo(1, "Item1"));
        childList.add(new DragChildInfo(2, "Item2"));
        childList.add(new DragChildInfo(3, "Item3"));
        childList.add(new DragChildInfo(4, "Item4"));
        childList.add(new DragChildInfo(5, "Item5"));
        childList.add(new DragChildInfo(6, "Item6"));
        childList.add(new DragChildInfo(7, "Item7"));
        return childList;
    }

    @Override
    public void dispatchChild(DragChildInfo childInfo) {
        Toast.makeText(this, "点击了DargChildInfo" + childInfo.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dispatchSingle(DragIconInfo dragInfo) {
        Toast.makeText(this, "点击了DragIconInfo" + dragInfo.getName(), Toast.LENGTH_SHORT).show();
    }
}
