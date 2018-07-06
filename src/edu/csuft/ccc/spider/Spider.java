package edu.csuft.ccc.spider;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * ��������
 * @author ccc
 *
 */
public class Spider implements Runnable{
	
	String url;
	
	/**
	 * �洢��Ӱ��Ϣ
	 */
	List<Film> filmList;
	
	/**
	 * ���췽��
	 * @param ��վ��·��
	 */
	public Spider(String url) {
		this.url=url;
		filmList = new ArrayList<>();
	}

	public void run() {
		try {
			Document doc = Jsoup.connect(url).get();
			
			//���itemԪ��
			Elements es = doc.select(".grid_view .item");
//			System.out.println(es.size());
			
			//����
			for (Element item:es) {
				Film film = new Film();
				film.title = item.select(".info .title").text();
				film.poster = item.select(".pic img").get(0).attr("src");
				film.rating = item.select(".star span").get(3).text();
				film.quote = item.select(".inq").text();
				String id = item.select("em").get(0).text();
				film.id = Integer.parseInt(id);
				String star = item.select(".rating_num").text();
				film.star = Double.parseDouble(star);
				
				
//				System.out.println(film);
				filmList.add(film);
				}
			
			//���浽���ݿ�
			
			// ��ûỰ���������ݿ����ӳأ�
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(new FileReader("config.xml"));
			
			// ���һ���Ự������),����Ϊ�Զ��ύ����
			SqlSession session = factory.openSession(true);
			
			// ���һ��mapper�����䣩
			FilmMapper mapper = session.getMapper(FilmMapper.class);
			
			for (Film f : filmList){
				try {
					mapper.add(f);
				} catch (PersistenceException e) {
					mapper.update(f);
				}
				new ImgDownload(f.poster,f.id+"","C:\\Java\\eclipse\\eclipse-workspace\\Spider\\img").download();;
				System.out.println(f.id+f.title+"�洢�ɹ�");
			}


			session.close();
			
//			System.out.println(filmList.size());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
