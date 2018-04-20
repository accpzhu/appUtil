package com.sys.core.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * 系统工具类
 * @author Administrator
 *
 */
public class ProcessUtil {

    public static void runbat(String batName) {
        try {
            Process ps = Runtime.getRuntime().exec(batName);
            InputStream in = ps.getInputStream();
            int c;
            while ((c = in.read()) != -1) {
                System.out.print(c);// 如果你不需要看输出，这行可以注销掉
            }
            in.close();
            ps.waitFor();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("child thread done");
    }

    public static void main(String[] args) {
    
        String batName = "F:\\database_backup\\ngx_backup\\backup_ngx.bat";
        ProcessUtil.runbat(batName);
        System.out.println("main thread");
    }

}
