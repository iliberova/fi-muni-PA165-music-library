package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of DAO layer of entity Album.
 *
 * @author Iva Liberova
 */
@Repository
@Transactional
public class AlbumDaoImpl implements AlbumDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Album album){
		em.persist(album);
	}

	@Override
	public void update(Album album){
		em.merge(album);
	}

	@Override
	public void remove(Album album){
		em.remove(findById(album.getId()));
	}

	@Override
	public Album findById(Long id){
		return em.find(Album.class, id);
	}

	@Override
	public List<Album> findByMusician(Musician musician){
		List<Song> songs = em.createQuery("SELECT s FROM Song s WHERE s.musician = :musician",
				Song.class).setParameter("musician", musician).getResultList();
		List<Album> albums = new ArrayList<>();
		for (Song song : songs) {
			albums.add(song.getAlbum());
		}
		return albums.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public List<Album> findByGenre(Genre genre){
		List<Song> songs = em.createQuery("SELECT s FROM Song s WHERE s.genre = :genre",
				Song.class).setParameter("genre", genre).getResultList();
		List<Album> albums = new ArrayList<>();
		for (Song song : songs) {
			albums.add(song.getAlbum());
		}
		return albums.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public List<Album> findByTitle(String titlePattern){
		return em.createQuery("SELECT a FROM Album a WHERE a.title like :title ",
				Album.class).setParameter("title", "%" + titlePattern + "%").getResultList();
	}

	@Override
	public List<Album> findAll() {
		return em.createQuery("SELECT a FROM Album a", Album.class).getResultList();
	}

	@Override
	public List<Album> getAlbumsReleasedBetween(Date startDate, Date endDate) {
		return em.createQuery("SELECT a FROM Album a WHERE a.releaseDate BETWEEN :startDate AND :endDate",
				Album.class).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
	}
}
