package com.raisetech.mybatisdemo20236.controller;

import com.raisetech.mybatisdemo20236.entity.Twitter;
import com.raisetech.mybatisdemo20236.exception.ResourceNotFoundException;
import com.raisetech.mybatisdemo20236.form.TwitterCreateForm;
import com.raisetech.mybatisdemo20236.form.TwitterUpdateForm;
import com.raisetech.mybatisdemo20236.service.TwitterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class TwitterController {

    private final TwitterService twitterService;

    public TwitterController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @GetMapping("/twitter/{id}")
    public Twitter selectUserById(@PathVariable("id") int id) {
        return twitterService.findById(id);
    }

    @GetMapping("/twitter")
    public List<Twitter> selectUsersByLikes(@RequestParam(value = "likes", required = false) Integer likes) {
        return twitterService.findByLikes(likes);
    }


    @PostMapping("/twitter")
    public ResponseEntity<Map<String, String>> createName(@Validated TwitterCreateForm form, UriComponentsBuilder uriBuilder) {

        Twitter twitter = twitterService.createTwitter(form);
        URI url = uriBuilder
                .path("/twitter/" + twitter.getId())
                .build()
                .toUri();

        return ResponseEntity.created(url).body(Map.of("message", "twitter`s information successfully created"));
    }

    @PatchMapping("/twitter/{id}")
    public ResponseEntity<Map<String, String>> updateTwitter(@PathVariable("id") int id, @RequestBody TwitterUpdateForm nameUpdateForm) {
        return ResponseEntity.ok(Map.of("message", "twitter`s information successfully updated"));
    }

    @DeleteMapping("/twitter/{id}")
    public ResponseEntity<Map<String, String>> deleteTwitter(@PathVariable("id") int id) {
        return ResponseEntity.ok(Map.of("message", "twitter`s information successfully deleted"));
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFound(
            ResourceNotFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }

//    @GetMapping("/anime")
//    public List<AnimeResponse> anime() {
//        List<Anime> anime = animeService.findAll();
//        List<AnimeResponse> animeResponse = animeAll.stream()
//                .map(AnimeResponse::new)
//                .toList();
//        return animeResponse;エラーが出たためとりあえず保留


//    @GetMapping("/names")
//    public List<Name> getAnime_moviesByPublished_year(@RequestParam(value = "published_year") String published_year) {
//        Optional<Name> anime_movies = Optional.ofNullable(NameService.findByPublishedYear(published_year));
//        List<NameResponse> anime_MoviesResponse = anime_movies.stream()
//                .map(NameResponse::new)
//                .toList();
//        return nameResponse;
//    }


}
