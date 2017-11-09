package com.system.service.impl;

import com.system.mapper.SelectedCourseMapper;
import com.system.mapper.StudentMapper;
import com.system.po.*;
import com.system.service.SelectedCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacey on 2017/6/29.
 */
@Service
public class SelectedCourseServiceImpl implements SelectedCourseService {

    @Autowired
    private SelectedCourseMapper selectedCourseMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<SelectedCourseCustom> findByCourseID(Integer id) throws Exception {

        SelectedCourseExample selectedCourseExample = new SelectedCourseExample();
        selectedCourseExample.or().andCourseidEqualTo(id);

        List<SelectedCourse> list = selectedCourseMapper.selectByExample(selectedCourseExample);
        List<SelectedCourseCustom> secList = new ArrayList<SelectedCourseCustom>();
        for (SelectedCourse s: list) {
            SelectedCourseCustom sec = new SelectedCourseCustom();
            BeanUtils.copyProperties(s, sec);
            //判断是否完成类该课程
            if (sec.getMark() != null) {
                sec.setOver(true);
            }
            Student student = studentMapper.selectByPrimaryKey(sec.getStudentid());
            StudentCustom studentCustom = new StudentCustom();
            BeanUtils.copyProperties(student, studentCustom);

            sec.setStudentCustom(studentCustom);

            secList.add(sec);
        }

        return secList;
    }

//    public List<SelectedCourseCustom> findByCourseIDPaging(Integer page, Integer id) throws Exception {
//        return null;
//    }
    
    //获取该课程学生数
    @Override
    public Long countByCourseID(Integer id) throws Exception {
        SelectedCourseExample example = new SelectedCourseExample();
        example.or().andCourseidEqualTo(id);

        return selectedCourseMapper.countByExample(example);
    }

    //查询指定学生成绩
    @Override
    public SelectedCourseCustom findOne(SelectedCourseCustom selectedCourseCustom) throws Exception {

        SelectedCourseExample example = new SelectedCourseExample();
        SelectedCourseExample.Criteria criteria = example.createCriteria();

        criteria.andCourseidEqualTo(selectedCourseCustom.getCourseid());
        criteria.andStudentidEqualTo(selectedCourseCustom.getStudentid());

        List<SelectedCourse> list = selectedCourseMapper.selectByExample(example);


        if (list.size() > 0) {
            SelectedCourseCustom sc = new SelectedCourseCustom();
            BeanUtils.copyProperties(list.get(0), sc);

            Student student = studentMapper.selectByPrimaryKey(selectedCourseCustom.getStudentid());
            StudentCustom studentCustom = new StudentCustom();
            BeanUtils.copyProperties(student, studentCustom);

            sc.setStudentCustom(studentCustom);

            return sc;
        }

        return null;
    }

    @Override
    public void updateOne(SelectedCourseCustom selectedCourseCustom) throws Exception {
        SelectedCourseExample example = new SelectedCourseExample();
        SelectedCourseExample.Criteria criteria = example.createCriteria();

        criteria.andCourseidEqualTo(selectedCourseCustom.getCourseid());
        criteria.andStudentidEqualTo(selectedCourseCustom.getStudentid());

        selectedCourseMapper.updateByExample(selectedCourseCustom, example);

    }

    @Override
    public void save(SelectedCourseCustom selectedCourseCustom) throws Exception {
        selectedCourseMapper.insert(selectedCourseCustom);
    }

    @Override
    public List<SelectedCourseCustom> findByStudentID(Integer id) throws Exception {
        return null;
    }

    @Override
    public void remove(SelectedCourseCustom selectedCourseCustom) throws Exception {
        SelectedCourseExample example = new SelectedCourseExample();

        example.or().andCourseidEqualTo(selectedCourseCustom.getCourseid());
        example.or().andStudentidEqualTo(selectedCourseCustom.getStudentid());

        selectedCourseMapper.deleteByExample(example);
    }

}
