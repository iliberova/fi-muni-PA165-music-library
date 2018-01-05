package cz.fi.muni.pa165.musiclibrary.web.forms;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import cz.fi.muni.pa165.musiclibrary.facade.AlbumFacade;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Iva Liberova
 * @author Jan-Sebastian Fabik
 */
public class AlbumCreateFormDataValidator implements Validator {

	private final AlbumFacade albumFacade;

	public AlbumCreateFormDataValidator(AlbumFacade albumFacade) {
		this.albumFacade = albumFacade;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return AlbumCreateFormData.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AlbumCreateFormData albumData = (AlbumCreateFormData) target;

		if (albumData.getTitle() == null || albumData.getTitle().isEmpty()) {
			errors.rejectValue("title", "AlbumCreateFormDataValidator.title.required");
		}

		AlbumDTO existingAlbum = albumFacade.findByTitle(albumData.getTitle());

		if (existingAlbum != null) {
			errors.rejectValue("title", "AlbumCreateFormDataValidator.title.alreadyExists");
		}

		if (!albumData.isReleaseDateFilled()) {
			errors.rejectValue("releaseDate", "AlbumCreateFormDataValidator.releaseDate.required");
		}

		if (!albumData.isReleaseDateValid()) {
			errors.rejectValue("releaseDate", "AlbumCreateFormDataValidator.releaseDate.invalid");
		}

		if (albumData.getAlbumArt() == null || albumData.getAlbumArt().length == 0) {
			errors.rejectValue("albumArt", "AlbumCreateFormDataValidator.albumArt.required");
		}
	}
}
