package db.project.movie.controller;

import db.project.movie.dto.GradeDto;
import db.project.movie.dto.MovieDto;

import db.project.movie.entity.MovieEntity;
import db.project.movie.repository.MovieRepository;
import db.project.movie.service.GradeService;
import db.project.movie.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;
    @Autowired
    private GradeService gradeService;

    // movieDetail 화면 전체 view 페이지
    @GetMapping("/detail")
    public String allMoviePage(Model model){

        List<MovieEntity> movieEntityList = movieService.findAll();
        // todo: 이미 MovieEntity 를 선언했기 때문에 추가로 선언할 필요가 없음
//        List<MovieEntity> movieEntity = movieService.findAll(); // 추가해봤는데...

        model.addAttribute("movieEntityList", movieEntityList);
        // todo: view 페이지에 해당 데이터를 전달할 필요가 없음
//        model.addAttribute("movieEntity", "/detail/{{id}}"); //추가해봤는데...

        return"/movie/details";
    }

    // movie detail view 페이지
    @GetMapping("/detail/{id}")
    public String detailMovie(@PathVariable Long id, Model model){

        MovieEntity movieEntity = movieService.findMovie(id);
        List<GradeDto> gradeDtos = gradeService.grades(id);

        model.addAttribute("movieEntity",movieEntity);
        model.addAttribute("gradesDtos", gradeDtos);
        return "/movie/detail";
    }

    // movie 등록 view 페이지
    @GetMapping("/detail/new")
    public String newMoviePage(){

        return"/movie/newDetail";
    }

    // movie 등록 post
    @PostMapping("/detail/create")
    public String createMovie(MovieDto movieDto, @RequestParam("imgFile") MultipartFile poster) throws Exception {
        // todo: @RequestParam("imgFile") 를 이용해서 post 시 데이터 전달. input 태그의 name 속성이 'imgFile' 이면 전달됨

        MovieEntity movieEntity = movieService.create(movieDto, poster);
        // todo: view 페이지에 해당 데이터를 전달할 필요가 없음
//       model.addAttribute("poster", movieEntity); // 모델 추가하긴했는데...

        return "redirect:/detail/"+movieEntity.getId();
    }

    // movie 수정 view 페이지
    @GetMapping("/detail/update/{id}")
    public String updateMoviePage(@PathVariable Long id,Model model){

        MovieEntity movieEntity = movieService.findMovie(id);
        model.addAttribute("movieEntity", movieEntity);
        return "/movie/update";
    }

    // movie 수정 patch
    @PatchMapping("/detail/edit") // action
    public String editMovie(@PathVariable Long id, MovieDto movieDto){
        MovieEntity movieEntity = movieService.edit(id, movieDto);
        return "redirect:/detail";
    }

    // movie 삭제 delete
    @GetMapping("/detail/delete/{id}")
    public String deleteMovie(@PathVariable Long id){
        MovieEntity movieEntity = movieService.delete(id);
        return "redirect:/detail";
    }

}