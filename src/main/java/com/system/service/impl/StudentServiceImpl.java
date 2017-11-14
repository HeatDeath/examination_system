package com.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.system.mapper.CollegeMapper;
import com.system.mapper.StudentCustomMapper;
import com.system.mapper.StudentMapper;
import com.system.po.Student;
import com.system.po.StudentCustom;
import com.system.po.StudentExample;
import com.system.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private StudentCustomMapper studentCustomMapper;


    @Override
    public void updateById(Integer id, Student student) throws Exception {
        studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public void removeById(Integer id) throws Exception {
        studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Boolean save(Student student) throws Exception {
        Student result = studentMapper.selectByPrimaryKey(student.getUserid());
        if (result == null) {
            studentMapper.insert(student);
            return true;
        }
        return false;
    }

    @Override
    public long getCountStudent() throws Exception {
        StudentExample studentExample = new StudentExample();
        // where studentId is not null
        studentExample.or().andUseridIsNotNull();
        return studentMapper.countByExample(studentExample);
    }

    @Override
    public StudentCustom findById(Integer id) throws Exception {
        Student student  = studentMapper.selectByPrimaryKey(id);
        StudentCustom studentCustom = null;
        if (student != null) {
            studentCustom = new StudentCustom();
            //类拷贝
            BeanUtils.copyProperties(student, studentCustom);
        }
        return studentCustom;
    }

    @Override
    public List<StudentCustom> findByName(Student student, int page, int rows) throws Exception {
        String name;
        if (StringUtil.isEmpty(student.getUsername())){
            name = "%";
        }else {
            name = "%" + student.getUsername() + "%";
        }

        PageHelper.startPage(page, rows);

        return studentCustomMapper.selectByName(name);
    }
}
