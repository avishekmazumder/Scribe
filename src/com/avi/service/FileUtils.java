package com.avi.service;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class FileUtils {
	
	public static void generateDealXml(String dealXml) {
		
		
		FileWriter fw = null;
		BufferedWriter writer = null;
		try {
			dealXml = FileUtils.format(dealXml, false);
			fw = new FileWriter("E:\\UBS\\Dev\\206003000.xml");
			writer = new BufferedWriter(fw);			
			writer.write(dealXml);
		} catch (IOException e) {
			
		} catch (Exception e) {
			
		} finally {
		
			if (writer!=null) {
				try {
					writer.close();
				} catch (IOException e) {

				}
			}
			
			if(fw!=null) {
				try {
					fw.close();
				} catch (IOException e) {

				}
			}
			
		}
	    
	}
	
	public static String format(String xml, Boolean ommitXmlDeclaration) throws IOException, SAXException, ParserConfigurationException {
		try {
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));

			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nodeList = (NodeList) xPath.evaluate("//text()[normalize-space()='']", document,
					XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); ++i) {
				Node node = nodeList.item(i);
				node.getParentNode().removeChild(node);
			}

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			StringWriter stringWriter = new StringWriter();
			StreamResult streamResult = new StreamResult(stringWriter);

			transformer.transform(new DOMSource(document), streamResult);
			
			//Source src = new SAXSource(new InputSource(xml));
			//transformer.transform(src, streamResult);

			return stringWriter.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}

}
