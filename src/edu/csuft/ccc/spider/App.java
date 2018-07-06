package edu.csuft.ccc.spider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




public class App {
	
	public static void main(String[] args) {
		
		// 线程池
		ExecutorService pool;
		
		// 创建线程池，同时响应多个客户端的请求[并发编程]
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