package com.wthfeng.learn.html;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

/**
 * @author wangtonghe
 * @date 2017/10/20 11:08
 */
public class XpathTest {

    @Test
    public void test() throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("books.xml");
        String expression = "//book[author='Neal Stephenson']/title/text()";
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList list = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < list.getLength(); i++) {
            System.out.println(list.item(i).getNodeValue());
        }


    }
}
