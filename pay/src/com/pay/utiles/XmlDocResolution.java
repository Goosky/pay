/**
 * 
 */
package com.pay.utiles;

import java.io.*;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

/**
 * @author Podevor
 *
 */
public class XmlDocResolution {
	/*
	 * get a element value by key in xmlDoc
	 * */
	public static String getValueInXml(String xmlDoc,String key){
		String value = null;
		StringReader reader = new StringReader(xmlDoc);
		InputSource source = new InputSource(reader);
		SAXBuilder sb = new SAXBuilder();
		try {
			Document doc = sb.build(source);
			Element root = doc.getRootElement();
			List<Element> nodes = root.getChildren();
			Element childNode = null;
			for (int nodeIndex = 0;
					nodeIndex < nodes.size();
					nodeIndex++){
				childNode = (Element) nodes.get(nodeIndex);
				if (key.equals(childNode.getName())) {
					value = childNode.getValue();
				}
			}
		} catch (JDOMException e) {
			value = null;
			e.printStackTrace();
			Logger.getLogger(XmlDocResolution.class).info("xml文件解析出错"+e.getLocalizedMessage());
		}
		catch(IOException e){
			e.printStackTrace();
			Logger.getLogger(XmlDocResolution.class).info("xml文件解析出错"+e.getLocalizedMessage());
		}
		return value;
	}
	
	public static String getRootAttributeInXml(String xmlDoc,String attributeName){
		String attributeValue = null;
		StringReader reader = new StringReader(xmlDoc);
		InputSource source = new InputSource(reader);
		SAXBuilder sb = new SAXBuilder();
		try {
			Document doc = sb.build(source);
			Element root = doc.getRootElement();
			attributeValue = root.getAttributeValue(attributeName);
		} catch (JDOMException e) {
			attributeValue = null;
			Logger.getLogger(XmlDocResolution.class).info("xml文件解析出错");
		}
		catch(IOException e){
			Logger.getLogger(XmlDocResolution.class).info("xml文件解析出错");
		}
		return attributeValue;
	}
	
	public static String getChildNodeAttributeInXml(String xmlDoc,String attributeName){
		String attributeValue = null;
		StringReader reader = new StringReader(xmlDoc);
		InputSource source = new InputSource(reader);
		SAXBuilder sb = new SAXBuilder();
		try {
			Document doc = sb.build(source);
			Element root = doc.getRootElement();
			List<Element> nodes = root.getChildren();
			Element childNode = null;
			for (int nodeIndex = 0;
					nodeIndex < nodes.size();
					nodeIndex++){
				childNode = (Element) nodes.get(nodeIndex);
				List<Attribute> nodeAttributes = childNode.getAttributes();
				for (int attriIndex = 0;
						attriIndex < nodeAttributes.size();
						attriIndex++){
					if(nodeAttributes.get(attriIndex).equals(attributeName)){
						childNode.getAttribute(attributeName);
					}
				}
			}
		} catch (JDOMException e) {
			attributeValue = null;
			Logger.getLogger(XmlDocResolution.class).info("xml文件解析出错");
		}
		catch(IOException e){
			Logger.getLogger(XmlDocResolution.class).info("xml文件解析出错");
		}
		return attributeValue;
	}
}
