package db.project.movie.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="movie_review")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(nullable = false, name = "movie_title")
    private String movieTitle;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @Column
    private String writer;

    @Column
    private Long score;

    @Column
    private Long views;


    public void patch(ReviewEntity reviewEntity){
        if(reviewEntity.movieTitle != null ){
            this.movieTitle = reviewEntity.movieTitle;
        }
        if(reviewEntity.title != null ){
            this.title = reviewEntity.title;
        }
        if(reviewEntity.content != null ){
            this.content = reviewEntity.content;
        }
        if(reviewEntity.writer != null ){
            this.writer = reviewEntity.writer;
        }
        if(reviewEntity.score != null ){
            this.score = reviewEntity.score;
        }

    }
/*
    public static ReviewEntity createReview(ReviewDto dto, MovieEntity movie){
        return null;
    }
*/
}