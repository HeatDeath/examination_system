package com.system.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.po.*;
import com.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
//@SessionAttributes({"courseCount", "studentCount"})
@RequestMapping("/admin")
public class AdminController {
    // 分页行数
    private static final int ROWS = 2;
    // 默认初始密码
    private static final String DEFAULT_PASSWORD = "123";
    // 学生角色编号
    private static final Integer STUDENT_ROLE = 2;
    // 教师角色编号
    private static final Integer TEACHER_ROLE = 1;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private UserLoginService userLoginService;

    // -----------------------------------------------------------------------
    // ---------------------  【课程】管理部分 ---------------------------------
    // -----------------------------------------------------------------------

    // 显示 or 搜索 课程信息
    @RequestMapping(value = {"/showCourse", "/searchCourse"})
    public ModelAndView showCourse(Course course,
                                   @RequestParam(required = false, defaultValue = "1") int page) throws Exception{
        ModelAndView result = new ModelAndView("/admin/showCourse");
        // 获取指定的课程 list
        List<Course> courseList = courseService.selectCourseByName(course, page, ROWS);
        result.addObject("pageInfo", new PageInfo<Course>(courseList));
        result.addObject("page", page);
        result.addObject("rows", ROWS);
        result.addObject("queryParam", course);
        return result;
    }

    // 添加课程 页面跳转
    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public ModelAndView addCourse() throws Exception{
        ModelAndView result = new ModelAndView("/admin/addCourse");
        // 将 teacherList 和 collegeList 添加到 数据模型 Model 中
        result.addObject("teacherList", teacherService.findAll());
        result.addObject("collegeList", collegeService.finAll());
        return result;
    }

    // 添加课程 表单处理
    @ResponseBody
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public String doAddCourse(CourseCustom courseCustom) throws Exception{
        // 保存新添加的课程的结果
        Boolean result = courseService.save(courseCustom);
        Map<String, Object> resultMap = new HashMap<>();

        if (result){
            resultMap.put("msg", "success");
            resultMap.put("page_url", "/admin/showCourse");
        }else {
            resultMap.put("msg", "fail");
            resultMap.put("page_url", "/admin/addCourse");
        }
        return JSON.toJSONString(resultMap);
    }

    // 编辑课程信息 页面跳转
    @RequestMapping(value = "/editCourse", method = RequestMethod.GET)
    public ModelAndView editCourse(@RequestParam("id") int courseId) throws Exception{
        // 通过 courseId 找到对应的 CourseCustom
        CourseCustom courseCustom = courseService.findById(courseId);
        // 找到全部的教师
        List<Teacher> teacherList = teacherService.findAll();
        // 找到全部的学院
        List<College> collegeList = collegeService.finAll();
        ModelAndView result = new ModelAndView();
        result.setViewName("/admin/editCourse");
        result.addObject("teacherList", teacherList);
        result.addObject("collegeList", collegeList);
        result.addObject("course", courseCustom);
        return result;
    }

    // 编辑课程信息 表单处理
    @ResponseBody
    @RequestMapping(value = "/editCourse", method = RequestMethod.POST)
    public String doEditCourse(Course course) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();

        // 尝试 更新 课程信息
        try {
            courseService.updateByPrimaryKey(course);
            resultMap.put("msg", "success");
            resultMap.put("page_url", "/admin/showCourse");

        // 更新失败的话，alert 提示信息，返回课程编辑页面
        }catch (Exception e){
            resultMap.put("msg", "fail");
            resultMap.put("page_url", "/admin/editCourse?id=" + course.getCourseid());
        }

