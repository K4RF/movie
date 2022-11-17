package db.project.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import db.project.movie.entity.GradeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GradeDto {

    private Long id;
    @JsonProperty("movie_id") //JSON에서는 "movie_id"로 날라오니까 그걸로 맵핑시켜줘
    private Long movieId;
    private String title;
    private Integer grade;
    private String body;

    // JSON을 DTO로 변환하는 메소드
    public static GradeDto createGradeDto(GradeEntity grade) {
        return new GradeDto(
                grade.getId(),
                grade.getMovie().getId(),
                grade.getTitle(),
                grade.getGrade(),
                grade.getBody()
        );
    }

//    public GradeEntity toEntity(){
//        return new GradeEntity(id, movieId, title, grade, body);
//    }

}