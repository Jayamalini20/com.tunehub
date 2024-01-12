package com.kodnest.boot.Service;

import java.util.List;

import com.kodnest.boot.Entity.Song;

public interface SongService {

	public void addSong(Song song);

	public List<Song> fetchAllSongs();

	public boolean songExists(String name);

	public void updateSong(Song song);

}
