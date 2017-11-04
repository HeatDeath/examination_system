package com.system.service.impl;

import com.system.mapper.CollegeMapper;
import com.system.po.College;
import com.system.po.CollegeExample;
import com.system.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
