package com.kodnest.boot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.boot.Entity.Song;

public interface SongRepository 
           extends JpaRepository<Song, Integer>{

	public Song findByName(String name);
}
