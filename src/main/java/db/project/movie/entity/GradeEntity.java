package db.project.movie.entity;

import db.project.movie.dto.GradeDto;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class GradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @Column
    private String title;

    @Column
    private Integer grade;

    @Column
    private String body;

    public void patch(GradeDto dto) { // 이 메소드는 GradeService.update()에 있음
        if(this.id != dto.getId()) // 예외처리
            throw new IllegalArgumentException("잘못된 id 입니다");
        //나머지는 입력된 내용들로 갱신시켜주기
        if(dto.getTitle() != null)
            this.title = dto.getTitle();
        if(dto.getGrade() != null)
            this.grade = dto.getGrade();
        if(dto.getBody() != null)
            this.body = dto.getBody();
    }
    public static GradeEntity createGrade(GradeDto dto, MovieEntity movie) {
        if(dto.getId() != null){ // 아이디가 입력되면 안되는데 입력됐을 때
            throw new IllegalArgumentException("id입력 하면 안돼요");
        }
        if(dto.getMovieId() != movie.getId()){ // 해당영화는 1번인데 댓글은 2번에 만들려고할때
            throw new IllegalArgumentException(("id가 잘못됐어요"));
        }
        return new GradeEntity(
                dto.getId(),
                movie,
                dto.getTitle(),
                dto.getGrade(),
                dto.getBody()
        );
    }
}