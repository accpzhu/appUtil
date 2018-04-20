package com.sys.core.util.filestream;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.sys.core.util.UrlOrStrFilter;
/**
 * 文件流操作
 * @author zzs
 *
 */
public class FileUtil {
	
	 private static int BUFFER_SIZE = 8000;
     private static FileOutputStream fos = null;   
     private static BufferedInputStream bis = null;   
     private static HttpURLConnection httpUrl = null;   
     private static URL url = null; 
	/**
	 * 将url地址文件保存到path目录下
	 * @param url
	 * @param path
	 * @throws Exception 
	 */
	public static String  saveFileForHttp(String urlStr,String path) throws Exception{
		
		        byte[] buf = new byte[BUFFER_SIZE];   
		        int size = 0;   
		        
		        if(urlStr==null){
		        	throw new Exception("图片url不能为空");
		        }
		        
		        if(path==null){
		        	throw new Exception("图片保存路径不能为空");
		        }
		        
		        String fileName=UrlOrStrFilter.getLastFilterStr(urlStr, "/");
		        
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
				}
		        
	            url = new URL(urlStr);   
	            httpUrl = (HttpURLConnection) url.openConnection();   
	            // 连接指定的资源   
	            httpUrl.connect();   
	            // 获取网络输入流   
	           bis = new BufferedInputStream(httpUrl.getInputStream());   
	            // 建立文件   
	 
	            fos = new FileOutputStream(path+fileName);   

	            // 保存文件   
	            while ((size = bis.read(buf)) != -1) {
	                fos.write(buf, 0, size);   
	                fos.flush();
	            }
	            fos.close();   
	            bis.close();   
	            httpUrl.disconnect();   
	            
	            return path+fileName;
	            
