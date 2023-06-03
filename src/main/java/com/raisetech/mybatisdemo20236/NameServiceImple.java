package com.raisetech.mybatisdemo20236;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NameServiceImple implements NameService {

    private final NameMapper nameMapper;

    public NameServiceImple(NameMapper nameMapper) {
        this.nameMapper = nameMapper;
    }

    public List<Name> findAll() {
        return nameMapper.findAll();
    }
}
