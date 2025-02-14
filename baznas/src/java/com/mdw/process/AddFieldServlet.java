/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdw.process;

import com.asac.function.JsonProcess;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.mdw.db.DatabaseProcess;
import com.mdw.model.Model_Conn;
import com.mdw.model.Model_Iso;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.me.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author suhanda
 */
public class AddFieldServlet extends HttpServlet {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AddFieldServlet.class);
    DatabaseProcess dp = new DatabaseProcess();

    public AddFieldServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String data = "";
        String line = "";

        while ((line = in.readLine()) != null) {
            data += line;
        }
        HashMap gg = JsonProcess.decodeJson(data);

        String field = gg.get("field").toString();
        String name_of_field = gg.get("name_of_field").toString();
        String format = gg.get("format").toString();
//        String chars = gg.get("char").toString();
        String length = gg.get("length").toString();
        String bankcode = gg.get("bankcode").toString();

        String status = dp.addField(bankcode, field, name_of_field, format, length);

        HashMap res = new HashMap();
        res.put("status", status);
        if ("00".equals(status)) {
            ArrayList<Model_Iso> listIso = new ArrayList<>();
            try {
                listIso = dp.getAllIso(bankcode);
            } catch (ParseException ex) {
                Logger.getLogger(AddFieldServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(listIso);
            JsonArray jsonArray = element.getAsJsonArray();

            try {
                DocumentBuilderFactory dbFactory
                        = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.newDocument();

                // root element
                Element induk = doc.createElement("isopackager");
                doc.appendChild(induk);

                for (int i = 0; i < jsonArray.size(); i++) {
                    HashMap ggwp = JsonProcess.decodeJson(jsonArray.get(i).toString());
                    // isofield element
                    Element anak = doc.createElement("isofield");
                    induk.appendChild(anak);

                    // setting attribute to element
                    Attr attr1 = doc.createAttribute("id");
                    attr1.setValue(ggwp.get("field").toString());
                    anak.setAttributeNode(attr1);

                    Attr attr2 = doc.createAttribute("length");
                    attr2.setValue(ggwp.get("length").toString());
                    anak.setAttributeNode(attr2);

                    Attr attr3 = doc.createAttribute("name");
                    attr3.setValue(ggwp.get("name_of_field").toString());
                    anak.setAttributeNode(attr3);

                    Attr attr4 = doc.createAttribute("class");
                    attr4.setValue("org.jpos.iso."+ggwp.get("format").toString());
                    anak.setAttributeNode(attr4);
                }
                
                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "genericpackager.dtd");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("D:\\packager mios\\"+bankcode+".xml"));
                transformer.transform(source, result);

                // Output to console for testing
                StreamResult consoleResult = new StreamResult(System.out);
                transformer.transform(source, consoleResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.getWriter().print(JsonProcess.generateJson(res));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
        } else {
            response.getWriter().print(JsonProcess.generateJson(res));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
        }
    }
}
