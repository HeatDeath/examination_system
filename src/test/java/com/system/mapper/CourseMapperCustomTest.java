package com.system.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.BaseTest;
import com.system.po.*;
import org.apache.ibatis.session.RowBounds;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

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