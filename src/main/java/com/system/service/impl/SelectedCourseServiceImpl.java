package com.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.system.mapper.SelectedCourseCustomMapper;
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
    private SelectedCourseCustomMapper selectedCourseCustomMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<SelectedCourseCustom> findByCourseID(Integer id, int page, int rows) throws Exception {

//        SelectedCourseExample selectedCourseExample = new SelectedCourseExample();
//        selectedCourseExample.or().andCourseidEqualTo(id);

        PageHelper.startPage(page, rows);
        List<SelectedCourseCustom> list = selectedCourseCustomMapper.selectByCourseID(id);

        for (SelectedCourseCustom s: list) {

            //判断是否完成类该课程
            if (s.getMark() != null) {
                s.setOver(true);
            }else {
                s.setOver(false);
            }

        }

        return list;
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
    public boolean save(SelectedCourseCustom selectedCourseCustom) throws Exception {
        SelectedCourseExample example = new SelectedCourseExample();

        // where courseID == courseID and studentID == studentID
        example.or().andCourseidEqualTo(selectedCourseCustom.getCourseid())
                .andStudentidEqualTo(selectedCourseCustom.getStudentid());
        List<SelectedCourse> get_scc = selectedCourseMapper.selectByExample(example);
        if (get_scc.size() == 0){
            selectedCourseMapper.insert(selectedCourseCustom);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<SelectedCourseCustom> findByStudentID(Integer id) throws Exception {
        return selectedCourseCustomMapper.selectByStudentID(id);
    }

    @Override
    public void remove(SelectedCourseCustom selectedCourseCustom) throws Exception {
        SelectedCourseExample example = new SelectedCourseExample();

        // where courseID == courseID and studentID == studentID
        example.or().andCourseidEqualTo(selectedCourseCustom.getCourseid())
                .andStudentidEqualTo(selectedCourseCustom.getStudentid());

        selectedCourseMapper.deleteByExample(example);
    }

}
