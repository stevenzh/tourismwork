package com.opentravelsoft.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:40 $
 */
public class XMLUtility {

    private static XMLUtility INSTANCE = null;

    // TransformerFactory
    private TransformerFactory transformerFactory = null;

    // DocumentBuilderFactory
    private DocumentBuilderFactory documentBuilderFactory = null;

    // DocumentBuilder
    private DocumentBuilder documentBuilder = null;

    // Document
    private Document document = null;

    private XMLUtility() {
    
        transformerFactory = TransformerFactory.newInstance();
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
    }

    private XMLUtility(String filePath) throws ParserConfigurationException,
            IOException, SAXException, Exception {
        this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
        this.documentBuilder = documentBuilderFactory.newDocumentBuilder();
        this.document = documentBuilder.parse(filePath);
    }

    public static XMLUtility getInstance() {
    
        if (INSTANCE == null) {
            synchronized (XMLUtility.class) {
                if (INSTANCE == null) {
                    INSTANCE = new XMLUtility();
                }
            }
        }
        return INSTANCE;
    }

    public static XMLUtility getInstance(String filePath)
            throws ParserConfigurationException, IOException, SAXException,
            Exception {

        if (INSTANCE == null) {
            synchronized (XMLUtility.class) {
                if (INSTANCE == null) {
                    INSTANCE = new XMLUtility(filePath);
                }
            }
        }
        return INSTANCE;
    }

    public String[] getData(String tagName, String[] nextTagName) {
        String[] strRet = null;

        if (nextTagName != null) {
            strRet = new String[nextTagName.length];
        } else {
            strRet = new String[] { "" };
        }

        Element root = this.document.getDocumentElement();

        if (root != null) {
            NodeList nodes = root.getElementsByTagName(tagName);
            if (nodes.getLength() > 0) {
                Node node = nodes.item(0);
                // 複数Valueを取得
                for (int i = 0; i < nextTagName.length; i++) {
                    strRet[i] = getChildElementValue(node, nextTagName[i]);
                }
            }
        }
        return strRet;
    }

    private String getChildElementValue(Node node, String subTagName) {
        String returnString = "";
        if (node != null) {
            NodeList children = node.getChildNodes();
            for (int innerLoop = 0; innerLoop < children.getLength(); innerLoop++) {
                Node child = children.item(innerLoop);
                if (child == null || child.getNodeName() == null
                        || !child.getNodeName().equals(subTagName))
                    continue;
                Node grandChild = child.getFirstChild();
                if (grandChild != null && grandChild.getNodeValue() != null)
                    return grandChild.getNodeValue();
            }
        }
        return returnString;
    }

    public List<String> getData(String tagName, String subTagName) {
        List<String> list = new ArrayList<String>();
        Element root = this.document.getDocumentElement();

        if (root != null) {
            NodeList nodes = root.getElementsByTagName(tagName);
            if (nodes.getLength() > 0) {
                Node node = nodes.item(0);
                list = getChildElements(node, subTagName);
            }
        }
        return list;
    }

    public String[] getDatas(String tagName, String subTagName) {
        Element root = document.getDocumentElement();
        List<String> values = new ArrayList<String>();

        if (root != null) {
            NodeList nodes = root.getElementsByTagName(tagName);
            if (nodes.getLength() > 0) {
                Node node = nodes.item(0);
                if (node != null) {
                    Node children = node.getFirstChild();
                    do {
                        if (children == null || children.getNodeName() == null
                                || !children.getNodeName().equals(subTagName)) {
                            children = children.getNextSibling();
                            continue;
                        }

                        Node grandChild = children.getFirstChild();
                        if (grandChild.getNodeValue() != null) {
                            values.add(grandChild.getNodeValue());
                        }

                        children = children.getNextSibling();
                    } while (children != null);
                }
            }
        }
        String[] result = new String[values.size()];
        for (int i = 0; i < values.size(); i++) {
            result[i] = (String) values.get(i);
        }
        return result;
    }

    private List<String> getChildElements(Node node, String subTagName) {
        List<String> list = new ArrayList<String>();
        String strValue = "";
        String strName = "";
        if (node != null) {
            NodeList children = node.getChildNodes();

            for (int innerLoop = 0; innerLoop < children.getLength(); innerLoop++) {
                Node child = children.item(innerLoop);
                if (child == null || child.getNodeName() == null
                        || !child.getNodeName().equals(subTagName))
                    continue;
                Node grandChild = child.getFirstChild();
                if (grandChild != null && grandChild.getNodeValue() != null) {
                    strName = child.getAttributes().item(0).getNodeValue();
                    strValue = grandChild.getNodeValue();
                    list.add(strName + "," + strValue);
                }
            }
        }
        return list;
    }

