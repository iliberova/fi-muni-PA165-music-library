package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;

import java.util.Date;
import java.util.List;

/**
 * Interface for DAO layer of entity Album.
 *
 * @author Iva Liberova
 */
public interface AlbumDao {
	/**
	 * Create new album in database
	 *
	 * @param album that will be created
	 */
	void create(Album album);

	/**
	 * Update album in database
	 *
	 * @param album that will be modified
	 */
	void update(Album album);

	/**
	 * Remove given album from database
	 *
	 * @param album that will be removed
	 */
	void remove(Album album);

	/**
	 * Finds album in database with given id
	 *
	 * @param id to be found
	 * @return album with given id
	 */
	Album findById(Long id);

	/**
	 * Returns all albums that contain song of given musician
	 *
	 * @param musician author of a song
	 * @return list of albums that contain song of given musician
	 */
	List<Album> findByMusician(Musician musician);

	/**
	 * Returns all albums that contain some song of given genre
	 *
	 * @param genre of some song in album
	 * @return list of albums that contain songs of given genre
	 */
	List<Album> findByGenre(Genre genre);

	/**
	 * Returns all albums that contain given patter in title
	 *
	 * @param titlePattern patter to be found
	 * @return list of albums containing given pattern
	 */
	List<Album> findByTitle(String titlePattern);

	/**
	 * Returns all albums in database
	 *
	 * @return list of all albums
	 */
	List<Album> findAll();

	/**
	 * Returns all albums released between given dates
	 *
	 * @param startDate first valid date
	 * @param endDate last valid date
	 * @return list of all albums that were released
	 */
	List<Album> getAlbumsReleasedBetween(Date startDate, Date endDate);
}
