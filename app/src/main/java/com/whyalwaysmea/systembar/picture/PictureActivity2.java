package com.whyalwaysmea.systembar.picture;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.whyalwaysmea.systembar.R;
import com.whyalwaysmea.systembar.utils.MeasureUtil;

import java.util.ArrayList;
import java.util.List;

public class PictureActivity2 extends AppCompatActivity {
    private ListView listView;
    private List<String> data;
    private RecyclerView recycleView;
    private HomeAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            // 设置全屏，并且不会Activity的布局让出状态栏的空间
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture2);
        StatusBarUtil.setTransparent(this);
        materialCollapsingForKitkat();


        ImageView img = (ImageView) findViewById(R.id.iv_book_bg);
        img.setBackgroundResource(R.drawable.bg);


        data = getdata();
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(adapter = new HomeAdapter());
        mLinearLayoutManager = (LinearLayoutManager) recycleView.getLayoutManager();


        mLinearLayoutManager.setSmoothScrollbarEnabled(true);
        mLinearLayoutManager.setAutoMeasureEnabled(true);
        recycleView.setLayoutManager(mLinearLayoutManager);
        recycleView.setHasFixedSize(true);
        recycleView.setNestedScrollingEnabled(false);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PictureActivity2.this, "toolbar", Toast.LENGTH_SHORT).show();
            }
        });
//        toolbar.set


    }

    private void materialCollapsingForKitkat() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {

            // 设置Toolbar对顶部的距离
            final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) toolbar
                    .getLayoutParams();
            layoutParams.topMargin = MeasureUtil.getStatusBarHeight(this);
        }
    }

    public List<String> getdata() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 200; i++) {
            list.add("***********" + i + "***********");
        }
        return list;
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    PictureActivity2.this).inflate(R.layout.recycleview_item, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }


        }
    }

}
