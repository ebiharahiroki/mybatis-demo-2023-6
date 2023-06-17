package com.raisetech.mybatisdemo20236.service;

import com.raisetech.mybatisdemo20236.entity.Twitter;
import com.raisetech.mybatisdemo20236.mapper.TwitterMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterServiceImpl implements TwitterService {

    private final TwitterMapper twitterMapper;

    public TwitterServiceImpl(TwitterMapper twitterMapper) {
        this.twitterMapper = twitterMapper;
    }

    public List<Twitter> findAll() {
        return twitterMapper.findAll();
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
