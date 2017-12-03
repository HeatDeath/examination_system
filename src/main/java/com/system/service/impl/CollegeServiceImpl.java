package com.system.service.impl;

import com.system.dao.CollegeMapper;
import com.system.pojo.College;
import com.system.pojo.CollegeExample;
import com.system.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    // 查找所有学院
    @Override
    public List<College> finAll() throws Exception {
        CollegeExample collegeExample = new CollegeExample();
        collegeExample.or().andCollegeidIsNotNull();
        return collegeMapper.selectByExample(collegeExample);
    }
}
