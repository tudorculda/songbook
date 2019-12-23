package com.tudor.software.SongBookCreator;

import java.util.List;

import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.dom.element.office.OfficeTextElement;
import org.odftoolkit.odfdom.incubator.doc.office.OdfOfficeAutomaticStyles;
import org.odftoolkit.odfdom.incubator.doc.office.OdfOfficeStyles;
import org.odftoolkit.odfdom.incubator.doc.text.OdfTextHeading;
import org.odftoolkit.odfdom.incubator.doc.text.OdfTextParagraph;
import org.odftoolkit.odfdom.pkg.OdfFileDom;

public class OdtCreator {
	public static int i = 1;
	
	private OdfTextDocument outputDocument;

	private OdfFileDom contentDom;  // the document object model for content.xml
	private OdfFileDom stylesDom;   // the document object model for styles.xml
	// the office:automatic-styles element in content.xml
	// this is here for the sake of completeness; this program doesn't use it
	private OdfOfficeAutomaticStyles contentAutoStyles;

	// the office:styles element in styles.xml
	private OdfOfficeStyles stylesOfficeStyles;

	// the office:text element in the content.xml file
	private OfficeTextElement officeText;

	public static void makeBook(List<Song> songs) {
		OdtCreator docBuilder = new OdtCreator();
		docBuilder.setupOutputDocument();
		songs.forEach(song -> docBuilder.addSong(song));		
		docBuilder.saveOutputDocument();		
	}
	
	private void addSong(Song song) {
		OdfTextHeading  heading = new OdfTextHeading(contentDom);
		heading.addContent(String.valueOf(i++) + ". " + song.title);
		OdfTextParagraph paragraph = new OdfTextParagraph(contentDom);
		paragraph.addContentWhitespace(song.lyrics);
		officeText.appendChild(heading);
		officeText.appendChild(paragraph);
	}

	void setupOutputDocument()
	{
	    try
	    {
	        outputDocument = OdfTextDocument.newTextDocument();

	        contentDom = outputDocument.getContentDom();
	        stylesDom = outputDocument.getStylesDom();
	        
	        contentAutoStyles = contentDom.getOrCreateAutomaticStyles();
	        stylesOfficeStyles = outputDocument.getOrCreateDocumentStyles();

	        officeText = outputDocument.getContentRoot();
	    }
	    catch (Exception e)
	    {
	        System.err.println("Unable to create output file.");
	        System.err.println(e.getMessage());
	        outputDocument = null;
	    }
	}
	
	void saveOutputDocument()
	{
	    try
	    {
	        outputDocument.save("carte cantari.odt");
	    }
	    catch (Exception e)
	    {
	        System.err.println("Unable to save document.");
	        System.err.println(e.getMessage());
	    }
	}

}
