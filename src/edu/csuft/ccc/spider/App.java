package edu.csuft.ccc.spider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




public class App {
	
	public static void main(String[] args) {
		
		// �̳߳�
		ExecutorService pool;
		
		// �����̳߳أ�ͬʱ��Ӧ����ͻ��˵�����[�������]
		pool = Executors.newCachedThreadPool();
		pool = Executors.newFixedThreadPool(4);
		String url = "https://movie.douban.com/top250?start=";
		int i=0;
		while(true) {
			pool.execute(new Spider(url+i));
			i+=25;
			if(i>=250)
				break;
		}
	}
}