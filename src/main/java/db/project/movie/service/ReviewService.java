package db.project.movie.service;

import db.project.movie.dto.ReviewDto;
import db.project.movie.entity.MovieEntity;
import db.project.movie.entity.ReviewEntity;
import db.project.movie.repository.MovieRepository;
import db.project.movie.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public List<ReviewEntity> findAll(){

        return reviewRepository.findAll();


    }

    @Transactional(readOnly = true)
    public ReviewEntity findById(Long id){
        return reviewRepository.findById(id).orElse(null);
    }

    public ReviewEntity create(ReviewDto reviewDto){

        MovieEntity target = movieRepository.findById(reviewDto.getMovieId()).orElse(null);
        reviewDto.setMovieTitle(target.getTitle());
        ReviewEntity reviewEntity = reviewDto.toEntity();

        return reviewRepository.save(reviewEntity);
    }

    public ReviewEntity edit(Long id, ReviewDto reviewDto){
        ReviewEntity reviewEntity = reviewDto.toEntity();
        ReviewEntity target = reviewRepository.findById(id).orElse(null);

        if(target == null){
            return null;
        }

        target.patch(reviewEntity);
        return reviewRepository.save(target);
    }

    public ReviewEntity delete(Long id){
        ReviewEntity target = reviewRepository.findById(id).orElse(null);

        if(target == null){
            return null;
        }

        reviewRepository.delete(target);
        return target;
    }
}