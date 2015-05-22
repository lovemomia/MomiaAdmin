package com.momia.until;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by duohongzhi on 2015/5/22.
 */
public class PropertyParameter {

    private static Logger logger = LoggerFactory.getLogger(PropertyParameter.class);

    public static final String CONFIG_FILE = PropertyParameter.class.getResource("/").getPath().toString().replaceAll("file:/", "") + "config.properties";

   /* public static void main(String[] args) {

        Properties prop = new Properties();
        try {
            InputStream in = new BufferedInputStream (new FileInputStream(CONFIG_FILE));
        prop.load(in);     ///加载属性列表
        Iterator<String> it = prop.stringPropertyNames().iterator();
        while(it.hasNext()){
            String key=it.next();
            System.out.println(key+":"+prop.getProperty(key));
        }
        in.close();

    }catch (Exception e){
        logger.error("系统配置文件" + CONFIG_FILE + "不存在!");
        throw new IllegalArgumentException("系统配置文件"+CONFIG_FILE+"不存在!");
    }
    }*/

    public static Properties loadProperties(){
        Properties prop = new Properties();
        try {
            //读取属性文件config.properties
            InputStream in = new BufferedInputStream (new FileInputStream(CONFIG_FILE));
            prop.load(in);     ///加载属性列表
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
               // System.out.println(key+":"+prop.getProperty(key));
            }
            in.close();

        }catch (Exception e){
            logger.error("系统配置文件" + CONFIG_FILE + "不存在!");
            throw new IllegalArgumentException("系统配置文件"+CONFIG_FILE+"不存在!");
        }

        return prop;
    }
}
