package cz.fi.muni.pa165.musiclibrary.web.forms;

import cz.fi.muni.pa165.musiclibrary.dto.GenreCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.facade.GenreFacade;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Tomas Kovarik
 */
public class GenreCreateDTOValidator implements Validator {

	private final GenreFacade genreFacade;

	public GenreCreateDTOValidator(GenreFacade genreFacade) {
		this.genreFacade = genreFacade;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return GenreCreateDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		GenreCreateDTO genreCreateDTO = (GenreCreateDTO) target;

		if (genreCreateDTO.getName() == null || genreCreateDTO.getName().isEmpty()) {
			errors.rejectValue("name", "GenreCreateDTOValidator.name.required");
		}

		GenreDTO existingGenre = genreFacade.findByName(genreCreateDTO.getName());

		if (existingGenre != null) {
			errors.rejectValue("name", "GenreCreateDTOValidator.name.alreadyExists");
		}
	}
}
