package cz.fi.muni.pa165.musiclibrary.web.forms;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import cz.fi.muni.pa165.musiclibrary.facade.AlbumFacade;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * @author Jan-Sebastian Fabik
 */
public class AlbumEditFormDataValidator implements Validator {

	private final AlbumFacade albumFacade;

	public AlbumEditFormDataValidator(AlbumFacade albumFacade) {
		this.albumFacade = albumFacade;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return AlbumEditFormData.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AlbumEditFormData albumData = (AlbumEditFormData) target;

		if (albumData.getTitle() == null || albumData.getTitle().isEmpty()) {
			errors.rejectValue("title", "AlbumEditFormDataValidator.title.required");
		}

		AlbumDTO existingAlbum = albumFacade.findByTitle(albumData.getTitle());

		if (existingAlbum != null && !Objects.equals(existingAlbum.getId(), albumData.getId())) {
			errors.rejectValue("title", "AlbumEditFormDataValidator.title.alreadyExists");
		}

		if (!albumData.isReleaseDateFilled()) {
			errors.rejectValue("releaseDate", "AlbumEditFormDataValidator.releaseDate.required");
		}

		if (!albumData.isReleaseDateValid()) {
			errors.rejectValue("releaseDate", "AlbumEditFormDataValidator.releaseDate.invalid");
		}

	}
}
