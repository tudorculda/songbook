package com.tudor.software.SongBookCreator;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class SongMapper {
	public static List<Song> parseList(List<SongDTO> rawSongs) {			
		return rawSongs.stream()
				.map(SongMapper::parseSong)
				.collect(Collectors.toList());		
	}
	
	public static Song parseSong(SongDTO dto) {
		SAXBuilder saxBuilder = new SAXBuilder();
		Song toReturn = new Song();
		try {
			Document doc = saxBuilder.build(new StringReader(dto.lyrics));
			Element classElement = doc.getRootElement();
			List<Element> verses = classElement.getChild("lyrics").getChildren("verse");
			StringBuilder lyricsBuilder = new StringBuilder();
			lyricsBuilder.append("Ordine ").append(dto.order);
			verses.forEach( v ->{
				lyricsBuilder.append("\n\r\n\r[")
				.append(v.getAttributeValue("type"))
				.append(v.getAttributeValue("label"))
				.append(":]")
				.append(v.getText());
			});
			toReturn.title = dto.title;
			toReturn.lyrics = lyricsBuilder.toString();
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

}
