package com.devsuperior.dsmovie.services;

import org.springframework.transaction.annotation.Transactional;
import com.devsuperior.dsmovie.Entities.Movie;
import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.Entities.Score;
import com.devsuperior.dsmovie.Entities.User;
import com.devsuperior.dsmovie.Repositories.MovieRepository;
import com.devsuperior.dsmovie.Repositories.UserRepository;
import com.devsuperior.dsmovie.Repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.devsuperior.dsmovie.dto.ScoreDTO;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    public MovieDTO saveScore(ScoreDTO dto){
        User user =  userRepository.findByEmail(dto.getEmail());        
        if(user == null){
            user = new User();
            user.setEmail(dto.getEmail());
            user = userRepository.saveAndFlush(user);
        }
        Movie movie = movieRepository.findById(dto.getMovieId()).get();
        Score score = new Score();
        score.setMovie(movie);
        score.setUser(user);
        score.setValue(dto.getScore());

        score = scoreRepository.saveAndFlush(score);

        double sum = 0.0;
        for(Score s : movie.getScores()){
            sum = sum + s.getValue();
        }
        double avg = sum / movie.getScores().size();

        movie.setScore(avg);
        movie.setCount(movie.getScores().size());
        movie = movieRepository.save(movie);
        return new MovieDTO(movie);
    }    
}