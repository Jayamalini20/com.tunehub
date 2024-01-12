package com.kodnest.boot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.boot.Entity.Playlist;

public interface PlaylistRepository 
			extends JpaRepository<Playlist, Integer>
{
	
}
