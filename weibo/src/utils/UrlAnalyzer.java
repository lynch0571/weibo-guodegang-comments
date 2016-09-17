package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class UrlAnalyzer {

	private static String baseUrl="http://qqqq14.com/";
	public static String[] getAllImageUrl(String urlStr) {
		String content = HtmlParser.getURLSource(urlStr);
		Document doc = Jsoup.parse(content);

		Elements elements = doc.select("img[src~=(?i).(png|jpe?g)]");

		String[] strs = elements.outerHtml().split("<img src=\"");
		for (int i = 0; i < strs.length; i++) {
			strs[i] = strs[i].replaceAll("\" border=\"0\">", "");
		}
		return strs;
	}

	public static String getNextUrl(String urlStr) {
		String content = HtmlParser.getURLSource(urlStr);
		Document doc = Jsoup.parse(content);

		String elements = doc.select(".next a").attr("href");

		String str = baseUrl+elements;
		System.out.println(str);
		return str;
	}

}
