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
        Twitter twitter = new Twitter(form.getLikes(), form.getFollowers());
        twitterMapper.createTwitter(twitter);
        return twitter;
    }

    @Override
    public void updateTwitter(Twitter updateTwitter) {
        Twitter twitter = twitterMapper.findById(updateTwitter.getId())
                .orElseThrow(() -> new ResourceNotFoundException("This id is not found"));
        if (Objects.isNull(updateTwitter.getLikes())) {
            updateTwitter.setLikes(twitter.getLikes());
        }
        if (Objects.isNull(updateTwitter.getFollowers())) {
            updateTwitter.setFollowers(twitter.getFollowers());
        }
        twitterMapper.updateTwitter(updateTwitter);
    }

    public void deleteTwitter(int id) {
        twitterMapper.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This id is not found"));
        twitterMapper.deleteTwitter(id);
    }

}
