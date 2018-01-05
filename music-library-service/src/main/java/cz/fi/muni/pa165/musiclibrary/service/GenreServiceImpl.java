package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.dao.GenreDao;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.utils.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Kovarik Tomas
 */

@Service
public class GenreServiceImpl implements GenreService {

	private final GenreDao genreDao;

	@Autowired
	public GenreServiceImpl(GenreDao genreDao) {
		this.genreDao = genreDao;
	}

	@Override
	public Genre create(Genre g) {
		genreDao.create(g);
		return g;
	}

	@Override
	public void update(Genre g) {
		genreDao.update(g);
	}

	@Override
	public void delete(Genre g) {
		genreDao.delete(g);
	}

	@Override
	public Genre findById(Long id) {
		return genreDao.findById(id);
	}

	@Override
	public Genre findByName(String name) {
		return genreDao.findByName(name);
	}

	@Override
	public List<Genre> findByNameLike(String query) {
		List<String> patterns = SearchHelper.splitSearchQuery(query);
		return genreDao.findByNameLike(patterns);
	}

	@Override
	public List<Genre> findAll() {
		return genreDao.findAll();
	}
}
