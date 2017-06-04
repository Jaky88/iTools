package com.onyx.itools.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.onyx.itools.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/6/4 0004,23:47
 * @Version: V1.0
 * @Description: TODO
 */
public class FileActivity extends Activity {

    private TextView m_tv = null;
    private MediaPlayer m_mp = null;
    private Intent m_it = new Intent("com.demo.SERVICE_PLAYER");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.titlebar);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        TextView tv = (TextView)findViewById(R.id.title_text);
        tv.setText("文件浏览");
        tv.setTextSize(20);
        tv = (TextView)findViewById(R.id.title_left_text);
        tv.setText("标题");
        tv.setTextSize(16);
        ImageButton btn = (ImageButton)findViewById(R.id.title_back);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setContentView(R.layout.file_xml);
        m_tv = (TextView)findViewById(R.id.editText1);

        Intent in = getIntent();
        String strName = in.getStringExtra("filename");

        File f = new File(strName);
        long lSize = f.length();
        if ( lSize > 1024*1024 ){
            int nPos = strName.lastIndexOf('.');
            if ( nPos != -1 ){
                String strExt = strName.substring(nPos+1);
                if ( strExt.compareToIgnoreCase("mp3") == 0 ){
                    m_it.putExtra("filename", strName);
                    startService(m_it);
                    Toast.makeText(this, "开始播放音乐", Toast.LENGTH_LONG).show();
                    return ;
                }
            }

            Toast.makeText(this, "文件太大", Toast.LENGTH_LONG).show();

        }
        else
            ReadFile(f);
    }
    @Override
    protected void onStop() {
        if ( m_mp != null ){
            m_mp.stop();
            m_mp.release();
        }
        stopService(m_it);
        super.onStop();
    }

    public void ReadFile(File f){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            StringBuilder sb = new StringBuilder();
            int nBuffLen = 10*1024; //10KB
            byte[] buff = new byte[nBuffLen];
            while( true ){
                int nRead = fis.read(buff);
                if ( nRead<=0 )
                    break;
                String str = new String(buff, 0, nRead);
                sb.append(str);
                if ( nRead<nBuffLen )
                    break;
            }
            m_tv.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch( IOException e ) {
            e.printStackTrace();
        }
        finally{
            if ( fis != null )
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
