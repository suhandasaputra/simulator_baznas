/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asac.function;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.InputSource;

/**
 *
 * @author adi
 */
public class XMLFunction {

    private static Logger log = Logger.getLogger(XMLFunction.class);

    public static HashMap convertXMLtoHashmap(String xmltext) {
        HashMap result = new HashMap();
        try {
            InputSource is = new InputSource(new StringReader(xmltext));
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(is);
            Element rootElement = document.getRootElement();
            List<Element> content = rootElement.getChildren();
            System.out.println("contect size : " + content.size());
            for (int temp = 0; temp < content.size(); temp++) {
                Element data = content.get(temp);
                System.out.println(data.getName() + " - " + data.getValue());
                result.put(data.getName(), data.getValue());
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    public static String convertHashmapToXML(HashMap map, String rootelement) {
        Element root = new Element(rootelement);
        Document doc = new Document(root);
        Iterator iter = map.keySet().iterator();
        String item;
        while (iter.hasNext()) {
            item = iter.next().toString();
            Element node = new Element(item);
            node.setText(map.get(item).toString());
            doc.getRootElement().addContent(node);

        }
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getCompactFormat());
        return xmlOutput.outputString(doc);
    }

    public static String setRequestInquiryXMLWay4Msg(String noref, String datetime, String cardNumber, String terminalId) {
        Element root = new Element("UFXMsg");
        root.setAttribute("direction", "Rq");
        root.setAttribute("msg_type", "Doc");
        root.setAttribute("scheme", "WAY4Doc");
        root.setAttribute("version", "2.0");
        Document doc = new Document(root);
        Element msgId = new Element("MsgId");
        msgId.setText(noref);
        doc.getRootElement().addContent(msgId);
        Element source = new Element("Source");
        source.setAttribute("app", "Web01");
        doc.getRootElement().addContent(source);

        Element msgData = new Element("MsgData");
        Element docData = new Element("Doc");
        Element transType = new Element("TransType");
        Element transCode = new Element("TransCode");
        Element msgCode = new Element("MsgCode");
        msgCode.setText("01000B");
        transCode.addContent(msgCode);
        transType.addContent(transCode);
        docData.addContent(transType);
        Element localDt = new Element("LocalDt");
        localDt.setText(datetime);
        docData.addContent(localDt);
        Element requestor = new Element("Requestor");
        Element contractNumberRequestor = new Element("ContractNumber");
        contractNumberRequestor.setText(cardNumber);
        requestor.addContent(contractNumberRequestor);
        docData.addContent(requestor);
        Element sourceData = new Element("Source");
        Element contractNumberSource = new Element("ContractNumber");
        contractNumberSource.setText(terminalId);
        sourceData.addContent(contractNumberSource);
        docData.addContent(sourceData);
        Element resultDtls = new Element("ResultDtls");
        Element parm = new Element("Parm");
        Element parmCode = new Element("ParmCode");
        Element value = new Element("Value");

        parmCode.setText("Balance");
        value.setText("Y");
        parm.addContent(parmCode);
        parm.addContent(value);
        resultDtls.addContent(parm);
        docData.addContent(resultDtls);

        msgData.addContent(docData);
        doc.getRootElement().addContent(msgData);
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        return xmlOutput.outputString(doc);
    }

//    public static void main(String[] args) throws JDOMException, IOException {
////        HashMap a = new HashMap();
////        a.put("nama", "herry");
////        a.put("alamat","kebagusan");
////        System.out.println(XMLFunction.convertHashmapToXML(a, "verdana").substring(40));
//
////        System.out.println(XMLFunction.setRequestInquiryXMLWay4Msg("noref12345", "2016-02-16 19:18:26", "1876840179721327", "ATM00077").substring(40));
////        System.out.println(convertXMLtoHashmap("<input><admin>3500</admin><biaya_registrasi>2500</biaya_registrasi><cmd>npp_inq_bpjs.900000400365</cmd><data1>900000400365</data1><data2>BARJAHUDIN</data2><data3></data3><data4>JHT+JKK+JKM</data4><ftrxid>NKP2016-06-10 04:16:13.0924</ftrxid><isnew>0</isnew><JHT>32000</JHT><JKK>16000</JKK><JKM>6800</JKM><rate_JHT>2.00</rate_JHT><rate_JKK>1.00</rate_JKK><rate_JKM>.30</rate_JKM><status>0</status><status_bayar>T</status_bayar><tagihan>54800</tagihan><trxid>MATAJARI2016061016150000000100000000000105</trxid></input>")); 
//        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><verdana><adminFee>3500</adminFee><biayaRegistrasi>2500</biayaRegistrasi><harga>54800</harga><input><admin>3500</admin><biaya_registrasi>2500</biaya_registrasi><cmd>npp_inq_bpjs.900000400365</cmd><data1>900000400365</data1><data2>BARJAHUDIN</data2><data3></data3><data4>JHT+JKK+JKM</data4><ftrxid>NKP2016-06-10 04:16:13.0924</ftrxid><isnew>0</isnew><JHT>32000</JHT><JKK>16000</JKK><JKM>6800</JKM><rate_JHT>2.00</rate_JHT><rate_JKK>1.00</rate_JKK><rate_JKM>.30</rate_JKM><status>0</status><status_bayar>T</status_bayar><tagihan>54800</tagihan><trxid>MATAJARI2016061016150000000100000000000105</trxid></input></verdana>";
//        InputSource is = new InputSource(new StringReader(xmlString));
//        SAXBuilder saxBuilder = new SAXBuilder();
//        Document document = saxBuilder.build(is);
//        Element rootElement = document.getRootElement();
//
//        Element inputobj = rootElement.getChild("input");
//       
//        System.out.println(inputobj);
//    }

}
