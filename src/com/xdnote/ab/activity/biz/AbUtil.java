package com.xdnote.ab.activity.biz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.xdnote.ab.util.ConfigManager;
import com.xdnote.ab.util.M;

/**
 * @author xdnote.com
 * <p></p>
 */
public class AbUtil extends Thread {


    /**
     *结果Bean
     */
    private AbUtilBean bean;

    /**
     *结果Bean
     */
	private List<Regs> reglist;

	/**
	 *pub
	 * */
	public AbUtil(String filename, String url, String totalcount,String meancount, String limit,
            boolean keeplive, boolean useHead) {
        super();
        this.bean = new AbUtilBean();
        this.bean.setFilename(filename);
        this.bean.setUrl(url);
        this.bean.setTotalcount(totalcount);
        this.bean.setMeancount(meancount);
        this.bean.setLimit(limit);
        this.bean.setKeeplive(String.valueOf(keeplive));
        this.bean.setUseHead(String.valueOf(useHead));
        reglist = new ArrayList<Regs>();
        reglist.add(new Regs("Server Software", "serverName"));
    }

    class Regs {
	    /**
	     * @since 1.0
	     * */
		private String regexp;
		private String field;
		public Regs(String regexp, String field) {
			super();
			this.setRegexp(regexp);
			this.setField(field);
		}
        public String getRegexp() {
            return regexp;
        }
        public void setRegexp(String regexp) {
            this.regexp = regexp;
        }
        public String getField() {
            return field;
        }
        public void setField(String field) {
            this.field = field;
        }

	}
    /**
     * 写入文件流
     * */
    private BufferedWriter  bw;
	@Override
    public void run() {
//        super.run();
	    this.createFile();
        this.exeABtest();
        this.closeFile();
    }

	
	private void createFile(){
        File file=new File(ConfigManager.getValue(M.T_LOG_PATH));
        if(file.exists()&&file.isDirectory()){
            try {
                File logFile = File.createTempFile(this.bean.getFilename(), ".log", file);
                this.bw=new BufferedWriter(new FileWriter(logFile));
                this.appendLog(M.Q_DATE,this.bean.getFilename());
                this.appendLog(M.Q_URL,this.bean.getUrl());
                this.appendLog(M.Q_COUNT, this.bean.getTotalcount());
                this.appendLog(M.Q_MEAN, this.bean.getMeancount());
                this.appendLog(M.Q_LIMIT, this.bean.getLimit());
                this.appendLog(M.Q_KEEPLIVE, String.valueOf(this.bean.isKeeplive()));
                this.appendLog(M.Q_USEHEAD, String.valueOf(this.bean.isUseHead()));
                this.appendLog("--------------------------------------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
	}
	
	private void exeABtest(){
        String cmd ="\""+ ConfigManager.getValue(M.T_APACHE_PATH) + "\"";
//      -n requests         一共发送的请求数量
//      -c concurrency      每次发送的请求数量
//      -t timelimit            每次请求开始发送前的等待时间
//      -i                          使用head发送（默认Get）
//      -k                   使用HTTP keepAlive特性
//      -C attribute    增加一个Cookie(方式如 Apache=1234)
//      -H attribute    增加一个请求头(方式如 Accept-Encoding: gzip)
        String cmdOpt = " -n "+this.bean.getTotalcount(); 
        if(!this.bean.getMeancount().equals("")){
            cmdOpt += " -c " + this.bean.getMeancount();
        }
        if(!this.bean.getLimit().equals("")){
            cmdOpt += " -t " + this.bean.getLimit();
        }
        if(this.bean.isKeeplive()){
            cmdOpt += " -k ";
        }
        if(this.bean.isUseHead()){
            cmdOpt += " -i ";
        }
        cmdOpt += " "+this.bean.getUrl();
        cmd += cmdOpt;
	    
        Runtime rt=Runtime.getRuntime();
        this.appendLog(cmd);
        this.appendLog("--------------------------------------------------");
        try {
            Process proc=rt.exec(cmd);
            BufferedReader bf =new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String tmp = bf.readLine();
            while (tmp != null) {
                this.appendLog(tmp);
                tmp = bf.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private void appendLog(String str) {
        try {
//            System.out.println(str);
            bw.write(str);
            bw.newLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
    private void appendLog(String key,String value){
         this.appendLog(key+":"+value);
    }
	
	private void closeFile(){
	    try {
            this.bw.flush();
            this.bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}}
