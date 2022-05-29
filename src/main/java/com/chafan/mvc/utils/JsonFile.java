package com.chafan.mvc.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

/**
 * @Auther: 茶凡
 * @ClassName JsonFile
 * @Description TODO
 * @date 2022/5/22 10:09
 * @Version 1.0
 */
public class JsonFile {

    /**
     * 将JSON数据格式化并保存到文件中
     *
     * @param
     * @param jsonData 需要输出的json数
     * @param filePath 输出的文件地址
     * @return
     */
    public static boolean createJsonFile( Object jsonData, String filePath) {
        String content = JSON.toJSONString(jsonData, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        // 标记文件生成是否成功
        boolean flag = true;
        // 生成json格式文件
        try {
            // 保证创建一个新文件
            File file = new File(filePath);
            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // 如果已存在,删除旧文件
                file.delete();
            }
            file.createNewFile();
            // 将格式化后的字符串写入文件
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(content);
            write.flush();
            write.close();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public static String nioMethod(File file) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(file.getPath())));


        return jsonString;
    }

//    public static void main(String[] args) throws IOException {
//        File file = new File("src\\main\\resources\\static\\file\\class.json");
//        System.out.println(nioMethod(file));
//    }

//    public static void main(String[] args) {
//
//
//        createJsonFile("hello","src\\main\\resources\\static\\file\\class.json");
//        createJsonFile("hello","src\\main\\resources\\static\\file\\grade.json");
//
//
//    }


}
