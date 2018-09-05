package batch_address;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlPaser {
	public static void main(String[] args) {
		String path = "/config/test.xml";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();//Factory가 들어가는 클래스는 대부분 생성자로 만들지 못하게 막혀있음
		//newInstance()로 초기화해서 사용해야햅
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = XmlPaser.class.getResourceAsStream(path);
			Document doc = db.parse(is);
			NodeList foodList = doc.getElementsByTagName("foods");
			for(int i=0,max=foodList.getLength();i<max;i++) {
				Node food = foodList.item(i);
				System.out.println(food.getTextContent());
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
