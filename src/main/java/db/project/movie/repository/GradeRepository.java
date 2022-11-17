package db.project.movie.repository;

import db.project.movie.entity.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface GradeRepository extends JpaRepository<GradeEntity, Long> {

    ArrayList<GradeEntity> findAll();

    @Query(value = "SELECT * FROM GRADE_ENTITY WHERE movie_id = :movieId",
            nativeQuery = true)
    List<GradeEntity> findByMovieId(Long movieId);

    @Query(value = "SELECT * FROM GRADE_ENTITY WHERE title = :title",
            nativeQuery = true)
    List<GradeEntity> findByTitle(String title);



}