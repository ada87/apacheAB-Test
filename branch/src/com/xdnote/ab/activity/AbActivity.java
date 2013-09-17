package com.xdnote.ab.activity;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.xdnote.ab.WindowFrame;
import com.xdnote.ab.activity.biz.AbUtil;
import com.xdnote.ab.skin.Button;
import com.xdnote.ab.skin.CheckBox;
import com.xdnote.ab.skin.Label;
import com.xdnote.ab.skin.Text;
import com.xdnote.ab.util.ConfigManager;
import com.xdnote.ab.util.M;

/**
 * @author xdnote.com
 * <p>
 * 使用AB工具图形化的界面，可输入相关参数后进行测试
 * </p>
 * */
public class AbActivity extends WindowFrame {

    /**
     * 请求地址输入框
     * */
    private Text txt_url;
    /**
     * 输入-文本框 - 共请求次数
     * */
    private Text txt_requestTotalCount;
    /**
     * 输入-文本框 - 每次请求个数（并发）
     * */
    private Text txt_requestMeanCount;
    /**
     * 输入-文本框 - 每次请求间隔时间
     * */
    private Text txt_limitTime;

    /**
     * 输入- CheckBox - 是否启用keepLive
     * */
    private CheckBox cb_keepLive;

    /**
     * 输入- CheckBox - 是否启用Head方式发送
     * */
    private CheckBox cb_useHeader;

    /**
     * Action -Submit 按钮 触发开始测试
     * */
    private Button btn_submit;

    /**
     * Event - 等待过程
     * */
    private Timer evt_submit;
    /**
     * 输出 - 等待过程中的文字提示，以及事件完成的的提示
     * */
    private Label lb_state;
    /**
     * BIZ - 内置对象，用于执行测试的具体过程
     * */
    private AbUtil util;
	/**
     *  序列化参数
     */
    private static final long serialVersionUID = -6976476000827089714L;

