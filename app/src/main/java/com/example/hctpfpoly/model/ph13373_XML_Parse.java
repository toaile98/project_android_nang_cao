package com.example.hctpfpoly.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ph13373_XML_Parse {
    public Document getDocument(String xml) throws IOException, SAXException {
        Document document = null;
        //xu li...
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder =null;
        try {
            builder=factory.newDocumentBuilder();//tao moi 1 tai lieu
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        InputSource inputSource = new InputSource();//tao input
        inputSource.setCharacterStream(new StringReader(xml));//tao luong de dua du lieu vao Document
        inputSource.setEncoding("UTF-8");//dinh dang du lieu utf-8

        document=builder.parse(inputSource);//thuc thi
        //ket thuc
        return document;
    }
    //input;Element ,name
    //output: value (string)
    public String getValue(Element node, String name){
        String kq="";
        //xu li...
        NodeList nodeList =node.getElementsByTagName(name);//lay ve cac phan tu cung ten
        kq=getTextofNode(nodeList.item(0));//lay text cua phan tu dau tien
        //ket thuc
        return kq;
    }

    private String getTextofNode(Node n) {//lay ve text cua node
        Node child;
        if (n!=null){
            if (n.hasChildNodes()){
                for (child=n.getFirstChild();child!=null;child=child.getNextSibling()){
                    //kiem tra xem kie type co phai text k
                    if (child.getNodeType()==Node.TEXT_NODE){
                        return child.getNodeValue();//tra ve text cua node
                    }
                }
            }
        }
        return "";

    }
}
