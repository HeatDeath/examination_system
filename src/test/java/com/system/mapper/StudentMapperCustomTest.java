//package com.system.mapper;
//
//import com.system.po.PagingVO;
//import com.system.po.Student;
//import com.system.po.StudentCustom;
//import org.apache.log4j.Logger;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.util.List;
//
//public class StudentMapperCustomTest {
//
//    private ApplicationContext applicationContext;
//
//    Logger log = Logger.getLogger(StudentMapperCustomTest.class);
//
//    @Before
//    public void setUp() throws Exception {
//        applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
//    }
//
//    @Test
//    public void findByPaging() throws Exception {
//        StudentMapperCustom studentMapperCustom = (StudentMapperCustom) applicationContext.getBean("studentMapperCustom");
//
//        PagingVO pagingVO = new PagingVO();
//        pagingVO.setToPageNo(1);
//
//        List<StudentCustom> list = studentMapperCustom.findByPaging(pagingVO);
//
//        log.info(list.toString());
//
//    }
//
//    @Test
//    public void findStudentAndSelectCourseListByName() throws Exception{
//        StudentMapperCustom studentMapperCustom = (StudentMapperCustom) applicationContext.getBean("studentMapperCustom");
//        StudentCustom studentCustom = studentMapperCustom.findStudentAndSelectCourseListById(10001);
//       log.info(studentCustom.toString());
//
//    }
//
//
//}
