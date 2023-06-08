package com.raisetech.mybatisdemo20236.controller;

import com.raisetech.mybatisdemo20236.entity.Anime;
import com.raisetech.mybatisdemo20236.service.AnimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimeController {

    private final AnimeService animeService;

    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping("/anime")
    public List<AnimeResponse> anime(List<AnimeResponse> animeResponses) {
        List<Anime> anime = animeService.findAll();
        List<AnimeResponse> animeResponse = anime.stream().map(AnimeResponse::new).toList();
        return animeResponses;
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
