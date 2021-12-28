//package com.lin.demo.utils.copy;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.tika.Tika;
//import org.apache.tika.exception.TikaException;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.net.URL;
//
///**
// * @author linpu
// * @dateTime 2021/12/27 14:21
// * @email im.linpu@qq.com
// * @description
// */
//@Slf4j
//public class PdfUtils {
//    public static boolean checkPdf(String urlStrng) {
////        try {
////            Tika tika = new Tika();
////            URL url = new URL(urlStrng);
////            File file = new File("");
////            InputStream inputStream = new FileInputStream(file);
////            tika.parseToString(inputStream);
////            tika.parseToString(file);
////            tika.parseToString(url);
////            //System.out.println(sb);
////            return true;
////        } catch (TikaException e) {
////            e.printStackTrace();
////            log.info(e.toString());
////            return false;
////        } catch (Exception e) {
////            e.printStackTrace();
////            return false;
////        }
//
//
//        Tika tika = new Tika();
//// 标识损坏文件数
//        int n = 0;
//// 标识损坏文件位置
//        int[] faultFlag = new int[multipartFiles.length];
////multipartFiles是上传的文件数组，根据自己的需求来获取真实名称，此处为我的业务场景。
//        String[] temp = multipartFiles[i].getOriginalFilename().split("\\.");
////获取文件现用格式
//        String fileType = temp[temp.length - 1];
//        if ("doc".equals(fileType)) {
//            // 正常doc:application/x-tika-msoffice ，正常txt:text/plain
//            if (!"application/x-tika-msoffice".equals(tika.detect(multipartFiles[i].getInputStream()))) {
//                // 对损坏文件标识
//                faultFlag[i] = 1;
//                n++;
//            }
//
//
//
//        }
//
//    public static void main(String[] args) {
//        long begin = System.currentTimeMillis();
////        boolean b = checkPdf("https://gongbaojinji.oss-cn-hangzhou.aliyuncs.com/pdf/unified/report/20211227112000&1475296477603258369.pdf");
//        boolean b = checkPdf("https://pcistest.zsins.com/vch/service/CPlyNoDownLoadServlet?&CPlyNo=2993208051620210000035&id=7731abd5a0413f712c5bd32de8554247");
//        long spendTime = System.currentTimeMillis()- begin;
//        System.out.println("spendTime = " + spendTime);
//        System.out.println("b = " + b);
//    }
//}
