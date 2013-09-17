package com.xdnote.ab.activity;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.xdnote.ab.WindowFrame;
import com.xdnote.ab.activity.biz.AbUtilBean;
import com.xdnote.ab.activity.biz.AbUtilBean.UseTime;
import com.xdnote.ab.activity.biz.HistoryUtil;
import com.xdnote.ab.skin.Button;
import com.xdnote.ab.skin.Label;
import com.xdnote.ab.skin.Table;
/**
 * @author xdnote.com
 * <p>历史记录查看类</p>
 * */
public class HistoryActivity extends WindowFrame {
    /**
     * <p>按钮 - 上一条</p>
     * */
    private Button prev;
    /**
     * <p>按钮 - 下一条</p>
     * */
    private Button next;
    /**
     * <p>文本 - 当前条数/总条数</p>
     * */
    private Label offset;
    /**
     * <p>文本 - 服务器主机：端口</p>
     * */
    private Label serverHostPort;
    /**
     * <p>文本 - 是否HEAD发送请求，是否使用keepLive特性</p>
     * */
    private Label requestFuture;
    /**
     * <p>文本 - 请求路径</p>
     * */
    private Label requestPath;
    /**
     * <p>文本 - 文档大小</p>
     * */
    private Label docSize;
    /**
     * <p>文本 - 服务器名称</p>
     * */
    private Label serverName;
    /**
     * <p>请求总数量</p>
     * */
    private Label totalCount;
    /**
     * <p>失败总数量</p>
     * */
    private Label failCount;
    /**
     * <p>并发总数量</p>
     * */
    private Label concurrencyLevel;
    /**
     * <p>并发间隔</p>
     * */
    private Label concurrencyTime;
    /**
     * <p>共用时间</p>
     * */
    private Label costTime;
    /**
     *<p> 重要指标：平均每秒请求数（吞吐率）</p>
     * */
    private Label meanCount;
    /**
     *<p> 重要指标：每个请求响应时间  （响应时间）</p>
     * */
    private Label meanTime;
    /**
     * <p>总流量</p>
     * */
    private Label totalTransfer;
    /**
     * <p>HTML耗费的流量</p>
     * */
    private Label htmlTransfer;
    /**
     *<p> 时间耗费表</p>
     * */
    private Table costTimes;
    /**
     * <p>用户打开时长表</p>
     * */
    private Table userTimes;
    /**
     * <p>用户打开时长表</p>
     * */
    private Button viewLogFile;
    /**
     * <p>当前文件位置</p>
     * */
    private int currentOffset;
    /**
     * <p>日志处理对象</p>
     * */
    private HistoryUtil util;
    /**
     * <p>初始化面板，绘出显示布局</p>
     * */
    private void initPanel() {
        this.util = new HistoryUtil();
        JPanel panel = new JPanel();
        if (this.util.getHistoryCount() == 0) {
            panel.add(new Label("还没有日志哦"));
            this.add(panel);
            return;
        }
        panel.setLayout(new GridBagLayout());


        this.prev = new Button();
        prev.setText("上一个");
        prev.addActionListener(this);
        prev.setEnabled(false);
        this.next = new Button();
        next.setText("下一个");
        next.addActionListener(this);
        next.setEnabled(false);
        this.offset = new Label();
        this.viewLogFile = new Button();
        viewLogFile.setText("查看日志文件");
        viewLogFile.addActionListener(this);
        viewLogFile.setEnabled(false);
        //gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets, ipadx, ipady
        GridBagConstraints oprev = new GridBagConstraints(0, 0, 3, 1, 0.1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        GridBagConstraints ooffset = new GridBagConstraints(3, 0, 3, 1, 0.15, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        GridBagConstraints onext = new GridBagConstraints(6, 0, 3, 1, 0.1, 0, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        GridBagConstraints ospace1 = new GridBagConstraints(9, 0, 6, 1, 0.5, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        GridBagConstraints oviewfile = new GridBagConstraints(15, 0, 4, 1, 0.15, 0, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(prev, oprev);
        panel.add(offset, ooffset);
        panel.add(next, onext);
        panel.add(new Label(" "), ospace1);
        panel.add(viewLogFile, oviewfile);

        this.serverHostPort = new Label("主机信息：");
        this.requestFuture = new Label("请求参数：");
        GridBagConstraints oserverHostPort = new GridBagConstraints(0, 1, 12, 1, 0.5, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0);
        GridBagConstraints orequestFuture = new GridBagConstraints(12, 1, 8, 1, 0.5, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0);
        panel.add(serverHostPort, oserverHostPort);
        panel.add(requestFuture, orequestFuture);

        this.requestPath = new Label("请求路径：");
        this.docSize = new Label("文件大小：");
        GridBagConstraints orequestPath = new GridBagConstraints(0, 2, 10, 1, 0.5, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0);
        GridBagConstraints odocSize = new GridBagConstraints(12, 2, 8, 1, 0.5, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0);
        panel.add(requestPath, orequestPath);
        panel.add(docSize, odocSize);

        this.serverName = new Label("服务器信息：");
        GridBagConstraints oserverName = new GridBagConstraints(0, 3, 20, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0);
        panel.add(serverName, oserverName);

        this.totalCount = new Label("请求总数：");
        this.failCount = new Label("失败数量：");
        GridBagConstraints ototalCount = new GridBagConstraints(0, 4, 6, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0);
        GridBagConstraints ofailCount = new GridBagConstraints(6, 4, 6, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0);
        panel.add(totalCount, ototalCount);
        panel.add(failCount, ofailCount);

        this.concurrencyLevel = new Label("并发数量：");
        this.concurrencyTime = new Label("并发间隔：");
        GridBagConstraints oconcurrencyLevel = new GridBagConstraints(0, 5, 6, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0);
        GridBagConstraints oconcurrencyTime = new GridBagConstraints(6, 5, 6, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0);
        panel.add(concurrencyLevel, oconcurrencyLevel);
        panel.add(concurrencyTime, oconcurrencyTime);

        this.meanCount = new Label("平均用时（吞吐率）：");
        this.costTime = new Label("共耗时长：");
        GridBagConstraints omeanCount = new GridBagConstraints(0, 6, 6, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0);
        GridBagConstraints ocostTime = new GridBagConstraints(6, 6, 6, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0);
        panel.add(meanCount, omeanCount);
        panel.add(costTime, ocostTime);

        this.meanTime = new Label("平均响应：");
        GridBagConstraints omeanTime = new GridBagConstraints(0, 7, 20, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0);
        panel.add(meanTime, omeanTime);

        this.totalTransfer = new Label("共计流量");
        this.htmlTransfer = new Label("HTML占用流量");
        GridBagConstraints ototalTransfer = new GridBagConstraints(0, 8, 6, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0);
        GridBagConstraints ohtmlTransfer = new GridBagConstraints(6, 8, 6, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0);
        panel.add(totalTransfer, ototalTransfer);
        panel.add(htmlTransfer, ohtmlTransfer);

        this.costTimes = new Table();
        GridBagConstraints ocostTimes = new GridBagConstraints(0, 9, 20, 5, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0);
        panel.add(costTimes, ocostTimes);

        this.userTimes = new Table();
        GridBagConstraints ouserTimes = new GridBagConstraints(0, 14, 10, 8, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0);
        panel.add(userTimes, ouserTimes);

        this.currentOffset = 0;

        this.add(panel);
        this.switchPanel(this.currentOffset);

    }
    /**
     * @param index 日志的位置
     * <p>
     * 切换显示的日志
     * </p>
     * */
    private void switchPanel(final int index) {
        this.currentOffset = index;
        int total = util.getHistoryCount();
        if (total > 0) {
            this.viewLogFile.setEnabled(true);
            if (index > 0) {
                this.prev.setEnabled(true);
            } else {
                this.prev.setEnabled(false);
            }
            if ((index + 1) < total) {
                this.next.setEnabled(true);
            } else {
                this.next.setEnabled(false);
            }
        } else {
            this.viewLogFile.setEnabled(false);
        }
        this.offset.setText((index + 1) + " / " + total);
        AbUtilBean bean = this.util.getResult(index);
        this.serverHostPort.changeValue(bean.getHost() + ":" + bean.getPort());
        this.requestFuture.changeValue((bean.isKeeplive() ? "HTTP keepLive  "
                : "") + (bean.isUseHead() ? "使用HEAD发送" : ""));
        this.requestPath.changeValue(bean.getPath());
        this.docSize.changeValue(bean.getSize());
        this.serverName.changeValue(bean.getServerName());
        this.totalCount.changeValue(bean.getCompleteRequests());
        this.failCount.changeValue(bean.getFailedRequests());
        this.concurrencyLevel.changeValue(bean.getSendCount());
        this.concurrencyTime.changeValue(bean.getLimit());
        this.meanCount.changeValue(bean.getThroughRate());
        this.costTime.changeValue(bean.getTotalltime());
        this.meanTime.changeValue(bean.getMeanTime());
        this.totalTransfer.changeValue(bean.getTransferred());
        this.htmlTransfer.changeValue(bean.getHtmltransfered());

        DefaultTableModel model = (DefaultTableModel) this.costTimes.getModel();
        model.setColumnCount(6);
        model.setRowCount(0);
        model.addRow(new String[]{"", "最小", "中等", "最大", "平均", "上下"});
        UseTime connect = bean.getConnect();
        UseTime processing = bean.getProcess();
        UseTime wait = bean.getWait();
        UseTime totalt = bean.getTotal();
        model.addRow(new String[]{"连接耗时", connect.getMin(), connect.getMedian(), connect.getMax(), connect.getMean(), connect.getLimit()});
        model.addRow(new String[]{"执行耗时", processing.getMin(), processing.getMedian(), processing.getMax(), processing.getMean(), processing.getLimit()});
        model.addRow(new String[]{"等待耗时", wait.getMin(), wait.getMedian(), wait.getMax(), wait.getMean(), wait.getLimit()});
        model.addRow(new String[]{"总耗时", totalt.getMin(), totalt.getMedian(), totalt.getMax(), totalt.getMean(), totalt.getLimit()});
        this.costTimes.repaint();

        DefaultTableModel modelu = (DefaultTableModel) this.userTimes.getModel();
        modelu.setColumnCount(2);
        modelu.setRowCount(0);
        modelu.addRow(new String[]{"用户百分比", "连接最大用时"});
        HashMap<String, String> map = bean.getResponseTime();
        for(String key:map.keySet()){
            modelu.addRow(new String[]{key, map.get(key)});
        }
    }

    public HistoryActivity() {
        super();
        this.setSize(650, 700);
        this.resetBounds();
        this.initPanel();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.prev) {
            this.switchPanel(this.currentOffset - 1);
        } else if (e.getSource() == this.next) {
            this.switchPanel(this.currentOffset + 1);
        } else if (e.getSource() == this.viewLogFile) {
            new FileViewActivity(this.util.getFile(this.currentOffset));
        }
        super.actionPerformed(e);
    }


}
