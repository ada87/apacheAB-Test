package com.xdnote.ab.activity.biz;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author xdnote.com
 * <pre>
 *    封装AB测试结果类。
 *    包含请求体，响应体。
 *         请求体对应页面上的输入：
 *               请求链接，请求次数，每批次数，等待时间，是否使用keepLive,useHead等
 *         响应体对应CMD命令里面的输出：
 *               服务器信息，响应情况（失败比），吞吐率，时间等重要指标
 * </pre>
 * */
public class AbUtilBean {

    /**
    * 日志文件名称，以任务运行时的时间命名，精确到秒
    */
    private String filename = "";
    /**
    * 请求地址，测试任务的请求地址
    */
    private String url = "";
    /**
     * 共请求次数 对应ab里面的 -n {count}
     */
    private String totalcount = "";
    /**
     * 并发量 对应 ab里面的 -c {count}
     * */
    private String meancount = "";
    /**
     * 每次的等待时间 对应ab里面的 -t {time}
     */
    private String limit = "";
    /**
     * 是否使用HTTP里面的keepLive特性 对应ab里面的 -k
     */
    private boolean keeplive = false;
    /**
     * 是否使用HEAD方式发送请求 -u
     */
    private boolean useHead = false;


    /**
     * @return String 文件名，即任务开始时的时间
     * */
    public final String getFilename() {
        return filename;
    }

    /**
     * @param  fileName  String 文件名称
     * */
    @SetField(name = "Q_DATE")
    public final void setFilename(final String fileName) {
        this.filename = fileName;
    }

    /**
     * @return 文件名，即任务开始时的时间
     * */
    public final String getUrl() {
        return url;
    }

    /**
     * @param  requesturl  String 测试链接
     * */
    @SetField(name = "Q_URL")
    public final void setUrl(final String requesturl) {
        this.url = requesturl;
    }

    /**
     * @return String 发起的请求数
     * */
    public final String getTotalcount() {
        return totalcount;
    }


    /**
     * 设置发起的请求总数
     * @param totalCount 请求数
     * */
    @SetField(name = "Q_COUNT")
    public final void setTotalcount(final String totalCount) {
        this.totalcount = totalCount;
    }

    /**
     * 获取每次发送的请求数（并发量）
     * @return meancount 每次发送的请求数（并发量）
     * */
    public final String getMeancount() {
        return meancount;
    }

    /**
     * @param  meanCount  String 并发个数
     * */
    @SetField(name = "Q_MEAN")
    public final void setMeancount(final String meanCount) {
        this.meancount = meanCount;
    }

    /**
     * @return  limit  请求批次的分割时间
     * */
    public final String getLimit() {
        return limit;
    }
    /**
     * @param  l  String 等待时长
     * */
    @SetField(name = "Q_LIMIT")
    public final void setLimit(final String l) {
        this.limit = l;
    }

    /**
     * @return  boolean 是否使用HTTP的 Keeplive 特性
     * */
    public final boolean isKeeplive() {
        return keeplive;
    }

    /**
     * @param  k  String 是否启用keepLive特性
     * */
    @SetField(name = "Q_KEEPLIVE")
    public final void setKeeplive(final String k) {
        this.keeplive = Boolean.valueOf(k);
    }

    /**
     * @return  boolean 是否使用HEAD方式发送请求
     * */
    public final boolean isUseHead() {
        return useHead;
    }

    /**
     * @param  u  String 是否启用keepLive特性
     * */
    @SetField(name = "Q_USEHEAD")
    public final void setUseHead(final String u) {
        this.useHead = Boolean.valueOf(u);
    }

    /**
     * 服务器名
     * */
    private String serverName;
    /**
     * 主机
     * */
    private String host;
    /**
     * 端口
     * */
    private String port;
    /**
     * 请求URI
     * */
    private String path;
    /**
     * 文档大小
     * */
    private String size;

    /**
     * 同时并发数
     * */
    private String sendCount;
    /**
     * 总共使用时间
     * */
    private String totalltime;

