package com.qianlong.qltt.zbas.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesUtil {
	/**从指定的路径上加载properties文件*/
	public static Properties loadProperties(String filename) throws IOException {
		Properties properties = new Properties();
		FileReader reader = new FileReader(filename);
		properties.load(reader);
		reader.close();
		return properties;
	}  
  
    /**  
    * 更新properties文件的键值对  
    * 如果该主键已经存在，更新该主键的值；  
    * 如果该主键不存在，则插件一对键值。  
    * @param keyname 键名  
    * @param keyvalue 键值  
     * @throws IOException 
     * @throws FileNotFoundException 
    */   
    public static void updateProperties(String filename,String keyname,String keyvalue) throws IOException {   
    	Properties props = loadProperties( filename);
        OutputStream fos = new FileOutputStream(filename);              
        props.setProperty(keyname, keyvalue);   
        props.store(fos, "Update '" + keyname + "' value");   
        fos.flush();
        fos.close();
    }   
}
