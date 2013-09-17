package com.xdnote.ab.skin;

import javax.swing.JTextField;
import javax.swing.text.Document;

import com.xdnote.ab.util.StringUtil;
/**
 * @author xdnote.com
 * <p>设置样式</p>
 * */
public class Text extends JTextField {

    private void initSkin(){
        this.setFont(StringUtil.font);
    }
    public Text() {
        super();
        this.initSkin();
    }

    public Text(Document arg0, String arg1, int arg2) {
        super(arg0, arg1, arg2);
        this.initSkin();
    }

    public Text(int arg0) {
        super(arg0);
        this.initSkin();
    }

    public Text(String arg0, int arg1) {
        super(arg0, arg1);
        this.initSkin();
    }

    public Text(String arg0) {
        super(arg0);
        this.initSkin();
    }


}
