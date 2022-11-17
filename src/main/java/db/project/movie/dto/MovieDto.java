package db.project.movie.dto;

import db.project.movie.entity.MovieEntity;
import lombok.AllArgsConstructor;
import lombok.ToString;



@AllArgsConstructor
@ToString
public class MovieDto {

    private Long id;

    private String imgName;

    private String imgUrl;

    private String title;

    private String content;

    private String director;

    private String actor;

    private Integer movieTime;

    private String grade;

    public MovieEntity toEntity(){
        return new MovieEntity(id, imgName, imgUrl, title, content, director,actor, movieTime, grade);
    }
}
