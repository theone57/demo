package com.all.lin.utils;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSONObject;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * 签名
 * 加签/验签
 *
 * @author alex
 */
public class RSASign {

    private static final String CHARSET = "UTF-8";
    private static final String SIGN_TYPE_RSA = "RSA";
    private static final String SIGN_ALGORITHMS = "SHA256withRSA";
    private static ObjectMapper MAPPER = new ObjectMapper();
    private static final String BODY2 = "{\\n\" +\n" +
            "                    \"   \\\"body\\\": {\\n\" +\n" +
            "                    \"       \\\"policyList\\\": [\\n\" +\n" +
            "                    \"           {\\n\" +\n" +
            "                    \"               \\\"CITYID\\\": 9016,\\n\" +\n" +
            "                    \"               \\\"COUNTYID\\\": 3301,\\n\" +\n" +
            "                    \"               \\\"MOB_URL\\\": \\\"www.jd.com\\\",\\n\" +\n" +
            "                    \"               \\\"ZCJD\\\": \\\"www.jd.com\\\",\\n\" +\n" +
            "                    \"               \\\"ZCSB\\\": \\\"www.jd.com\\\",\\n\" +\n" +
            "                    \"               \\\"ZC_TYPE1\\\": \\\"1\\\",\\n\" +\n" +
            "                    \"               \\\"ZC_TYPE2\\\": \\\"1\\\",\\n\" +\n" +
            "                    \"               \\\"ZXDH\\\": \\\"1\\\",\\n\" +\n" +
            "                    \"               \\\"ZGBMID\\\": 1,\\n\" +\n" +
            "                    \"               \\\"PCZT\\\": 1,\\n\" +\n" +
            "                    \"               \\\"PC_URL\\\": \\\"www.baidu.com\\\",\\n\" +\n" +
            "                    \"               \\\"SBJSSJ\\\": \\\"2020-06-22\\\",\\n\" +\n" +
            "                    \"               \\\"SBKSSJ\\\": \\\"2020-05-22\\\",\\n\" +\n" +
            "                    \"               \\\"SBMC\\\": \\\"测试\\\",\\n\" +\n" +
            "                    \"               \\\"ZCYD\\\": \\\"测试\\\",\\n\" +\n" +
            "                    \"               \\\"SBXMLXMC\\\": \\\"申报类型名称\\\",\\n\" +\n" +
            "                    \"               \\\"SBZCLXMC\\\": \\\"申报类型\\\",\\n\" +\n" +
            "                    \"               \\\"XMBH\\\": \\\"123456\\\",\\n\" +\n" +
            "                    \"               \\\"YXTZC_ID\\\": \\\"123\\\",\\n\" +\n" +
            "                    \"               \\\"ZGBM\\\": \\\"主管部门\\\"\\n\" +\n" +
            "                    \"           }\\n\" +\n" +
            "                    \"       ]\\n\" +\n" +
            "                    \"   }\\n\" +\n" +
            "                    \"}";
    private static final String BODY = "{\n" +
            "\t\"body\": {\n" +
            "\t\t\"projectInfo\": {\n" +
            "\t\t\t\"qualification\": \"T1\",\n" +
            "\t\t\t\"cliamType\": \"01\",\n" +
            "\t\t\t\"counterGuaranteeType\": \"02\",\n" +
            "\t\t\t\"engineeringCost\": \"89000\",\n" +
            "\t\t\t\"engineeringContractNum\": \"202201190853\",\n" +
            "\t\t\t\"engineeringAddress\": \"广东省广州市海珠区\",\n" +
            "\t\t\t\"planProjectStartDate\": \"2022-01-23 08:53:18\",\n" +
            "\t\t\t\"projectType\": \"02\",\n" +
            "\t\t\t\"contractingType\": \"02\",\n" +
            "\t\t\t\"projectTime\": \"365\",\n" +
            "\t\t\t\"planProjectEndDate\": \"2023-01-22 08:53:18\",\n" +
            "\t\t\t\"tenderProjectName\": \"平安履约测试\"\n" +
            "\t\t},\n" +
            "\t\t\"applicantInfo\": {\n" +
            "\t\t\t\"identifyNumber\": \"91370125MA3N0HGNXM\",\n" +
            "\t\t\t\"contactName\": \"王星\",\n" +
            "\t\t\t\"applicantName\": \"彭若男\",\n" +
            "\t\t\t\"insuredAddress\": \"广东省广州市海珠区2\",\n" +
            "\t\t\t\"managerId\": \"150100198210313473\",\n" +
            "\t\t\t\"contactPhone\": \"17688889999\",\n" +
            "\t\t\t\"managerName\": \"李星\"\n" +
            "\t\t},\n" +
            "\t\t\"policyInfo\": {\n" +
            "\t\t\t\"areaCode\": \"440100000000\",\n" +
            "\t\t\t\"orderNo\": \"1483605160327913474hd\",\n" +
            "\t\t\t\"sumInsured\": \"1.0\",\n" +
            "\t\t\t\"endDate\": \"2023-01-19 23:59:59\",\n" +
            "\t\t\t\"rate\": \"0.00542\",\n" +
            "\t\t\t\"specialClause\": \"暂无\",\n" +
            "\t\t\t\"insuranceDays\": \"365\",\n" +
            "\t\t\t\"riskCode\": \"LY\",\n" +
            "\t\t\t\"startDate\": \"2022-01-20 00:00:00\",\n" +
            "\t\t\t\"sumPremium\": \"0.01\"\n" +
            "\t\t},\n" +
            "\t\t\"fileInfoList\": {\n" +
            "\t\t\t\"file\": [{\n" +
            "\t\t\t\t\t\"key\": \"P_GBLV_GP-20220119091454-60d4f72ad3d0462082b91c6a579b4908\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"key\": \"P_GBLV_GP-20220119091454-60d4f72ad3d0462082b91c6a579b4908\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"key\": \"P_GBLV_GP-20220119091454-60d4f72ad3d0462082b91c6a579b4908\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"key\": \"P_GBLV_GP-20220119091454-60d4f72ad3d0462082b91c6a579b4908\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t]\n" +
            "\t\t},\n" +
            "\t\t\"insuredInfo\": {\n" +
            "\t\t\t\"identifyNumber\": \"GB864071GJKQEGEBQU\",\n" +
            "\t\t\t\"contactName\": \"方小林\",\n" +
            "\t\t\t\"identifyAdd\": \"广东省广州市海珠区3\",\n" +
            "\t\t\t\"managerId\": \"150100200103315799\",\n" +
            "\t\t\t\"contactPhone\": \"18255556666\",\n" +
            "\t\t\t\"insuredName\": \"徐其乐\"\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}";

