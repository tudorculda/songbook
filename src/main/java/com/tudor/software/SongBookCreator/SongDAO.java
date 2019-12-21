package com.tudor.software.SongBookCreator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SongDAO {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public List<SongDTO> getSongs(){
		  return jdbcTemplate.query(
	                "select * from songs order by upper(trim(title))",
	                (rs, rowNum) ->
	                        new SongDTO(
	                        		 rs.getString("title"),
	                        		 rs.getString("lyrics"),
	                        		 rs.getString("verse_order")
		                               
	                        )
	        );
	    }

}