    /**
     *<pre>
     *          内置方法  -  初始化Activity时使用，用于加载页面布局，并绑定事件
     *</pre>
     * */
    private void initPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        //gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets, ipadx, ipady
        GridBagConstraints ol_url = new GridBagConstraints(1, 1, 1, 1, 0.1, 1, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        GridBagConstraints ol_requestCount = new GridBagConstraints(1, 2, 1, 1, 0.1, 1, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        GridBagConstraints ol_limitTime = new GridBagConstraints(1, 3, 1, 1, 0.1, 1, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);

        GridBagConstraints ol_requestMeanCount = new GridBagConstraints(3, 2, 1, 1, 0.1, 1, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        
        GridBagConstraints ot_url = new GridBagConstraints(2, 1, 3, 1, 0.8, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0);
        GridBagConstraints ot_requestTotalCount = new GridBagConstraints(2, 2, 1, 1, 0.3, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0);;
        GridBagConstraints ot_requestMeanCount = new GridBagConstraints(4, 2, 1, 1, 0.3, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0);;
        GridBagConstraints ot_limitTime = new GridBagConstraints(2, 3, 3, 1, 0.8, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0);;

        GridBagConstraints oc_keepLive = new GridBagConstraints(2, 4, 1, 1, 0.2, 1, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0);
        GridBagConstraints oc_useHeader = new GridBagConstraints(3, 4, 1, 1, 0.2, 1, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0);

        GridBagConstraints ob_button = new GridBagConstraints(2, 5, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        GridBagConstraints ol_state = new GridBagConstraints(3, 5, 1, 1, 0.2, 1, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0);

        GridBagConstraints space1 = new GridBagConstraints(5, 1, 1, 1, 0.1, 1, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0);


        txt_url = new Text();
        txt_url.setText(ConfigManager.getValue(M.T_TEST_URL));
        txt_requestTotalCount = new Text();
        txt_requestTotalCount.setText(ConfigManager.getValue(M.T_TEST_TOTAL));

        txt_requestMeanCount = new Text();
        txt_requestMeanCount.setText(ConfigManager.getValue(M.T_TEST_COUNT));

        txt_limitTime = new Text();
        txt_limitTime.setText(ConfigManager.getValue(M.T_TEST_LIMIT));

        cb_keepLive = new CheckBox("KeepLive",
          Boolean.valueOf(ConfigManager.getValue(M.T_TEST_KEEPLIVE, "false")
                        ));
        cb_useHeader = new CheckBox("使用HEAD发送请求",
          Boolean.valueOf(ConfigManager.getValue(M.T_TEST_HEAD, "false")
                        ));

        btn_submit = new Button("开始测试");
        btn_submit.addActionListener(this);
        lb_state = new Label();


        panel.add(new Label("每次个数："), ol_requestMeanCount);
        panel.add(new Label(), space1);
        panel.add(new Label("测试地址："),  ol_url);
        panel.add(new Label("请求次数："), ol_requestCount);
        panel.add(new Label("间隔时间："),  ol_limitTime);
        panel.add(txt_url, ot_url);
        panel.add(txt_requestTotalCount, ot_requestTotalCount);
        panel.add(txt_requestMeanCount, ot_requestMeanCount);
        panel.add(txt_limitTime, ot_limitTime);

        panel.add(cb_keepLive, oc_keepLive);
        panel.add(cb_useHeader, oc_useHeader);

        panel.add(btn_submit, ob_button);
        panel.add(lb_state, ol_state);

        this.add(panel);
    }

    /**
     * 无参数构造函数
     *<pre>
     *          构造函数 - 使 new可以直接弹出一个 JFrame.
     *</pre>
     * */
    public AbActivity() {
        super();
        this.initPanel();
        evt_submit = new Timer(1000, this);
        this.setVisible(true);
    }
    /**
     *<pre>
     *          内置方法 - 点击Submit后会调用到。用于运行用例
     *</pre>
     * */
    private void run() {
        //1.disable 页面操作 2.收集url,options信息 3.写入请求 4.执行 5.写入响应 6.enable页面，查看结果
        String url = this.txt_url.getText();
        String total = this.txt_requestTotalCount.getText();
        String count = this.txt_requestMeanCount.getText();
        String limit = this.txt_limitTime.getText();
        boolean keeplive = false;
        boolean useHead = false;
        if (this.cb_keepLive.getSelectedObjects() != null) {
            keeplive = true;
        }
        if (this.cb_useHeader.getSelectedObjects() != null) {
            useHead = true;
        }
        ConfigManager.setValue(M.T_TEST_URL, url);
        ConfigManager.setValue(M.T_TEST_TOTAL, total);
        ConfigManager.setValue(M.T_TEST_COUNT, count);
        ConfigManager.setValue(M.T_TEST_LIMIT, limit);
        ConfigManager.setValue(M.T_TEST_KEEPLIVE, String.valueOf(keeplive));
        ConfigManager.setValue(M.T_TEST_HEAD, String.valueOf(useHead));
       this.util = new AbUtil(
               new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()),
               url, total, count, limit, keeplive, useHead);
       util.start();
    }
    /**
     *@param e 事件
     *<pre>
     *          重写事件 - 处理Submit按钮操作和等待时间操作。
     *</pre>
     * */
    @Override
    public final void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.btn_submit) {
            this.btn_submit.setEnabled(false);
            this.lb_state.setText("测试中，不要关闭窗口.");
            this.evt_submit.start();
            this.run();
        } else {
            if (this.util.getState() == Thread.State.TERMINATED) {
                this.evt_submit.stop();
                this.lb_state.setText("测试完成，在历史记录中查看");
                new HistoryActivity();
                this.dispose();
                return;
            }
            String text = this.lb_state.getText();
            String end = text.substring(10);
            String tend = end.equals(".") ? ".." : ".";
            this.lb_state.setText("测试中，不要关闭窗口" + tend);
        }
     }

}
