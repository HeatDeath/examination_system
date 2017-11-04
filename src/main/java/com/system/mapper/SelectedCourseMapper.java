package com.system.mapper;

import com.system.po.SelectedCourse;
import com.system.po.SelectedCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SelectedCourseMapper {
    long countByExample(SelectedCourseExample example);

    int deleteByExample(SelectedCourseExample example);

    int insert(SelectedCourse record);

    int insertSelective(SelectedCourse record);

    List<SelectedCourse> selectByExample(SelectedCourseExample example);

    int updateByExampleSelective(@Param("record") SelectedCourse record, @Param("example") SelectedCourseExample example);

    int updateByExample(@Param("record") SelectedCourse record, @Param("example") SelectedCourseExample example);
}