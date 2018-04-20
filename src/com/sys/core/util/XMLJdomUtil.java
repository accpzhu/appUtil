package com.sys.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Text;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPath;

/**
 * XMLJdom工具
 * @author zzs
 *
 */
public class XMLJdomUtil {
	
	
	/**
	 * 获取xml文件root节点
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException 
	 * @throws JDOMException 
	 * @throws FileNotFoundException 
	 */
	public Element getRootElement(String filePath) throws FileNotFoundException, JDOMException, IOException{
		
		  SAXBuilder sb = new SAXBuilder();
		  
		  Document doc;

		  doc = sb.build(new FileInputStream(new File(filePath)));

		  return doc.getRootElement();
	}
	
	

	/**
	 * 文件流写xml
	 * @param filePath
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void xmlOutForPath(Document doc,String filePath) throws FileNotFoundException, IOException{
		
		 XMLOutputter out = new XMLOutputter();
		 
		 out.output(doc,new FileOutputStream(new File(filePath)));
	}

}