    /**
     * 完成的请求数
     * */
    private String completeRequests;
    /**
     * 失败的请求数
     * */
    private String failedRequests;
    /**
     * 总流量
     * */
    private String transferred;
    /**
     * HTML占用的流量
     * */
    private String htmltransfered;
    /**
     * 平均每秒请求数  -- 重要指标 （吞吐率）
     * */
    private String throughRate;
    /**
     * 平均每个请求响应时间  -- 重要指标 （连接时间）
     * */
    private String meanTime = "";
    /**
     * 平均後秒的流量 --
     * */
    private String meanTransfer;


    /**
     * 连接时间
     * */
    private UseTime connect;
    /**
     * 执行时间
     * */
    private UseTime process;
    /**
     * 等待时间
     * */
    private UseTime wait;
    /**
     * 总共时间
     * */
    private UseTime total;
    /**
     * 用户响应时间
     * */
    private HashMap<String, String> responseTime = new HashMap<String, String>();

    /**
     * @author xdnote.com
     * <pre>
     * 内部类，存放连接、执行、等待、共花费的时间
     * </pre>
     * */
    public class UseTime {
        /**
        *最小时间
        * */
        private String min;
        /**
        * 中等用时
         * */
        private String median;
        /**
         * 最大用时
         * */
        private String max;
        /**
         * 平均用时
         * */
        private String mean;
        /**
         * 一般落差
         * */
        private String limit;

        /**
         * @return String 最小花费时间
         * */
        public String getMin() {
            return min;
        }
        /**
         * @return String 中等花费时间
         * */
        public String getMedian() {
            return median;
        }
        /**
         * @return String 最大花费时间
         * */
        public String getMax() {
            return max;
        }
        /**
         * @return String 平均花费时间
         * */
        public String getMean() {
            return mean;
        }
        /**
         * @return String 平均花费时间的平均上下落差
         * */
        public String getLimit() {
            return limit;
        }
        /**
         * @param str 日志解析字符串
         * */
        public UseTime(final String str) {
            Pattern p = Pattern.compile("\\s*([\\d\\.]+)\\s+([\\d\\.]+)"
                    + "\\s+([\\d\\.]+)\\s+([\\d\\.]+)\\s+([\\d\\.]+)\\s*");
            Matcher m = p.matcher(str);
            if (m.find()) {
                this.min = m.group(1);
                this.mean = m.group(2);
                this.limit = m.group(3);
                this.median = m.group(4);
                this.max = m.group(5);
            }
        }
    }

    /**
     * @return 获取服务名名称
     * */
    public final String getServerName() {
        return serverName;
    }

    /**
     * @param s 服务名名称
     * */
    @SetField(name = "Server Software")
    public final void setServerName(final String s) {
        this.serverName = s;
    }
    /**
     * @return  URL HOST部分
     * */
    public final String getHost() {
        return host;
    }
    /**
     * @param  h URL HOST部分
     * */
    @SetField(name = "Server Hostname")
    public final void setHost(final String h) {
        this.host = h;
    }
    /**
     * @return  URL PORT部分
     * */
    public final String getPort() {
        return port;
    }
    /**
     * @param  p URL PORT部分
     * */
    @SetField(name = "Server Port")
    public final void setPort(final String p) {
        this.port = p;
    }
    /**
     * @return  URL PATH部分
     * */
    public final String getPath() {
        return path;
    }
    /**
     * @param  p PATH部分
     * */
    @SetField(name = "Document Path")
    public final void setPath(final String p) {
        this.path = p;
    }
    /**
     * @return  文档大小
     * */
    public final String getSize() {
        return size;
    }
    /**
     * @param  s 文档大小
     * */
    @SetField(name = "Document Length")
    public final void setSize(final String s) {
        this.size = s;
    }

