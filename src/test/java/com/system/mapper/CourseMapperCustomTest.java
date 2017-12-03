package com.system.mapper;

import com.system.BaseTest;
import com.system.dao.CourseMapper;
import com.system.pojo.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Jacey on 2017/6/29.
 */
public class CourseMapperCustomTest extends BaseTest {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    public void selectAll() throws Exception {

        CourseExample example = new CourseExample();
        example.or().andCourseidBetween(1,6);
        List<Course> list = courseMapper.selectByExample(example);

        for(Course course : list) {
            System.out.println(course.toString());
        }
    }
}