package cz.fi.muni.pa165.musiclibrary.web.forms;

import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.facade.GenreFacade;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * @author Tomas Kovarik
 */
public class GenreDTOValidator implements Validator {

	private final GenreFacade genreFacade;

	public GenreDTOValidator(GenreFacade genreFacade) {
		this.genreFacade = genreFacade;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return GenreDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		GenreDTO genreDTO = (GenreDTO) target;

		if (genreDTO.getName() == null || genreDTO.getName().isEmpty()) {
			errors.rejectValue("name", "GenreDTOValidator.name.required");
		}

		GenreDTO existingGenre = genreFacade.findByName(genreDTO.getName());

		if (existingGenre != null && !Objects.equals(existingGenre.getId(), genreDTO.getId())) {
			errors.rejectValue("name", "GenreDTOValidator.name.alreadyExists");
		}
	}
}
