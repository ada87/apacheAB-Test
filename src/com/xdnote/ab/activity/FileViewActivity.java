package com.xdnote.ab.activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextArea;

import com.xdnote.ab.WindowFrame;
/**
 * @author xdnote.com
 * <p>单个日志文件查看</p>
 * */
public class FileViewActivity extends WindowFrame {

    /**
     * <p>需要查看的文件</p>
     * */
    private File file;
    /**
     * <p>文件内容显示区域</p>
     * */
    private JTextArea content;

    /**
     * <p>初始化显示的面板</p>
     * */
    private void initPanel() {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.file));
            String tmp = null;
            while ((tmp = br.readLine()) != null) {
                sb.append(tmp);
                sb.append("\r\n");
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        content = new JTextArea();
        content.setText(sb.toString());
        this.add(content);
    }
    /**
     * @param file 文件
     * */
    public FileViewActivity(File file) {
        super();
        this.file = file;
        this.setSize(650, 900);
        this.resetBounds();
        this.initPanel();
        this.setVisible(true);
    }
}
