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
    public void updateById(Integer id, StudentCustom studentCustom) throws Exception {
        studentMapper.updateByPrimaryKey(studentCustom);
    }

    @Override
    public void removeById(Integer id) throws Exception {
        studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Boolean save(StudentCustom studentCustom) throws Exception {
        Student student = studentMapper.selectByPrimaryKey(studentCustom.getUserid());
        if (student == null) {
            studentMapper.insert(studentCustom);
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
        return (StudentCustom) studentMapper.selectByPrimaryKey(id);

    }

    @Override
    public List<StudentCustom> findByName(Student student, int page, int rows) throws Exception {
//        StudentExample studentExample = new StudentExample();
        String name;
        if (StringUtil.isEmpty(student.getUsername())){
//            studentExample.or().andUsernameLike("%");
            name = "%";
        }else {
            name = "%" + student.getUsername() + "%";
        }

        PageHelper.startPage(page, rows);

        return studentCustomMapper.selectByName(name);

//        List<StudentCustom> studentCustomList = null;
//
//        if (studentList != null) {
//            studentCustomList = new ArrayList<>();
//            for (Student stu : studentList) {
//                StudentCustom studentCustom = new StudentCustom();
//
//                // copy stu 中的属性
//                BeanUtils.copyProperties(stu, studentCustom);
//
//                // 获取并设置学院名称
//                studentCustom.setCollegeName(collegeMapper.selectByPrimaryKey(stu.getCollegeid()).getCollegename());
//                studentCustomList.add(studentCustom);
//            }
//        }
//        return studentCustomList;
//        return studentList;
    }

    @Override
    public StudentCustom findStudentAndSelectCourseListByName(String name) throws Exception {

        return null;
    }
}
