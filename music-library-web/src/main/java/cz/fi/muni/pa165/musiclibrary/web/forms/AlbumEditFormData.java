package cz.fi.muni.pa165.musiclibrary.web.forms;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jan-Sebastian Fabik
 */
public class AlbumEditFormData {

	private Long id;

	private String releaseDate;

	private String title;

	private String commentary;

	private byte[] albumArt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	boolean isReleaseDateFilled() {
		return releaseDate != null && !releaseDate.isEmpty();
	}

	boolean isReleaseDateValid() {
		try {
			parseReleaseDate();
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

	private Date parseReleaseDate() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		return dateFormat.parse(releaseDate);
	}

	private Date parseReleaseDateAndReturnNullIfNotValid() {
		try {
			return parseReleaseDate();
		} catch (ParseException ex) {
			return null;
		}
	}

	public String getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	public byte[] getAlbumArt() {
		return this.albumArt;
	}

	public void setAlbumArt(MultipartFile albumArt) throws IOException {
		this.albumArt = albumArt.isEmpty() ? null : albumArt.getBytes();
	}

	public void updateAlbumDTO(AlbumDTO albumDTO) {
		albumDTO.setReleaseDate(this.parseReleaseDateAndReturnNullIfNotValid());
		albumDTO.setTitle(this.title);
		albumDTO.setCommentary(this.commentary);

		if (this.albumArt != null && this.albumArt.length != 0) {
			albumDTO.setAlbumArt(this.albumArt);
		}
	}
}
