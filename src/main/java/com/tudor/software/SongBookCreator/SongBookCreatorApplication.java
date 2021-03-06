package com.tudor.software.SongBookCreator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SongBookCreatorApplication implements CommandLineRunner {
	
	@Autowired
	private SongDAO songDao;
	static int i = 1;

		public static void main(String[] args) {
	        new SpringApplicationBuilder(SongBookCreatorApplication.class).headless(false).run(args);
	    }

	    @Override
	    public void run(String... args) {
	       List<SongDTO> rawSongs = songDao.getSongs();
	       List<Song> songs = SongMapper.parseList(rawSongs);
	       OdtCreator.makeBook(songs);
	       System.out.println("-----------Done-------------");
	    }

		

}
