package com.joker.service.impl;

import com.joker.mapper.EssayMapper;
import com.joker.service.EssayMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Joker
 * @name:
 * @Date: 2020/7/15 20:41
 * @Describe:
 **/
@Service
public class EssayMapperServiceImpl implements EssayMapperService {

    @Autowired
    EssayMapper mapper;

    @Override
    public String test() {
        return "test";
    }
}
