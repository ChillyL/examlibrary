package com.chilly.examlibrary.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @auther ChillyLin
 * @create 2020/12/24
 */
public class UploadUtil {

    /**
     * 下载
     * 由于HTTP头部的默认编码为ISO-8859-1而我们上传文件于下载文件过程中，提取到的文件名都要通过HTTP头部。
     *所以我们需要在上传的时候对提取到的文件名进行转码为UTF-8，然后在下载时我们也要进行反向转码为ISO-8859-1.
     * @param res
     * @param pathAddress
     */
    public static void down(HttpServletResponse res, String pathAddress) throws UnsupportedEncodingException {
        File file=new File(pathAddress);
        String fileName = file.getName();

        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"),"iso-8859-1"));

        byte[] buff = new byte[1024];
        FileInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new FileInputStream(file);
            int readTmp = 0;
            while ((readTmp = bis.read(buff)) != -1) {
                //并不是每次都能读到1024个字节，所有用readTmp作为每次读取数据	的长度，否则会出现文件损坏的错误
                os.write(buff, 0, readTmp);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("success");
    }

}
