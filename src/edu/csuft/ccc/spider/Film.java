package edu.csuft.ccc.spider;

/**
 * 电影（实体类）
 * @author ccc
 *
 */
public class Film {
	
	/**
	 * 排名
	 */
	int id;
	
	/**
	 * 片名
	 */
	String title;
	
	/**
	 * 海报路径
	 */
	String poster;
	
	/**
	 * 评分
	 */
	double star;
	
	/**
	 * 投票人数
	 */
	String rating;
	
	/**
	 * 引用
	 */
	String quote;
	

	public Film() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", poster=" + poster + ", star=" + star + ", rating=" + rating
				+ ", quote=" + quote + "]";
	}
	
}
