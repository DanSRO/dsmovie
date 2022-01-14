package com.devsuperior.dsmovie.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsuperior.dsmovie.Entities.Score;
import com.devsuperior.dsmovie.Entities.ScorePK;
import com.devsuperior.dsmovie.Entities.User;

public interface ScoreRepository extends JpaRepository<Score, ScorePK>{
    User findByEmail(String email);    
}