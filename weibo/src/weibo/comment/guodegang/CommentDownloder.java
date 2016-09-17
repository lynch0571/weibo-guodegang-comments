package weibo.comment.guodegang;

import java.io.FileWriter;
import java.io.IOException;

import utils.HTMLSpider;
import utils.UnicodeUtils;

public class CommentDownloder {

	public static void main(String[] args) {
		String url = "http://m.weibo.cn/single/rcList?format=cards&id=4016334326674111&type=comment&hot=0&page=";
		int num=0;
		int tmp=0;
		String filePath="/home/lin/data";
		for(int i=1;i<30000;i++){
			long time=(long)(Math.floor(Math.random()*1000));
			String content="";
			try {
				content = UnicodeUtils.unicode2Chinese(HTMLSpider.getHTMLDocFromWeb(url+i).select("body").text());
				tmp=i;	//记录被封的序号
			} catch (Exception e1) {
				num++;
				i=tmp;
				try {
					System.out.println("第"+num+"次被封了，睡一会，接着爬～～");
					Thread.sleep(1000*60L);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("睡觉被打扰");
				}
			}
			System.out.println("第"+i*10+"条评论"+"["+time+"ms]:"+content);
			appendFile(filePath+"/guodegang_"+i+".json",content);
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Thread.sleep异常");
			}
		}
	}

	public static void appendFile(String fileName, String content) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
