package edu.csuft.ccc.spider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ͼƬ����
 * @author ccc
 *
 */
public class ImgDownload {
	
	// �ļ����ص�ַ
	String downloadUrl;
	
	// �ļ���
	String name;
	
	// �����ļ�����·��
	String path;
	
	/**
	 * ���췽��
	 * @param ������ַ
	 * @param ͼƬid
	 * @param ���ش��·��
	 */
	public ImgDownload(String downloadUrl, String name, String path) {
		this.downloadUrl = downloadUrl;
		this.name = name;
		this.path = path;
	}

	/**
	 * �����ļ�
	 */
	public void download() {
		try {
 
			URL url = new URL(downloadUrl);
			
			// ������Դ
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			// ���ó�ʱ��Ϊ3��
			conn.setConnectTimeout(3 * 1000);
			
			// ��ֹ���γ���ץȡ������403����
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
 
			// �����Ӷ����ȡ������
			InputStream inputStream = conn.getInputStream();
			
			// ��������
			byte[] buffer = readInputStream(inputStream);
 
			// ����ļ�����·���Ƿ���ڣ��������ڣ��򴴽�
			File saveDir = new File(path);
			if (!saveDir.exists()) {
				saveDir.mkdir();
			}
			
			File file = new File(saveDir + File.separator + name + ".jpg");
			
			//���������д���ļ�
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(buffer);
			if (fos != null) {
				fos.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			 System.out.println(Thread.currentThread()+"����:"+name+" ���سɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}
 
	/**
	 * ���������л�ȡ�ֽ�����
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