    /**
     * @return  并发个数
     * */
    public final String getSendCount() {
        return sendCount;
    }
    /**
     * @param s  并发个数
     * */
    @SetField(name = "Concurrency Level")
    public final void setSendCount(final String s) {
        this.sendCount = s;
    }
    /**
     * @return  测试费时
     * */
    public final String getTotalltime() {
        return totalltime;
    }
    /**
     * @param t 测试费时
     * */
    @SetField(name = "Time taken for tests")
    public final void setTotalltime(final String t) {
        this.totalltime = t;
    }

    /**
     * @return  完成请求数
     * */
    public final String getCompleteRequests() {
        return completeRequests;
    }
    /**
     * @param c 完成请求数
     * */
    @SetField(name = "Complete requests")
    public final void setCompleteRequests(final String c) {
        this.completeRequests = c;
    }

    /**
     * @return  失败数量
     * */
    public final String getFailedRequests() {
        return failedRequests;
    }
    /**
     * @param  f 失败数量
     * */
    @SetField(name = "Failed requests")
    public final void setFailedRequests(final String f) {
        this.failedRequests = f;
    }

    /**
     * @return  总流量
     * */
    public final String getTransferred() {
        return transferred;
    }
    /**
     * @param t  总流量
     * */
    @SetField(name = "Total transferred")
    public final void setTransferred(final String t) {
        this.transferred = t;
    }

    /**
     * @return  html耗费流量
     * */
    public final String getHtmltransfered() {
        return htmltransfered;
    }
    /**
     * @param h  html耗费流量
     * */
    @SetField(name = "HTML transferred")
    public final void setHtmltransfered(final String h) {
        this.htmltransfered = h;
    }

    /**
     * @return  平均每秒请求数 -- 重要指标 （吞吐率）
     * */
    public final String getThroughRate() {
        return throughRate;
    }
    /**
     * @param  t 平均每秒请求数 -- 重要指标 （吞吐率）
     * */
    @SetField(name = "Requests per second")
    public final void setThroughRate(final String t) {
        this.throughRate = t;
    }

    /**
     * @return  平均每个请求响应时间 -- 重要指标 （连接时间）
     * */
    public final String getMeanTime() {
        return meanTime;
    }
    /**
     * @param m 平均每个请求响应时间 -- 重要指标 （连接时间）
     * */
    @SetField(name = "Time per request")
    public final void setMeanTime(final String m) {
        this.meanTime += m;
    }
    /**
     * @return  平均每秒接收到的流量
     * */
    public final String getMeanTransfer() {
        return meanTransfer;
    }
    /**
     * @param m 平均每秒接收到的流量
     * */
    @SetField(name = "Transfer rate")
    public final void setMeanTransfer(final String m) {
        this.meanTransfer = m;
    }

    /**
     * @return  连接用时概况
     * */
    public final UseTime getConnect() {
        return this.connect;
    }
    /**
     * @param c  连接用时概况
     * */
    @SetField(name = "Connect")
    public final void setConnect(final String c) {
        this.connect = new UseTime(c);
    }
    /**
     * @return  执行用时概况
     * */
    public final UseTime getProcess() {
        return process;
    }
    /**
     * @param p  执行用时概况
     * */
    @SetField(name = "Processing")
    public final void setProcess(final String p) {
        this.process = new UseTime(p);
    }

    /**
     * @return  等待用时概况
     * */
    public final UseTime getWait() {
        return wait;
    }
    /**
     * @param w  等待用时概况
     * */
    @SetField(name = "Waiting")
    public final void setWait(final String w) {
        this.wait = new UseTime(w);
    }

    /**
     * @return  总共用时概况
     * */
    public final UseTime getTotal() {
        return total;
    }
    /**
     * @param t 总共用时概况
     * */
    @SetField(name = "Total")
    public final void setTotal(final String t) {
        this.total = new UseTime(t);
    }
    /**
     * @return  用户比例用时映射
     * */
    public final HashMap<String, String> getResponseTime() {
        return responseTime;
    }

    /**
     * @param key 用户比例用时映射条目的KEY
     * @param value 用户比例用时映射条目的值
     * */
    public final void setResponseTime(final String key, final String value) {
        this.responseTime.put(key, value);
    }

}
