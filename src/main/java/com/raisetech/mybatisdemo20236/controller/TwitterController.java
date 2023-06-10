package com.raisetech.mybatisdemo20236.controller;

import com.raisetech.mybatisdemo20236.TwitterCreateForm;
import com.raisetech.mybatisdemo20236.entity.Twitter;
import com.raisetech.mybatisdemo20236.service.TwitterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class TwitterController {

    private final TwitterService twitterService;

    public TwitterController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @GetMapping("/twitter")
    public List<TwitterResponse> anime() {
        List<Twitter> twitter = twitterService.findAll();
        List<TwitterResponse> twitterResponse = twitter.stream().map(TwitterResponse::new).toList();
        return twitterResponse;
    }

    @PostMapping("/twitter")
    public ResponseEntity<Map<String, String>> createName(@RequestBody TwitterCreateForm twitterCreateForm) {
        // 登録処理は割愛
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080").path("/twitter/id")
                .build()
                .toUri();

        System.out.println("==================");
        System.out.println(twitterCreateForm.getId());
        System.out.println(twitterCreateForm.getLikes());
        System.out.println(twitterCreateForm.getFollowers());
        System.out.println("===================");

        return ResponseEntity.created(url).body(Map.of("message", "name successfully created"));
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
