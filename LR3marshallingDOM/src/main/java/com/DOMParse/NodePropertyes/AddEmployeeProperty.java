package com.DOMParse.NodePropertyes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AddEmployeeProperty {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {

		File file = new File("src/main/resources/employee.xml");
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(System.in));
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			System.out.println("Enter employee id : ");
			int id = Integer.parseInt(input.readLine());
			System.out.println("Enter property name : ");
			String property = input.readLine();
			System.out.println("Enter value : ");
			String value = input.readLine();
			boolean result = AddEmployeePropertyInXml(document, id, property, value);
			if (result) {
				TransformerFactory tFactory = TransformerFactory.newInstance();
				Transformer transformer = tFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(document);
				StreamResult sResult = new StreamResult(file);
				transformer.transform(source, sResult);
				System.out.println("Employee property has been added successfully.");
			} else {
				System.out.println("Employee not exists.");
			}
		} finally {
			if (input != null) {
				input.close();
			}
		}

	}

	private static boolean AddEmployeePropertyInXml(Document document,int id, String property, String value) {

		NodeList list = document.getElementsByTagName("Employee");
		boolean result = false;
		int length = list.getLength();
		for (int i = 0; i < length; i++) {
			Node node = list.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				if (element.getAttribute("id").equals(String.valueOf(id))) {
					Element newElement=document.createElement(property);
					newElement.setTextContent(value);
					element.appendChild(newElement);
					result=true;
					break;
				}

			}
		}

		return result;

	}

}
