package com.uestc.znll.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.uestc.znll.R;
import com.uestc.znll.util.AlarmUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {

    //xml文件中定义的控件
    private ListView listView;
    private ImageButton imageButtonToFolderView;
    private ImageButton imageButtonEdit;

    @Override
    protected void initLayout() {
        findWidgets();
    }

    @Override
    protected void initValue() {
        //启动定时获取流量的任务
        AlarmUtil.startAlarmTask(context);
        setMainListView();
    }

    @Override
    protected void initListener() {
        imageButtonToFolderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FolderViewActivity.class);
                startActivity(intent);
            }
        });
        imageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddDataActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivityListViewItemClickMenuDialog dialog = new MainActivityListViewItemClickMenuDialog(MainActivity.this);
                dialog.show();
                return true;
            }
        });
    }

    @Override
    protected int setRootView() {
        return R.layout.activity_main;
    }

    private void findWidgets(){
        listView = (ListView)findViewById(R.id.Main_List);
        imageButtonToFolderView = (ImageButton)findViewById(R.id.main_imagebutton_tofolderview);
        imageButtonEdit = (ImageButton)findViewById(R.id.Main_button_edit);
    }
    private void setMainListView()
    {
        MainListViewAdapter listAdapter = new MainListViewAdapter(this,getData());
        listView.setAdapter(listAdapter);
    }
    private List<Map<String,Object>> getData()
    {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("progressvalue", 50);
        map.put("datatype","闲时流量");
        map.put("datausage","每日23：00到次日6：00");//"每日23：00到次日6：00"
        map.put("remainpecent","50%");
        map.put("remaindetail", "剩余1GB");
        list.add(map);
        return list;
    }

}
