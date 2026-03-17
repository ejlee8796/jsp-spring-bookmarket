<%@page import="org.w3c.dom.*"%>
<%@page import="javax.xml.parsers.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 	DocumentBuilderFactory docBuilderFactory1 = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder1 = null;
	Document doc1 = null;
	try {
		String url ="http://www.aladin.co.kr/rss/MonthlyBest/book"; //알라딘
		docBuilder1 = docBuilderFactory1.newDocumentBuilder();
		doc1 = docBuilder1.parse(url);
		doc1.getDocumentElement().normalize();
		Element root = doc1.getDocumentElement();
		NodeList dataList = doc1.getElementsByTagName("item");
		String Value = null;
		
		for(int i=0; i<dataList.getLength(); i++) {
			Node data = dataList.item(i);
			if(data.getNodeType() == Node.ELEMENT_NODE) {
				NodeList nodelist = data.getChildNodes();
				for(int j=0; j<nodelist.getLength(); j++) {
					Node child = nodelist.item(j);
					String nodeName = child.getNodeName();
					Value = child.getTextContent();
				}
			}// end if 
		}// end for
		String [] indexranktitle = Value.split("정가:"); 
		String [] indexrankline = Value.split("<h2>"); boolean rankview = true;

		out.print("<p class='text-right'>");
		out.print(indexrankline[0].split("<table cellspacing='1' cellpadding='5' border='0' bgcolor='#dddddd' style='margin-right:10px;'><tbody><tr><td bgcolor='#ffffff'>")[0].split("<br/><br/>")[0]);
		out.print("</p>");
		out.print("<div class='row'>");
		for(int i=0; i<11; i++) {
			if(i==0) {
				out.print("<div class='col-sm-3 text-center'><p>"
						+indexrankline[i].split("<table cellspacing='1' cellpadding='5' border='0' bgcolor='#dddddd' style='margin-right:10px;'><tbody><tr><td bgcolor='#ffffff'>")[0].split("<br/><br/>")[1]
						+"</p>"
						+"<a href='search_index/bookDetail.jsp?isbn="+indexranktitle[i].split("<tbody><tr><td bgcolor='#ffffff'>")[1].split("<h2>")[1].split("</h2>")[1].split("/")[3].split(">")[1].split("<")[0]+"'>"
						+"<img "+indexranktitle[i].split("<tbody><tr><td bgcolor='#ffffff'>")[1].split("<h2>")[0].split("<img")[1].split("</td></tr></tbody></table>")[0]
						+"<h3><strong>"
						+"<a href='search_index/bookDetail.jsp?isbn="+indexranktitle[i].split("<tbody><tr><td bgcolor='#ffffff'>")[1].split("<h2>")[1].split("</h2>")[1].split("/")[3].split(">")[1].split("<")[0]+"'>"		
						+indexranktitle[i].split("<tbody><tr><td bgcolor='#ffffff'>")[1].split("<h2>")[1].split("</h2>")[0].split(">")[1].split("</a")[0]
						+"</a></strong></h3>"
						+"<p>"+indexranktitle[i].split("<tbody><tr><td bgcolor='#ffffff'>")[1].split("<h2>")[1].split("</h2>")[1].split("/")[0]+"</p>"
						+"</div>"); 
			} else if(i!=0) {
				if(rankview) { out.print("<div class='col-sm-9'>"); rankview = false; }
				if(i==6 ) { out.print("<div class='rank' id='ch3'>"); }
				if(i==1) { out.print("<div class='rank' id='ch2'>"); }
				out.print("<div class='col-sm-2  text-center'>");
				out.print(indexrankline[i].split("</table><hr/>")[1].split("<table cellspacing='1' cellpadding='5' border='0' bgcolor='#dddddd' style='margin-right:10px;'><tbody><tr><td bgcolor='#ffffff'>")[0].split("<br/><br/><table border='0' cellpadding='0' cellspacing='0'>")[0]
						+"<a href='search_index/bookDetail.jsp?isbn="+indexranktitle[i].split("<tbody><tr><td bgcolor='#ffffff'>")[1].split("</h2>")[1].split("/")[3].split(">")[1].split("<")[0]+"' >"
						+"<img "+indexranktitle[i].split("<tbody><tr><td bgcolor='#ffffff'>")[1].split("<h2>")[0].split("<img")[1].split("</td></tr></tbody></table>")[0]
						+"<h5><strong>"
						+"<a href='search_index/bookDetail.jsp?isbn="+indexranktitle[i].split("<tbody><tr><td bgcolor='#ffffff'>")[1].split("</h2>")[1].split("/")[3].split(">")[1].split("<")[0]+"' >"
						+indexranktitle[i].split("<tbody><tr><td bgcolor='#ffffff'>")[1].split("<h2>")[1].split("</h2>")[0].split(">")[1].split("</a")[0]+"</a></strong></h5>"
						+indexranktitle[i].split("<tbody><tr><td bgcolor='#ffffff'>")[1].split("</h2>")[1].split("/")[0]);				
				out.print("</div>");
				if (i==5 ||i==10) { out.print("</div>"); }
			}
		}
		out.print("</div></div>");
	} catch (Exception e) { e.printStackTrace(); }
%>