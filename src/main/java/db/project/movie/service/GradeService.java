package db.project.movie.service;

import db.project.movie.dto.GradeDto;
import db.project.movie.entity.GradeEntity;
import db.project.movie.entity.MovieEntity;
import db.project.movie.repository.GradeRepository;
import db.project.movie.repository.MovieRepository;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ToString
@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<GradeDto> grades(Long movieId) {
        return gradeRepository.findByMovieId(movieId) //댓글 목록 조회
                .stream() // 엔티티 -> DTO 변환
                .map(gradeEntity -> GradeDto.createGradeDto(gradeEntity))
                .collect(Collectors.toList());
    }

    @Transactional
    public GradeDto create(Long movieId, GradeDto dto) {
        //댓글 생성이니까 댓글을 생성할 영화를 먼저찾기위해 movieRepository로 조회
        MovieEntity movie = movieRepository.findById(movieId) // 조회했을때 없으면 예외발생
                .orElseThrow(() -> new IllegalArgumentException("찾으려는 영화가 없어요"));

        GradeEntity grade = GradeEntity.createGrade(dto, movie);
        GradeEntity created = gradeRepository.save(grade);
        return GradeDto.createGradeDto(created); // DTO로 변환하여 리턴해주기
    }

    @Transactional
    public GradeDto update(Long id, GradeDto dto) {
        // 댓글 수정이니까 gradeRepository로 조회
        GradeEntity target = gradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾으려는 댓글이 없어요"));

        target.patch(dto); //target은 위에서 GradeEntity타입으로 만든변수
        GradeEntity updated = gradeRepository.save(target);

        return GradeDto.createGradeDto(updated);
    }
    @Transactional
    public GradeDto delete(Long id) {
        GradeEntity target = gradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾으려는 댓글이 없어요"));
        gradeRepository.delete(target); // 삭제니까 반환값이 없음
        return GradeDto.createGradeDto(target);
    }



}