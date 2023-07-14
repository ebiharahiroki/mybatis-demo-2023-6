package com.raisetech.mybatisdemo20236.controller;

import com.raisetech.mybatisdemo20236.entity.Twitter;
import com.raisetech.mybatisdemo20236.form.TwitterCreateForm;
import com.raisetech.mybatisdemo20236.form.TwitterUpdateForm;
import com.raisetech.mybatisdemo20236.service.TwitterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
public class TwitterController {

    private final TwitterService twitterService;

    @GetMapping("/tweets/{id}")
    public Twitter selectTwitterById(@PathVariable("id") int id) {
        return twitterService.findById(id);
    }

    @GetMapping("/tweets")
    public List<Twitter> selectTwitterByLikes(@RequestParam(value = "likes", required = false) Integer likes) {
        return twitterService.findByLikes(likes);
    }

    @PostMapping("/tweets")
    public ResponseEntity<Map<String, String>> createTwitter(
            @RequestBody @Validated TwitterCreateForm form, UriComponentsBuilder uriBuilder) {

        Twitter twitter = twitterService.createTwitter(form);
        URI url = uriBuilder
                .path("/tweets/" + twitter.getId())
                .build()
                .toUri();

        return ResponseEntity.created(url).body(Map.of("message", "tweets information successfully created"));
    }

    @PatchMapping("/tweets/{id}")
    public ResponseEntity<Map<String, String>> updateTwitter(
            @PathVariable("id") int id, @RequestBody TwitterUpdateForm twitterUpdateForm,
            UriComponentsBuilder uriBuilder) {
        twitterService.updateTwitter(twitterUpdateForm.convertToTwitter(id));
        URI url = uriBuilder
                .path("/tweets/")
                .build()
                .toUri();
        if (updateTwitter(id, twitterUpdateForm, uriBuilder) != null) {
            return ResponseEntity.created(url).body(Map.of("message", "tweets information successfully updated"));
        }
        return null;
    }

    @DeleteMapping("/tweets/{id}")
    public ResponseEntity<Map<String, String>> deleteTwitter(@PathVariable("id") int id) {
        twitterService.deleteTwitter(id);
        if (deleteTwitter(id) != null) {
            return ResponseEntity.ok(Map.of("message", "tweets information successfully deleted"));
        }
        return null;
    }
}