        // 返回 将 resultMap 转换后的 JSON 字符串
        return JSON.toJSONString(resultMap);
    }

    // 删除课程
    @ResponseBody
    @RequestMapping(value = "/removeCourse", method = RequestMethod.GET)
    public String removeCourse(@RequestParam("id") int courseId) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();

        // 尝试 删除 课程信息
        // 删除成功的话，alert 提示 成功信息
        try {
            courseService.removeById(courseId);
            resultMap.put("msg", "success");

            // 删除失败的话，alert 提示 失败信息
        }catch (Exception e){
            resultMap.put("msg", "fail");
        }

        // 返回 将 resultMap 转换后的 JSON 字符串
        return JSON.toJSONString(resultMap);
    }

    // -----------------------------------------------------------------------
    // ---------------------  【学生】管理部分 ---------------------------------
    // -----------------------------------------------------------------------

    // 显示 or 搜索 学生信息
    @RequestMapping(value = {"/showStudent", "/searchStudent"})
    public ModelAndView showStudent(Student student,
                                    @RequestParam(required = false, defaultValue = "1") int page) throws Exception {
        ModelAndView result = new ModelAndView("/admin/showStudent");
        List<StudentCustom> studentList = studentService.findByName(student, page, ROWS);
        result.addObject("pageInfo", new PageInfo<StudentCustom>(studentList));
        result.addObject("page", page);
        result.addObject("rows", ROWS);
        result.addObject("queryParam", student);
        return result;
    }

    // 添加学生信息 页面跳转
    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public ModelAndView addStudent() throws Exception{
        // 找出所有学院
        List<College> collegeList = collegeService.finAll();
        ModelAndView modelAndView = new ModelAndView("/admin/addStudent");
        modelAndView.addObject("collegeList", collegeList);
        return modelAndView;
    }

    // 添加学生信息 表单处理
    @ResponseBody
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String doAddStudent(StudentCustom studentCustom) throws Exception{
        Boolean result = studentService.save(studentCustom);
        Map<String, Object> resultMap = new HashMap<>();
        if (result){
            resultMap.put("msg", "success");
            resultMap.put("page_url", "/admin/showStudent");
            // 保存新添加的学生信息到 UserLogin 表
            UserLogin userLogin = new UserLogin();
            userLogin.setUserid(studentCustom.getUserid());
            userLogin.setUsername(studentCustom.getUsername());
            userLogin.setPassword(DEFAULT_PASSWORD);
            userLogin.setRole(STUDENT_ROLE);
            userLoginService.save(userLogin);
        }else {
            resultMap.put("msg", "fail");
            resultMap.put("page_url", "/admin/addStudent");
        }
        return JSON.toJSONString(resultMap);
    }

    // 编辑学生信息 页面跳转
    @RequestMapping(value = "/editStudent", method = RequestMethod.GET)
    public ModelAndView editStudent(@RequestParam("id") int studentId) throws Exception{
        StudentCustom studentCustom = studentService.findById(studentId);
        List<College> collegeList = collegeService.finAll();
        ModelAndView result = new ModelAndView();
        result.setViewName("/admin/editStudent");
        result.addObject("student", studentCustom);
        result.addObject("collegeList", collegeList);
        return result;
    }

    // 编辑学生信息 表单处理
    @ResponseBody
    @RequestMapping(value = "/editStudent", method = RequestMethod.POST)
    public String doEditStudent(Student student) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        try {
            studentService.updateById(student.getUserid(), student);
            resultMap.put("msg", "success");
            resultMap.put("page_url", "/admin/showStudent");
        }catch (Exception e){
            resultMap.put("msg", "fail");
            resultMap.put("page_url", "/admin/editStudent?id=" + student.getUserid());
        }
        return JSON.toJSONString(resultMap);
    }

    // 删除学生
    @ResponseBody
    @RequestMapping(value = "/removeStudent", method = RequestMethod.GET)
    public String removeStudent(@RequestParam("id") int studentId) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        try {
            studentService.removeById(studentId);
            resultMap.put("msg", "success");
        }catch (Exception e){
            resultMap.put("msg", "fail");
        }
        return JSON.toJSONString(resultMap);
    }

    // -----------------------------------------------------------------------
    // ---------------------  【教师】管理部分 ---------------------------------
    // -----------------------------------------------------------------------

    // 显示 or 搜索 教师信息
    @RequestMapping(value = {"/showTeacher", "/searchTeacher"})
    public ModelAndView showStudent(Teacher teacher,
                                    @RequestParam(required = false, defaultValue = "1") int page) throws Exception {
        ModelAndView result = new ModelAndView("/admin/showTeacher");
        List<TeacherCustom> teacherCustomList = teacherService.findByName(teacher, page, ROWS);
        result.addObject("pageInfo", new PageInfo<TeacherCustom>(teacherCustomList));
        result.addObject("page", page);
        result.addObject("rows", ROWS);
        result.addObject("queryParam", teacher);
        return result;
    }

    // 添加教师信息 页面跳转
    @RequestMapping(value = "/addTeacher", method = RequestMethod.GET)
    public ModelAndView addTeacher() throws Exception{
        // 找出所有学院
        List<College> collegeList = collegeService.finAll();
        ModelAndView modelAndView = new ModelAndView("/admin/addTeacher");
        modelAndView.addObject("collegeList", collegeList);
        return modelAndView;
    }

    // 添加教师信息 表单处理
    @ResponseBody
    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
    public String doAddTeacher(TeacherCustom teacherCustom) throws Exception{
        Boolean result = teacherService.save(teacherCustom);
        Map<String, Object> resultMap = new HashMap<>();
        if (result){
            resultMap.put("msg", "success");
            resultMap.put("page_url", "/admin/showTeacher");
            // 保存新添加的教师信息到 UserLogin 表
            UserLogin userLogin = new UserLogin();
            userLogin.setUserid(teacherCustom.getUserid());
            userLogin.setPassword(teacherCustom.getUsername());
            userLogin.setPassword(DEFAULT_PASSWORD);
            userLogin.setRole(TEACHER_ROLE);
            userLoginService.save(userLogin);
        }else {
            resultMap.put("msg", "fail");
            resultMap.put("page_url", "/admin/showTeacher");
        }
        return JSON.toJSONString(resultMap);
    }

    // 编辑教师信息 页面跳转
    @RequestMapping(value = "/editTeacher", method = RequestMethod.GET)
    public ModelAndView editTeacher(@RequestParam("id") int teacherId) throws Exception{
        TeacherCustom teacherCustom = teacherService.findById(teacherId);
        List<College> collegeList = collegeService.finAll();
        ModelAndView result = new ModelAndView();
        result.setViewName("/admin/editTeacher");
        result.addObject("teacher", teacherCustom);
        result.addObject("collegeList", collegeList);
        return result;
    }

    // 编辑教师信息 表单处理
    @ResponseBody
    @RequestMapping(value = "/editTeacher", method = RequestMethod.POST)
    public String doEditTeacher(Teacher teacher) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        try {
            teacherService.updateById(teacher.getUserid(), teacher);
            resultMap.put("msg", "success");
            resultMap.put("page_url", "/admin/showTeacher");
        }catch (Exception e){
            resultMap.put("msg", "fail");
            resultMap.put("page_url", "/admin/editTeacher?id=" + teacher.getUserid());
        }
        return JSON.toJSONString(resultMap);
    }

    // 删除教师
    @ResponseBody
    @RequestMapping(value = "/removeTeacher", method = RequestMethod.GET)
    public String removeTeacher(@RequestParam("id") int teacherId) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        try {
            teacherService.removeById(teacherId);
            resultMap.put("msg", "success");
        }catch (Exception e){
            resultMap.put("msg", "fail");
        }
        return JSON.toJSONString(resultMap);
    }

    // -----------------------------------------------------------------------
    // ---------------------  【密码】修改重置 部分 -----------------------------
    // -----------------------------------------------------------------------

    // 重置普通用户密码 页面跳转
    @RequestMapping(value = "/userPasswordReset", method = RequestMethod.GET)
    public String userPasswordReset() throws Exception{
        return "admin/userPasswordReset";
    }

    // 重置普通用户密码 表单处理
    @ResponseBody
    @RequestMapping(value = "/userPasswordReset", method = RequestMethod.POST)
    public String doUserPasswordReset(UserLogin userLogin) throws Exception{
        UserLogin getUser = userLoginService.findByName(userLogin.getUsername());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("page_url", "/admin/userPasswordReset");

        if (getUser != null){
            if (getUser.getRole() == 0){
                resultMap.put("msg", "该账户为管理员账户！");
            }else {
                getUser.setPassword(userLogin.getPassword());
                userLoginService.updateByName(userLogin.getUsername(), getUser);
                resultMap.put("msg", "成功修改用户的账户密码~");
            }
        }else {
            resultMap.put("msg", "没有找到该用户！");
        }
        return JSON.toJSONString(resultMap);
    }

    // 重置管理员账户密码 页面跳转
    @RequestMapping(value = "/passwordReset", method = RequestMethod.GET)
    public String passwordReset() throws Exception{
        return "admin/passwordReset";
    }
}
