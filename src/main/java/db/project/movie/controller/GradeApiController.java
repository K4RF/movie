package db.project.movie.controller;

import db.project.movie.dto.GradeDto;
import db.project.movie.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GradeApiController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("/api/movies/{movieId}/grades") // 전체보기
    public ResponseEntity<List<GradeDto>> grades(@PathVariable Long movieId){
        List<GradeDto> dtos = gradeService.grades(movieId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping("/api/movies/{movieId}/grades")
    public ResponseEntity<GradeDto> create(@PathVariable Long movieId,
                                           @RequestBody GradeDto dto){
        GradeDto createdDto = gradeService.create(movieId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    @PatchMapping("/api/grades/{id}")
    public ResponseEntity<GradeDto> update(@PathVariable Long id,
                                           @RequestBody GradeDto dto){
        GradeDto updatedDto = gradeService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/api/grades/{id}")
    public ResponseEntity<GradeDto> delete(@PathVariable Long id){
        GradeDto deleted = gradeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }

}