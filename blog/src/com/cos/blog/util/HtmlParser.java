package com.cos.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.nhncorp.lucy.security.xss.XssPreventer;

public class HtmlParser {
	public static String getContentPreview(String content) {

		if (content == null || content.equals("")) {
			
			return "내용 없음...";
			
		} else if (content.contains("<p>")) {

			Document doc = Jsoup.parse(content);
			
			Elements pTags = doc.select("p");

			String text = pTags.get(0).text();

			if (text.length() > 0) {
				text = XssPreventer.escape(text);
				if (text.length() < 11) {
					return text;
				} else {
					return text.substring(0, 10) + "...";
				}

			} else {
				return "";
			}
			
		} else if (content.length() > 10) {
			
			return content.substring(0, 10) + "...";
			
		}
		
		return XssPreventer.escape(content);
		
	}
	
	public static String youtubeParser(String content) {
		
		Document doc = Jsoup.parse(content);

		Elements els = doc.select("a");
		
		for (Element element : els) {
			
			System.out.println(element.toString());
			
//			if(!element.text().equals("")) {
				
				if(!element.attr("href").contains("youtube.com/channel") && element.attr("href").contains("youtube.com/")) {
					
					System.out.println(element.attr("href"));
					
					String[] parseContent = element.attr("href").split("v=");

					
					element.after("<br/><iframe width=\"400\" height=\"250\" src=\"https://www.youtube.com/embed/"+parseContent[1]+"\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
					
					
				} else if(element.attr("href").contains("youtu.be/")) {
					
					System.out.println(element.attr("href"));
					
					String[] parseContent = element.attr("href").split("be/");
					
					element.after("<br/><iframe width=\"400\" height=\"250\" src=\"https://www.youtube.com/embed/"+parseContent[1]+"\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
					
				}
				
//			}

		}
		
		return doc.toString();
	}
}



