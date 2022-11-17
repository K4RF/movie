package db.project.movie.controller;

import db.project.movie.dto.ReviewDto;
import db.project.movie.entity.MovieEntity;
import db.project.movie.entity.ReviewEntity;
import db.project.movie.repository.MovieRepository;
import db.project.movie.repository.ReviewRepository;
import db.project.movie.service.MovieService;
import db.project.movie.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MovieService movieService;

    @GetMapping("/review")      // 리뷰 리스트 보여주기
    public String allReviewPage(Model model){
//        List<ReviewEntity> reviewEntityList = reviewRepository.findAll();
        List<ReviewEntity> reviewEntityList = reviewService.findAll();
        model.addAttribute("reviewEntityList", reviewEntityList);
        return "/review/reviews";
    }

    @GetMapping("/review/{id}") // 리뷰 디테일 보여주기
    public String detailReviewPage(@PathVariable Long id, Model model){

//        ReviewEntity reviewEntity = reviewRepository.findById(id).orElse(null);
        ReviewEntity reviewEntity = reviewService.findById(id);
        model.addAttribute("reviewEntity", reviewEntity);

        return "/review/detail";
    }

    @GetMapping("/review/new")  // 새 리뷰 작성 창
    public String newReviewPage(Model model){

//        List<MovieEntity> movieEntityList = movieRepository.findAll();
        List<MovieEntity> movieEntityList = movieService.findAll();

        model.addAttribute("movieEntityList", movieEntityList);
        return "/review/newReview";
    }

    @PostMapping("/review/create") // 새 리뷰 작성 후 DB 저장
    public String createReview(ReviewDto reviewDto){
        // movieId title writer score content
        ReviewEntity reviewEntity = reviewService.create(reviewDto);
        return "redirect:/review";
    }

    // 리뷰 수정 페이지
    @GetMapping("/review/edit/{id}")
    public String editReviewPage(@PathVariable Long id, Model model){

        ReviewEntity reviewEntity = reviewService.findById(id);
        model.addAttribute("reviewEntity", reviewEntity);
        return "/review/edit";
    }


    // 리뷰 수정
    @PostMapping("/review/update")
    public String update(Long id, ReviewDto reviewDto){

        ReviewEntity reviewEntity = reviewService.edit(id, reviewDto);
//        ReviewEntity reviewEntity = reviewDto.toEntity();
//        ReviewEntity target = reviewRepository.findById(reviewDto.getId()).orElse(null);
//
//        if(target != null){
//            reviewRepository.save(reviewEntity);
//        }
//        else{
//            // 수정할 내용이 없을 시 들어갈 내용
//        }
        return "redirect:/review/" + reviewDto.getId();
    }

    // 삭제
    @GetMapping("/review/delete/{id}")
    public String deleteReview(@PathVariable Long id){
        ReviewEntity reviewDelete = reviewService.delete(id);
        return "redirect:/review";
    }
}