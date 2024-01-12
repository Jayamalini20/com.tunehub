package com.kodnest.boot.Service;

import java.util.List;

import com.kodnest.boot.Entity.Playlist;

public interface PlaylistService {

	public void addPlaylist(Playlist playlist);

	public List<Playlist> fetchAllPlaylists();

	

}
