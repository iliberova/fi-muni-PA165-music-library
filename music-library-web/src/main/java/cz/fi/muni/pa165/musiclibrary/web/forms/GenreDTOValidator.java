package cz.fi.muni.pa165.musiclibrary.web.forms;

import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Tomas Kovarik
 */
public class GenreDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return GenreDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		GenreDTO GenreDTO = (GenreDTO) target;

		if (GenreDTO.getName() == null || GenreDTO.getName().isEmpty()) {
			errors.rejectValue("name", "GenreDTOValidator.name.required");
		}
	}
}
