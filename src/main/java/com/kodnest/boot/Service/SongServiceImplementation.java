package com.kodnest.boot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.boot.Entity.Song;
import com.kodnest.boot.Repository.SongRepository;

@Service
public class SongServiceImplementation implements SongService{
	@Autowired
	SongRepository repo;
	@Override
	public void addSong(Song song) {
		repo.save(song);
	}
	@Override
	public List<Song> fetchAllSongs() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	@Override
	public boolean songExists(String name) {
		if(repo.findByName(name)==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	@Override
	public void updateSong(Song song) {
		repo.save(song);
		
	}

}
