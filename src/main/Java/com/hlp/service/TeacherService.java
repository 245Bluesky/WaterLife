package com.hlp.service;

/**
 * Created by Administrator on 2017/6/11.
 */
import com.hlp.dao.TeacherDao;
import com.hlp.dao.PageResult;
import com.hlp.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zyz on 2016-8-3.
 */
@Service
public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;
    public PageResult<Teacher> queryPageBusiness(Integer currentPage,Integer numPerPage,Teacher teacher){
        return teacherDao.queryPage(currentPage,numPerPage,teacher);
    }
    public List<Teacher> getTeachers(){
        return teacherDao.getTeachers();
    }
    public void addTeacher(Teacher teacher){
        teacherDao.addTeacher(teacher);
    }
    public Teacher getTeacher(int tid){ return teacherDao.getTeacher(tid);}
    public void update(Teacher teacher){teacherDao.update(teacher);}
    public void delete(int tid){teacherDao.delete(tid);}
}