    public static void main(String[] args) throws Exception {
        // 生成公私钥接口: http://t.cngongbao.com:11000/test/gbw/insurance-docking/insurance/rsa/create
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC13RCZ6qEWm6HfzlQPBhcHpjmb7vkq8ZjDafKLeDWVQ6PpnvhQyIvhjonTrLIzp0aqeVFs//cRGerJ/aH5knue1M5eMh7KQ5o82A6/5DsVZuEA6m6TiOb0oGB8YSkrUt3UfdgmgFt89xU6/Ho6vdax0J0nfVJ+68EqVFiGGTq6LwIDAQAB";
        String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALXdEJnqoRabod/OVA8GFwemOZvu+SrxmMNp8ot4NZVDo+me+FDIi+GOidOssjOnRqp5UWz/9xEZ6sn9ofmSe57Uzl4yHspDmjzYDr/kOxVm4QDqbpOI5vSgYHxhKStS3dR92CaAW3z3FTr8ejq91rHQnSd9Un7rwSpUWIYZOrovAgMBAAECgYEAsOHoiat5LkIE+Wb2vv4PXDNCiPWvhyMgj7/2Vcx/YUQ+orUpk3jbBHDcgPLRCAhEZP5GQhvbM9jfUcLMza65aCLgm00BqkkOFnqOtbuv0DYW4Qo06XrBS944/5t9g/iohAzs20AYhP258PvbgKAGMTmQiR+QgH6WhEoUYnu+kCECQQD+gSjID0Yy0EQPNL1eBQdQfVcKl8K1vPYzNhZeHYXeGFV+Wmb6h0CkCyNT+aiPpDeqY8Gqu5t8J8Dkv8mmBl5XAkEAtu6iesGMZGusbRnZnR8PCSGqPU/ntd/3BcLk11pVa7mdBGtgm7v5Nn0UxHHm5l5L+uwwD73OGPlY2s1WgrPr6QJBAIeQiwWNxUm50Bb9c8VKDmxcZ2nYDro5rjb3H5EATtdPxPJp+yKFuvPmjunjOVtSEOis/HMSPa8zibcPRb0u9T8CQEoOM0KkxhQFLul/9IPSBgE5WrS1e2U6hMMVUhUIYdpZawJWAecp4sq3OkgIoGQUOogbQ52rxhqAHwN86w/AjLkCQQDkDQxYrRIDq/KU100HsQSgnXgARzroaMvqjSMrF/TunpIgQcqRZkEbUpyKhchwsJn9DIYr/B8KgKpfOJx9YDU+";

        String sign = sign(BODY, privateKey);
        System.out.println("sign = " + sign);
        boolean result = checkSign(BODY, sign, publicKey);
        System.out.println("result = " + result);

    }


