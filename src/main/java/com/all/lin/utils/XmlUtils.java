package com.all.lin.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * xml工具类
 *
 * @author AnthonyPark
 * @date 2018/5/25
 */
public class XmlUtils {


    public static String readElement(Element e, Map<String, String> vars, Document document) {
        //判断是否有复合内容
        if (e.hasMixedContent()) {
            Iterator it = e.elementIterator();
            while (it.hasNext()) {
                Element arrrName = (Element) it.next();
                //递归
                readElement(arrrName, vars, document);
            }
        } else {
            if (StringUtils.hasText(e.getTextTrim())) {
                String key = e.getText();
                key = key.replaceAll("#", "");

                String value = vars.get(key);
                if (value != null) {
                    e.setText(value);
                } else {
                    if (StringUtils.hasText(key) && !(key.startsWith("#") && key.endsWith("#"))) {
                        e.setText(key);
                    } else {
                        e.setText("");
                    }
                }
            }
        }
        return document.asXML();
    }

    public static String findNode(String xml, String nodeName) throws DocumentException {
        Document document = DocumentHelper.parseText(xml);
        Element element = document.getRootElement();
        Element ec = (Element) element.selectSingleNode("//" + nodeName);
        return ec == null ? null : ec.getText();
    }


    public static void main(String[] agrs) throws DocumentException {


        /// ----------------------
//        String xml = "<root><a name = \"第一个元素\"><b>最底层节点值</b></a></root>";
//        org.w3c.dom.Document document = XmlUtil.parseXml(xml);
//        org.w3c.dom.Element goalElement = XmlUtil.getElementByXPath("//root/a", document);
//        Object bString = XmlUtil.getByXPath("//root/a/b", document, XPathConstants.STRING);
//        System.out.println("b元素节点值：" + bString);
//        String name = goalElement.getAttribute("name");
//        System.out.println("a元素属性值：" + name);
//        /// ----------------------

        /*String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                "<RETURN>\n" +
                "<HEAD>\n" +
                "\t<TRANSRNO>10004</TRANSRNO>\n" +
                "\t<PARTNERCODE>GBW</PARTNERCODE>\n" +
                "\t<PARTNERSUBCODE>GBW</PARTNERSUBCODE>\n" +
                "\t</HEAD>\n" +
                "\t<MAIN>\n" +
                "    <TRANSRNO>10004</TRANSRNO>\n" +
                "    <SERIALDECIMAL>9DA81468D8E24193A9D1D38039C8CD0A</SERIALDECIMAL>\n" +
                "    <TRANSRDATE>2018-11-23 11:36:52</TRANSRDATE>\n" +
                "    <RESULTCODE>0002</RESULTCODE>\n" +
                "    <ERR_INFO/>\n" +
                "  </MAIN>\n" +
                "\t<BODY>\n" +
                "    <BODY_LIST>\n" +
                "      <CUS_RESULT>0003</CUS_RESULT>\n" +
                "      <CUS_MESSAGE>该客户信息已存在</CUS_MESSAGE>\n" +
                "      <APP_CUS_CODE>102835</APP_CUS_CODE>\n" +
                "      <C_CUST_NO>BL201811231114014930003</C_CUST_NO>\n" +
                "      <C_CUST_AMOUNT>0.0</C_CUST_AMOUNT>\n" +
                "    </BODY_LIST>\n" +
                "  </BODY>\n" +
                "</RETURN>";*/

        String xml = "<xml> <ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>12345678</CreateTime><MsgType><![CDATA[text]]></MsgType> <Content><![CDATA[你好]]></Content></xml>";
        System.out.println(XmlUtils.findNode(xml, "ToUserName1111"));
//        Document document = DocumentHelper.parseText(xml);
//        Element element = document.getRootElement();
//        //如果投保时险种为履约险，需删除多余节点
//        String xpath ="/PACKET/BODY/PrpTitemKindList/PrpTitemKindDto[@proType='LY']";
//        List<Node> nodes = document.selectNodes(xpath);
//        for(Node node : nodes){
//            node.getParent().remove(node);
//        }
//        System.out.println(document.asXML());
    }
}
