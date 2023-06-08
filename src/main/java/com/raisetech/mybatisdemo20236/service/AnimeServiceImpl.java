package com.raisetech.mybatisdemo20236.service;

import com.raisetech.mybatisdemo20236.entity.Anime;
import com.raisetech.mybatisdemo20236.mapper.AnimeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeServiceImpl implements AnimeService {

    private final AnimeMapper animeMappaer;

    public AnimeServiceImpl(AnimeMapper animeMapper) {
        this.animeMappaer = animeMapper;
    }

    public List<Anime> findAll() {
        return animeMappaer.findAll();
    }

//    @Override
//    public List<Anime> findAll() {
//        return animeMappaer.findAll();
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
//        return this.animeMappaer.findByEvaluatedValue(evaluated_value);
//    }

}
