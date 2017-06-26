package com.qianlong.qlttms.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

public class HtmlUtil {

	/**
	 * 传入一个image标签，解析以base64编码的图片格式，并将该图片保存到固定路径
	 */
	public static boolean generateImage(String imgTag, String filePath)
			throws IOException {
		// 创建文件夹路径
		String dir = filePath.substring(0, filePath.lastIndexOf("/"));
		File dirFile = new File(dir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();// 创建根目录
		}

		// 如果存在，删除
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		String src = null;
		String imageBs64Data = null;
		Matcher matcher_image = Pattern
				.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)",
						Pattern.CASE_INSENSITIVE)
				.matcher(imgTag);
		while (matcher_image.find()) {
			src = matcher_image.group(1);
			System.out.println(src);
			if (!StringUtils.isEmpty(src)) {
				if (src.indexOf("data:image/jpeg;base64,") != -1) {
					imageBs64Data = src
							.substring("data:image/jpeg;base64,".length());
					break;
				}
			}
			
		}
		if (!StringUtils.isEmpty(imageBs64Data)) {
			base64ToImage(imageBs64Data, file);
			return true;
		}
		return false;
	}
	
	/**
	 * 解析image中的title
	 */
	public static String parseImgExtend(String imgTag){
		// 创建文件夹路径
		Matcher matcher_title = Pattern
				.compile("title\\s*=\\s*\"?(.*?)\"",
						Pattern.CASE_INSENSITIVE)
				.matcher(imgTag);
		while (matcher_title.find()) {
			String titleHtml = matcher_title.group(1);
			int index = titleHtml.lastIndexOf(".");
			if(index != -1){
				return titleHtml.substring(titleHtml.lastIndexOf(".")).trim();
			}
		}
		return "";
	}

	/**
	 * 将base64字符串转化成图片
	 * 
	 * @throws IOException
	 */
	public static boolean base64ToImage(String imgStr, File file)
			throws IOException { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		Base64 base64 = new Base64();

		// Base64解码
		byte[] b = base64.decode(imgStr);
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {// 调整异常数据
				b[i] += 256;
			}
		}
		OutputStream out = new FileOutputStream(file);
		out.write(b);
		out.flush();
		out.close();
		return true;
	}

	// 从html中提取纯文本
	public static String html2Text(String inputString) {
		if (StringUtils.isEmpty(inputString)) {
			return null;
		}
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		// 剔除空格行
		textStr = textStr.replaceAll("[ |\t]+", " ");
		textStr = textStr.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
		return textStr;// 返回文本字符串
	}

	public static void main(String[] args) throws IOException {
		String msgstr = "<img bigimg=\"true\" src=\"data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAFxNE\"><img bigimg=\"true\" src=\"data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAFxNE\">";
		System.out.println(StringUtils.isEmpty(msgstr.substring(0,0)));
	}
}
