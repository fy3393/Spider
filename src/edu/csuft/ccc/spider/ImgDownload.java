package edu.csuft.ccc.spider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 图片下载
 * @author ccc
 *
 */
public class ImgDownload {
	
	// 文件下载地址
	String downloadUrl;
	
	// 文件名
	String name;
	
	// 下载文件保存路径
	String path;
	
	/**
	 * 构造方法
	 * @param 下载网址
	 * @param 图片id
	 * @param 下载存放路径
	 */
	public ImgDownload(String downloadUrl, String name, String path) {
		this.downloadUrl = downloadUrl;
		this.name = name;
		this.path = path;
	}

	/**
	 * 下载文件
	 */
	public void download() {
		try {
 
			URL url = new URL(downloadUrl);
			
			// 访问资源
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			// 设置超时间为3秒
			conn.setConnectTimeout(3 * 1000);
			
			// 防止屏蔽程序抓取而返回403错误
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
 
			// 从连接对象获取输入流
			InputStream inputStream = conn.getInputStream();
			
			// 建立缓存
			byte[] buffer = readInputStream(inputStream);
 
			// 检测文件保存路径是否存在，若不存在，则创建
			File saveDir = new File(path);
			if (!saveDir.exists()) {
				saveDir.mkdir();
			}
			
			File file = new File(saveDir + File.separator + name + ".jpg");
			
			//创建输出流写入文件
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(buffer);
			if (fos != null) {
				fos.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			 System.out.println(Thread.currentThread()+"进度:"+name+" 下载成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}
 
	/**
	 * 从输入流中获取字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}
}
