package com.raisetech.mybatisdemo20236.service;

import com.raisetech.mybatisdemo20236.entity.Twitter;
import com.raisetech.mybatisdemo20236.exception.ResourceNotFoundException;
import com.raisetech.mybatisdemo20236.form.TwitterCreateForm;
import com.raisetech.mybatisdemo20236.mapper.TwitterMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TwitterServiceImpl implements TwitterService {

    private final TwitterMapper twitterMapper;

    public TwitterServiceImpl(TwitterMapper twitterMapper) {
        this.twitterMapper = twitterMapper;
    }

    public List<Twitter> findAll() {
        return twitterMapper.findAll();
    }

    public Twitter findById(int id) {
        return twitterMapper.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This id is not found"));
    }

    public List<Twitter> findByLikes(Integer likes) {
        if (likes != null) {
            return twitterMapper.findByLikesGreaterThan(likes);
        } else {
            return twitterMapper.findAll();
        }
    }

    @Override
    public Twitter createTwitter(TwitterCreateForm form) {
        Twitter twitter = new Twitter(form.getId(), form.getLikes(), form.getFollowers());
        twitterMapper.createTwitter(twitter);
        return twitter;
    }

    @Override
    public void updateTwitter(Twitter updateTwitter) {
        Twitter user = twitterMapper.findById(updateTwitter.getId())
                .orElseThrow(() -> new ResourceNotFoundException("This id is not found"));
        if (Objects.isNull(updateTwitter.getLikes())) {
            updateTwitter.setLikes(user.getLikes());
        }
        if (Objects.isNull(updateTwitter.getFollowers())) {
            updateTwitter.setFollowers(user.getFollowers());
        }
        twitterMapper.updateTwitter(updateTwitter);
    }

//    @Override
//    public List<Anime> findAll() {
//        return animeMapper.findAll();
//    }
//
//    @Override
//    public Anime findById(int id) {
//        return null;
//    }
//
//    @Override
//    public Anime findByEvaluated_Value(String evaluated_value) {
//        return null;
//    }
//
//
//    @Override
//    public Optional<Anime> findByEvaluatedValue(String evaluated_value) {
//        return this.animeMapper.findByEvaluatedValue(evaluated_value);
//    }

}
