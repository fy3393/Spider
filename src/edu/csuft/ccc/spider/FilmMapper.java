package edu.csuft.ccc.spider;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * myBatis
 * @author ccc
 *
 */
@Mapper
public interface FilmMapper {
	
	// insert into film() values()
	@Insert("insert into film(id,title,poster,star,rating,quote) values(#{id},#{title,jdbcType=VARCHAR},#{poster,jdbcType=VARCHAR},#{star},#{rating,jdbcType=VARCHAR},#{quote,jdbcType=VARCHAR})")

	void add(Film film);
	
	// uodate film
	@Update("UPDATE film SET title = #{title,jdbcType=VARCHAR} , poster=#{poster,jdbcType=VARCHAR} , star = #{star} , rating=#{rating,jdbcType=VARCHAR} ,quote = #{quote,jdbcType=VARCHAR}  WHERE id = #{id}")
	void update(Film film);
	
	@Select("select * from film")
	List<Film> findAll();
}