    /**
     * 使用私钥生成签名字符串
     *
     * @param signContent 待签名参数集合
     * @param privateKey  私钥原始字符串
     * @return 签名结果字符串
     * @throws Exception
     */
    public static String sign(String signContent, String privateKey) throws Exception {
        //参数值
        StringBuffer param = new StringBuffer();
        //循环拼接参数
        // mapToString(JsonUtil.bean(json, TreeMap.class), param);
        mapToString(JSONObject.parseObject(signContent, TreeMap.class), param);

        Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
        signature.initSign(getPrivateKeyPKCS8(privateKey));
        signature.update(param.toString().getBytes(CHARSET));
        byte[] signed = signature.sign();

        return new String(Base64.getEncoder().encode(signed));
    }


    /**
     * 使用公钥校验签名
     *
     * @param json      入参数据
     * @param signData  签名
     * @param publicKey 公钥原始字符串
     * @return true 验签通过 | false 验签不通过
     * @throws Exception
     */
    public static boolean checkSign(String json, String signData, String publicKey) throws Exception {

        //参数值
        StringBuffer param = new StringBuffer();
        //循环拼接参数
        mapToString(bean(json, TreeMap.class), param);
        // verify
        Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
        signature.initVerify(getPublicKeyX509(publicKey));
        signature.update(param.toString().getBytes(CHARSET));

        return signature.verify(Base64.getDecoder().decode(signData.getBytes(CHARSET)));
    }


    /**
     * 将公钥字符串进行Base64 decode之后，生成X509标准公钥
     *
     * @param publicKey 公钥原始字符串
     * @return X509标准公钥
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    private static PublicKey getPublicKeyX509(String publicKey) throws InvalidKeySpecException,
            NoSuchAlgorithmException, UnsupportedEncodingException {
        if (StringUtils.isEmpty(publicKey)) {
            return null;
        }
        KeyFactory keyFactory = KeyFactory.getInstance(SIGN_TYPE_RSA);
        byte[] decodedKey = Base64.getDecoder().decode(publicKey.getBytes(CHARSET));
        return keyFactory.generatePublic(new X509EncodedKeySpec(decodedKey));
    }

    /**
     * 将私钥字符串进行Base64 decode之后，生成PKCS #8标准的私钥
     *
     * @param privateKey 私钥原始字符串
     * @return PKCS #8标准的私钥
     * @throws Exception
     */
    private static PrivateKey getPrivateKeyPKCS8(String privateKey) throws Exception {
        if (StringUtils.isEmpty(privateKey)) {
            return null;
        }
        KeyFactory keyFactory = KeyFactory.getInstance(SIGN_TYPE_RSA);
        byte[] decodedKey = Base64.getDecoder().decode(privateKey.getBytes(CHARSET));
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedKey));
    }


    /**
     * map转字符串
     *
     * @param map   集合
     * @param param 追加参数
     * @return
     */
    public static void mapToString(TreeMap<String, Object> map, StringBuffer param) {
        //循环集合
        for (String key : map.keySet()) {
            //值
            Object obj = map.get(key);
            //判断不同类型，执行不同参数转换
            if (obj instanceof List) {
                // 转list
                List<TreeMap<String, Object>> list = Convert.convert(List.class, obj);
                // 递归遍历
                for (Object m : list) {
                    mapToString(Convert.convert(TreeMap.class, m), param);
                }
            } else if (obj instanceof Map) {
                // 递归遍历
                mapToString(Convert.convert(TreeMap.class, obj), param);
            } else {
                //判断是否为空
                if (StringUtils.isNotBlank(Convert.toStr(obj))) {
                    //附值
                    param.append(Convert.toStr(obj));
                }
            }
        }
    }
    /**
     * map转字符串
     *
     * @param map   集合
     * @param param 追加参数
     * @return
     */
    public static void mapToString2(TreeMap<String, Object> map, StringBuffer param) {
        //循环集合
        for (String key : map.keySet()) {
            //值
            Object obj = map.get(key);
            //判断不同类型，执行不同参数转换
            if (obj instanceof List) {
                // 转list
                List<TreeMap<String, Object>> list = JsonUtils.bean(JSONObject.toJSONString(obj), List.class);
                // 递归遍历
                for (Object m : list) {
                    mapToString(JsonUtils.bean(JSONObject.toJSONString(m), TreeMap.class), param);
                }
            } else if (obj instanceof Map) {
                // 递归遍历
                mapToString(JsonUtils.bean(JSONObject.toJSONString(obj), TreeMap.class), param);
            } else {
                //判断是否为空
                if (StringUtils.isNotBlank(Convert.toStr(obj))) {
                    //附值
                    param.append(Convert.toStr(obj));
                }
            }
        }
    }
    public static <T> T bean(String value, Class<T> basicClass) {
        if (StringUtils.isEmpty(value)) {
            return null;
        } else {
            try {
                return MAPPER.readValue(value, basicClass);
            } catch (Exception var3) {
                throw new IllegalStateException(var3);
            }
        }
    }
}