	          // return UrlOrStrFilter.getFilePathFilterStr(path+fileName, "/");
	            
	}
	
	
	/**  
     *  新建目录  
     *  @param  folderPath  String  如  c:/fqf  
     *  @return  boolean  
     */  
   public static  void  newFolder(String  folderPath)  {  
       try  {  
           String  filePath  =  folderPath;  
           filePath  =  filePath.toString();  
           java.io.File  myFilePath  =  new  java.io.File(filePath);  
           if  (!myFilePath.exists())  {  
               myFilePath.mkdir();  
           }  
       }  
       catch  (Exception  e)  {  
           System.out.println("新建目录操作出错");  
           e.printStackTrace();  
       }  
   }  

   /**  
    *  新建文件  
    *  @param  filePathAndName  String  文件路径及名称  如c:/fqf.txt  
    *  @param  fileContent  String  文件内容  
    *  @return  boolean  
    */  
  public static void  newFile(String  filePathAndName,  String  fileContent)  {  

      try  {  
          String  filePath  =  filePathAndName;  
          filePath  =  filePath.toString();  
          File  myFilePath  =  new  File(filePath);  
          if  (!myFilePath.exists())  {  
              myFilePath.createNewFile();  
          }  
          FileWriter  resultFile  =  new  FileWriter(myFilePath);  
          PrintWriter  myFile  =  new  PrintWriter(resultFile);  
          String  strContent  =  fileContent;  
          myFile.println(strContent);  
          resultFile.close();  
      }  
      catch  (Exception  e)  {  
          System.out.println("新建目录操作出错");  
          e.printStackTrace();  
      }  
  }  

	  /**  
	   *  删除文件  
	   *  @param  filePathAndName  String  文件路径及名称  如c:/fqf.txt  
	   *  @param  fileContent  String  
	   *  @return  boolean  
	   */  
	 public static void  delFile(String  filePathAndName)  {  
	     try  {  
	         String  filePath  =  filePathAndName;  
	         filePath  =  filePath.toString();  
	         java.io.File  myDelFile  =  new  java.io.File(filePath);  
	         myDelFile.delete();  
	
	     }  
	     catch  (Exception  e)  {  
	         System.out.println("删除文件操作出错");  
	         e.printStackTrace();  
	
	     }  
	
	 }  
	
	   /**  
	     *  删除文件夹  
	     *  @param  filePathAndName  String  文件夹路径及名称  如c:/fqf  
	     *  @param  fileContent  String  
	     *  @return  boolean  
	     */  
	   public static void  delFolder(String  folderPath)  {  
	       try  {  
	           delAllFile(folderPath);  //删除完里面所有内容  
	           String  filePath  =  folderPath;  
	           filePath  =  filePath.toString();  
	           java.io.File  myFilePath  =  new  java.io.File(filePath);  
	           myFilePath.delete();  //删除空文件夹  
	 
	       }  
	       catch  (Exception  e)  {  
	           System.out.println("删除文件夹操作出错");  
	           e.printStackTrace();  
	 
	       }  
	 
	   }  
	   
	   /**  
	     *  删除文件夹里面的所有文件  
	     *  @param  path  String  文件夹路径  如  c:/fqf  
	     */  
	   public static void  delAllFile(String  path)  {  
	       File  file  =  new  File(path);  
	       if  (!file.exists())  {  
	           return;  
	       }  
	       if  (!file.isDirectory())  {  
	           return;  
	       }  
	       String[]  tempList  =  file.list();  
	       File  temp  =  null;  
	       for  (int  i  =  0;  i  <  tempList.length;  i++)  {  
	           if  (path.endsWith(File.separator))  {  
	               temp  =  new  File(path  +  tempList[i]);  
	           }  
	           else  {  
	               temp  =  new  File(path  +  File.separator  +  tempList[i]);  
	           }  
	           if  (temp.isFile())  {  
	               temp.delete();  
	           }  
	           if  (temp.isDirectory())  {  
	               delAllFile(path+"/"+  tempList[i]);//先删除文件夹里面的文件  
	               delFolder(path+"/"+  tempList[i]);//再删除空文件夹  
	           }  
	       }  
	   }  

	   
	   /**  
	     *  复制单个文件  
	     *  @param  oldPath  String  原文件路径  如：c:/fqf.txt  
	     *  @param  newPath  String  复制后路径  如：f:/fqf.txt  
	     *  @return  boolean  
	     */  
	   public static void  copyFile(String  oldPath,  String  newPath)  {  
	       try  {  
	           int  bytesum  =  0;  
	           int  byteread  =  0;  
	           File  oldfile  =  new  File(oldPath);  
	           if  (oldfile.exists())  {  //文件存在时  
	               InputStream  inStream  =  new  FileInputStream(oldPath);  //读入原文件  
	               FileOutputStream  fs  =  new  FileOutputStream(newPath);  
	               byte[]  buffer  =  new  byte[1444];  
	               int  length;  
	               while  (  (byteread  =  inStream.read(buffer))  !=  -1)  {  
	                   bytesum  +=  byteread;  //字节数  文件大小  
	                   System.out.println(bytesum);  
	                   fs.write(buffer,  0,  byteread);  
	               }  
	               inStream.close();  
	           }  
	       }  
	       catch  (Exception  e)  {  
	           System.out.println("复制单个文件操作出错");  
	           e.printStackTrace();  
	 
	       }  
	 
	   }  

	   /**  
	     *  复制整个文件夹内容  
	     *  @param  oldPath  String  原文件路径  如：c:/fqf  
	     *  @param  newPath  String  复制后路径  如：f:/fqf/ff  
	     *  @return  boolean  
	     */  
	   public static void  copyFolder(String  oldPath,  String  newPath)  {  
	 
	       try  {  
	           (new  File(newPath)).mkdirs();  //如果文件夹不存在  则建立新文件夹  
	           File  a=new  File(oldPath);  
	           String[]  file=a.list();  
	           File  temp=null;  
	           for  (int  i  =  0;  i  <  file.length;  i++)  {  
	               if(oldPath.endsWith(File.separator)){  
	                   temp=new  File(oldPath+file[i]);  
	               }  
	               else{  
	                   temp=new  File(oldPath+File.separator+file[i]);  
	               }  
	 
	               if(temp.isFile()){  
	                   FileInputStream  input  =  new  FileInputStream(temp);  
	                   FileOutputStream  output  =  new  FileOutputStream(newPath  +  "/"  +  
	                           (temp.getName()).toString());  
	                   byte[]  b  =  new  byte[1024  *  5];  
	                   int  len;  
	                   while  (  (len  =  input.read(b))  !=  -1)  {  
	                       output.write(b,  0,  len);  
	                   }  
	                   output.flush();  
	                   output.close();  
	                   input.close();  
	               }  
	               if(temp.isDirectory()){//如果是子文件夹  
	                   copyFolder(oldPath+"/"+file[i],newPath+"/"+file[i]);  
	               }  
	           }  
	       }  
	       catch  (Exception  e)  {  
	           System.out.println("复制整个文件夹内容操作出错");  
	           e.printStackTrace();  
	 
	       }  
	 
	   }  

	   /**  
	     *  移动文件到指定目录  
	     *  @param  oldPath  String  如：c:/fqf.txt  
	     *  @param  newPath  String  如：d:/fqf.txt  
	     */  
	   public static void  moveFile(String  oldPath,  String  newPath)  {  
	       copyFile(oldPath,  newPath);  
	       delFile(oldPath);  
	 
	   }  
	 
	   /**  
	     *  移动文件到指定目录  
	     *  @param  oldPath  String  如：c:/fqf.txt  
	     *  @param  newPath  String  如：d:/fqf.txt  
	     */  
	   public static void  moveFolder(String  oldPath,  String  newPath)  {  
	       copyFolder(oldPath,  newPath);  
	       delFolder(oldPath);  
	 
	   }  

	
	 /**
	 * @param fileName
	 * @return 去除文件路径与后缀名，返回文件名
	 */
	public static String getFileName(String fileName){
		
		fileName=fileName.replace("\\", "/");
		      
	  return fileName.substring(fileName.lastIndexOf("/"),fileName.lastIndexOf("."));//
	}
	   
	/**返回文件内容字符串
	 * @param path
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String fileInputStreamDemo(String path,String charset) throws IOException{ 
		
		StringBuffer stringBuffer=new StringBuffer();
		
		File file=new File(path); 
		if(!file.exists()||file.isDirectory()) 
		throw new FileNotFoundException(); 
		FileInputStream fis=new FileInputStream(file); 
		
		BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(file),charset));
	    String stemp;
	    while((stemp = bufferedreader.readLine()) != null){
	     
	    	stringBuffer.append(stemp+"\n");
	    }
	return stringBuffer.toString(); 
	} 
	
	/**返回文件内容字符串
	 * @param path
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String getFileStrForNewline(String path,String charset) throws IOException{ 
		
		StringBuffer stringBuffer=new StringBuffer();
		
		File file=new File(path); 
		if(!file.exists()||file.isDirectory()) 
		throw new FileNotFoundException(); 
		FileInputStream fis=new FileInputStream(file); 
		
		BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(file),charset));
	    String stemp;
	    while((stemp = bufferedreader.readLine()) != null){
	     
	    	stringBuffer.append(stemp+"\n");
	    }
	return stringBuffer.toString(); 
	} 
	
	/**
	 * 文件路径分隔符
	 * @return
	 */
	public static String separator(){
		
		
		return "/";
	}
	
    /**
     * 获取文章行数
     * @param str
     * @return
     * @throws IOException 
     */
    public static Integer getStringLineSize(String str) throws IOException{
    	
    	int line = 0;  
    	
    	String tempString="";
    	
    	BufferedReader reader = new BufferedReader(new StringReader(str));
    	
    	while (((tempString = reader.readLine()) != null)){  
    		//显示行号   
    		++line;  
        }  
    	return line;
    }
	

    /**
     * 获取文件行数
     * @param path
     * @return
     * @throws IOException
     */
    public static Integer getFileLineSize(String path) throws IOException{
    	
        FileReader in = new FileReader(path);
        LineNumberReader reader = new LineNumberReader(in);
        String strLine = reader.readLine();
        int totalLines = 0;
        while (strLine != null) {
            totalLines++;
            strLine = reader.readLine();
        }
        reader.close();
        in.close();
        return totalLines;
    }
	
    
    /**
     * 获取文章str下num行信息 (从0开始)
     * @param str
     * @param num
     * @return
     */
    public static String getStringLine(String str,Integer num) throws IOException{
    	
    	int line = 0;  
    	
    	String tempString="";
    	
    	BufferedReader reader = new BufferedReader(new StringReader(str));
    	
    	while (((tempString = reader.readLine()) != null)){  
    		//显示行号   
    		line++;  
    		
    		if(line==num){
    			
    			return tempString;
    		}
        }  
    	return "";
    }
    
    /**
     * 获取文章str下num行信息 (从0开始)
     * @param str
     * @param num
     * @return
     */
    public static String getIndexStringLineForFile(String path,String charset,Integer num) throws IOException{
    	
    	String str=getFileStrForNewline(path,charset);
    	
    	int line = 0;  
    	
    	String tempString="";
    	
    	BufferedReader reader = new BufferedReader(new StringReader(str));
    	
    	while (((tempString = reader.readLine()) != null)){  
    		//显示行号   
    		line++;  
    		
    		if(line==num){
    			
    			return tempString.trim();
    		}
    		
        }  
    	
    	return "";
    }
    
    /**
     * 获取文章行数
     * @param str
     * @return
     * @throws IOException 
     */
    public static Integer getStringLineSizeForFile(String path,String charset) throws IOException{
    	
    	int line = 0;  
    	
    	String tempString="";
    	
    	String str=getFileStrForNewline(path,charset);
    	
    	BufferedReader reader = new BufferedReader(new StringReader(str));
    	
    	while (((tempString = reader.readLine()) != null)){  
    		//显示行号   
    		++line;  
        }  

    	return line;
    }
	   
	public  static void main(String args[]){
		
		for(int i=0;i<50;i++){
			try {
				String lineStr=FileUtil.getIndexStringLineForFile("I:\\bh_app_res\\2015-09-16\\bi_bhqygx_biz_kh_business.sql", "utf-8", i);
			
			System.out.println(lineStr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