    public Document setNodeValue(Document xml, String[] id, String[] value) {

        if (xml == null)
            return null;

        for (int i = 0; i < id.length; i++) {

            // Node を選択
            Node node = selectNodeById(xml, id[i]);

            if (node == null)
                continue;

            // 文字列をセット
            setNodeValue(node, value[i]);
        }

        return xml;
    }

    public void setNodeValue(Node node, String value) {

        if (node != null && value != null) {

            if (node.getFirstChild() == null) {
                Text textNode = node.getOwnerDocument().createTextNode(value);
                node.appendChild(textNode);
            } else {
                node.getFirstChild().setNodeValue(value);
            }
        }
    }

    public Node selectNodeById(Document document, String id) {

        // 検索対象の属性名
        final String[] ids = { "id", "ID", "Id", "iD" };

        Node retNode = null;

        // タグ名のリストを取得
        NodeList list = document.getElementsByTagName("*");
        if (list == null)
            return null;

        // タグを１件ずつチェック
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);

            // 属性値の取得
            String attribute = null;
            for (int j = 0; j < ids.length; j++) {
                attribute = element.getAttribute(ids[j]);
                if (attribute != null && !"".equals(attribute))
                    break;
            }

            // 指定した ID があった場合ノードを取得
            if (attribute != null && attribute.equals(id)) {
                retNode = (Node) element;
                break;
            }
        }

        return retNode;
    }

    public Document createDOMDocument() {

        DocumentBuilder documentBuilder = null;
        synchronized (documentBuilderFactory) {
            try {
                documentBuilder = documentBuilderFactory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                return null;
            }
        }

        return documentBuilder.newDocument();
    }

    public Document createDOMDocument(byte[] xml) {

        if (xml == null) {
            return null;
        }

        return createDOMDocument(new ByteArrayInputStream(xml));
    }

    public Document createDOMDocument(InputStream in) {

        if (in == null) {
            return null;
        }

        InputSource source = new InputSource(in);
        DocumentBuilder documentBuilder = null;
        synchronized (documentBuilderFactory) {
            try {
                documentBuilder = documentBuilderFactory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                return null;
            }
        }
        Document document = null;
        try {
            document = documentBuilder.parse(source);
        } catch (SAXException e) {
            return null;
        } catch (IOException e) {
            return null;
        }

        return document;
    }

    public DocumentFragment transformNode(Node xml, Node xsl, Hashtable table) {

        if (xml == null || xsl == null) {
            return null;
        }

        StringReader reader = new StringReader(toString(xsl));
        DocumentFragment documentFragment = createDOMDocument()
                .createDocumentFragment();

        Transformer transformer = null;
        synchronized (transformerFactory) {
            try {
                transformer = transformerFactory
                        .newTransformer(new StreamSource(reader));
            } catch (TransformerConfigurationException e) {
                return null;
            }
        }

        if (table != null) {
            Enumeration keys = table.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                transformer.setParameter(key, table.get(key));
            }
        }

        try {
            transformer.transform(new DOMSource(xml), new DOMResult(
                    documentFragment));
        } catch (TransformerException e) {
            return null;
        }

        return documentFragment;
    }

    public DocumentFragment transformNode(Node xml, Node xsl) {

        if (xml == null || xsl == null) {
            return null;
        }

        return transformNode(xml, xsl, null);
    }

    public String transform2String(byte[] xml, byte[] xsl) {

        if (xml == null || xsl == null)
            return null;

        return transform2String(createDOMDocument(xml), createDOMDocument(xsl));
    }

    public String transform2String(Node xml, Node xsl) {

        if (xml == null || xsl == null)
            return null;

        return toString(transformNode(xml, xsl));
    }

    public String toString(NodeList nodeList) {

        if (nodeList == null) {
            return null;
        }

        Transformer transformer = null;
        try {
            synchronized (transformerFactory) {
                transformer = transformerFactory.newTransformer();
            }
        } catch (TransformerConfigurationException e) {
            return null;
        }

        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer = new StringWriter();
        try {
            for (int index = 0; index < nodeList.getLength(); index++) {
                transformer.transform(new DOMSource(nodeList.item(index)),
                        new StreamResult(writer));
            }
        } catch (TransformerException e) {
            return null;
        }

        return writer.toString();
    }

    public String toString(Node node) {

        return toString(node, null);
    }

    public String toString(Node node, String encoding) {

        if (node == null) {
            return null;
        }

        Transformer transformer = null;
        try {
            synchronized (transformerFactory) {
                transformer = transformerFactory.newTransformer();
            }
        } catch (TransformerConfigurationException e) {
            return null;
        }

        if (encoding == null) {
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
                    "yes");
        } else {
            transformer
                    .setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
        }

        StringWriter writer = new StringWriter();
        try {
            transformer
                    .transform(new DOMSource(node), new StreamResult(writer));
        } catch (TransformerException e) {
            return null;
        }

        return writer.toString();
    }

}
