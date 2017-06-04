package com.onyx.itools.fragment.side;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.onyx.itools.R;
import com.onyx.itools.activity.FileActivity;
import com.onyx.itools.base.BaseFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/3/30 0030,0:48
 * @Version: V1.0
 * @Description: TODO
 */
public class FileFragment extends BaseFragment {
    private static final String COLUMN_NAME_NAME = "column_name_name";
    private TextView textView;
    private RecyclerView rcvFiles;
    private FileListAdapter fileListAdapter;
    private List<HashMap<String, Object>> mDatas;

//    private ListView m_lv = null;
//    private TouchCallback m_tcb = null;
    private ArrayList<File> mFileList = new ArrayList<File>();
    private String m_strDir = "./";
    private TextView tvFile = null;
//    private OnItemLongClickListenerEx mItemLclickCb = null;
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_file;
    }

    @Override
    protected void initView(View view) {
//        textView = (TextView) view.findViewById(R.id.menu_text);
//        String text = getArguments().getString("text");
//        textView.setText(text);

        rcvFiles = (RecyclerView) view.findViewById(R.id.rcv_files);
        tvFile = (TextView)view.findViewById(R.id.menu_text);
        rcvFiles.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvFiles.setAdapter( fileListAdapter = new FileListAdapter());
        rcvFiles.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void intData() {
//        mDatas = new ArrayList<String>();
//        for (int i = 'A'; i < 'z'; i++)
//        {
//            mDatas.add("" + (char) i);
//        }

        //生成动态数组，加入数据
        File fRoot = new File(m_strDir);
        mDatas = getAddFiles(fRoot);
    }

    @Override
    protected void intEvent() {

    }

    class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.item_recycleview_files, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {

            holder.ivIcon.setImageResource((Integer) mDatas.get(position).get("image"));
            holder.tvAttrs.setText((String) mDatas.get(position).get("attrs"));
            holder.tvFileName.setText((String) mDatas.get(position).get("name"));
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {
            ImageView ivIcon;
            TextView tvAttrs;
            TextView tvFileName;

            public MyViewHolder(View view)
            {
                super(view);
                ivIcon = (ImageView) view.findViewById(R.id.iv_file_icon);
                tvAttrs = (TextView) view.findViewById(R.id.tv_attrs);
                tvFileName = (TextView) view.findViewById(R.id.tv_file_name);
            }
        }
    }

/*    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch( item.getItemId() ){
            case MENU_ID_BASE+1://删除
                Toast.makeText(this, "删除文件", Toast.LENGTH_LONG).show();
                break;
            case MENU_ID_BASE+2://复制
                Toast.makeText(this, "复制文件", Toast.LENGTH_LONG).show();
                break;
            case MENU_ID_BASE+3://重命名
                Toast.makeText(this, "重命名文件", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onContextItemSelected(item);
    }*/

/*    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN ){
            if ( !isRoot() ){
                goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }*/

/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_lv = (ListView)findViewById(R.id.lv1);
        tvFile = (TextView)findViewById(R.id.tv_file);
        m_tcb = new TouchCallback();
        mItemLclickCb = new OnItemLongClickListenerEx();
        m_tcb.setMainActivity(this);
        m_lv.setOnItemClickListener(m_tcb);
        mItemLclickCb.SetActivity(this);
        mItemLclickCb.SetListView(m_lv);
        m_lv.setOnItemLongClickListener(mItemLclickCb);
        //m_lv.setOnClickListener(m_tcb);

    }*/

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    public class MyMapComparator implements Comparator {

        @Override
        public int compare(Object lhs, Object rhs) {
            HashMap<String, Object> o1 = (HashMap<String, Object>)lhs;
            HashMap<String, Object> o2 = (HashMap<String, Object>)rhs;
            String strName1 = (String) o1.get("name");
            String strName2 = (String) o2.get("name");
            return strName1.compareTo(strName2);
        }

    }

    public class MyFileComparator implements Comparator{

        @Override
        public int compare(Object lhs, Object rhs) {
            File o1 = (File)lhs;
            File o2 = (File)rhs;
            String strName1 = (String) o1.getName();
            String strName2 = (String) o2.getName();
            return strName1.compareTo(strName2);
        }

    }

    public ArrayList<HashMap<String, Object>> getAddFiles(File f){
        if ( f.isFile() ){
            showFileDlg(f);
            return null;
        }
        File[] fs = f.listFiles();
        if ( fs == null || fs.length == 0 ){
            toast("没有权限");
            return null;
        }
        mFileList.clear();
        m_strDir = f.getPath();
        if (isRoot())
            tvFile.setText("根目录");
        else
            tvFile.setText(m_strDir);
        for(File f1:fs)
            mFileList.add(f1);
        Collections.sort(mFileList, new MyFileComparator());
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        for (File f1: mFileList){
            HashMap<String, Object> map = new HashMap<String, Object>();
            String strName = f1.getName();
            if (f1.isDirectory()) {
                map.put("image", R.drawable.ic_dir);
            } else {
                map.put("image", getFileImgId(strName));
            }
            map.put("attrs", GetFileAttrs(f1));
            map.put("name", strName);
            listItem.add(map);
        }

        return listItem;
//        SimpleAdapter sa = new SimpleAdapter(getActivity(), listItem, R.layout.item_recycleview_files,
//                new String[] {"image", "attrs", "name"},
//                new int[]{R.id.iv_file_icon, R.id.id_attrs, R.id.id_file_name});
//        rcvFiles.setAdapter(sa);
    }
    public String GetFileAttrs(File f){
        String str = "";
        if ( f.canRead() )
            str += "读 ";
        if ( f.canWrite() )
            str += "写 ";
        if ( f.canExecute() )
            str += "执行";
        if ( str.isEmpty() )
            str = "无权限";
        return str;
    }
    public File GetFileByIndex(int nIndex){
        if ( mFileList == null )
            return null;
        int nCount = mFileList.size();
        if ( nIndex<0 || nIndex>=nCount )
            return null;
        return mFileList.get(nIndex);
    }
    public boolean isRoot(){
        if ( (m_strDir.compareTo(".") == 0) || (m_strDir.compareTo("./") == 0) )
            return true;
        return false;
    }
    public void goBack(){
        if ( isRoot() ){
            return ;
        }
        int nPos = m_strDir.lastIndexOf("/");
        String str = m_strDir.substring(0, nPos);
        File f = new File(str);
        getAddFiles(f);
    }
    public int getFileImgId(String fileName){
        int nPos = fileName.lastIndexOf(".");
        if ( nPos == -1 || nPos == fileName.length()-1 )
            return R.drawable.file;
        int nPos1 = fileName.lastIndexOf("/");
        if ( nPos1 > nPos )
            return R.drawable.file;
        String strExt = fileName.substring(nPos+1);
        if ( strExt.compareToIgnoreCase("xml") == 0 )
            return R.drawable.xml;
        if ( strExt.compareToIgnoreCase("mp3") == 0 )
            return R.drawable.mp3;
        if ( strExt.compareToIgnoreCase("bat") == 0 )
            return R.drawable.bat;
        if ( strExt.compareToIgnoreCase("rc") == 0 )
            return R.drawable.rc;
        if ( strExt.compareToIgnoreCase("db") == 0 )
            return R.drawable.db;
        if ( strExt.compareToIgnoreCase("doc") == 0 )
            return R.drawable.doc;
        if ( strExt.compareToIgnoreCase("gif") == 0 )
            return R.drawable.gif;
        if ( strExt.compareToIgnoreCase("ico") == 0 )
            return R.drawable.ico;
        if ( strExt.compareToIgnoreCase("jpg") == 0
                || strExt.compareToIgnoreCase("jpeg") == 0
                || strExt.compareToIgnoreCase("bmp") == 0 )
            return R.drawable.jpg;
        if ( strExt.compareToIgnoreCase("png") == 0 )
            return R.drawable.png;
        return R.drawable.file;
    }
    public void showFileDlg(File f) {
        Intent in = new Intent(getActivity(), FileActivity.class);
        //Bundle b = new Bundle();
        //b.putString("filename", f.getPath());
        in.putExtra("filename", f.getPath());
        //in.putExtras(b);
        startActivity(in);
    }
}
