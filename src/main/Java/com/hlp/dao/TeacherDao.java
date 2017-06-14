package com.hlp.dao;

/**
 * Created by Administrator on 2017/6/11.
 */
import com.hlp.dao.PageResult;
import com.hlp.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyz on 2016-8-3.
 */
@Repository
public class TeacherDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public PageResult<Teacher> queryPage(Integer currentPage,Integer numPerPage,Teacher teacher){
        StringBuffer sql=new StringBuffer();
        sql.append("select tid, tno,tname,dno from teacher where 1=1 ");
        if(teacher!=null){
            if(teacher.getTname()!=null && !teacher.getTname().equals("")){
                sql.append(" and tname like '");
                sql.append(teacher.getTname());
                sql.append("%' ");
            }
            if(teacher.getDno()!=null && !teacher.getDno().equals("")){
                sql.append(" and dno like '");
                sql.append(teacher.getDno());
                sql.append("%' ");
            }
        }
        sql.append(" order by tno asc");
        PageResult<Teacher> page=new PageResult<Teacher>(sql.toString(),currentPage,numPerPage,this.jdbcTemplate);
        return page;
    }
    public List<Teacher> getTeachers(){
        List<Teacher> teachers=new ArrayList<Teacher>();
        String sql="select tid, tno,tname,dno from teacher";
        teachers=jdbcTemplate.query(sql,new BeanPropertyRowMapper<Teacher>(Teacher.class));
        return teachers;
    }
    public void addTeacher(final Teacher teacher){
        String sql="insert into teacher(tno,tname,dno) values(?,?,?)";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,teacher.getTno());
                ps.setString(2,teacher.getTname());
                ps.setString(3,teacher.getDno());
            }
        });
    }

    public Teacher getTeacher(int tid){
        String sql="select tid,tno,tname,dno from teacher where tid=?";
        Teacher teacher=jdbcTemplate.queryForObject(sql,new Object[]{tid},new BeanPropertyRowMapper<Teacher>(Teacher.class));
        return teacher;
    }

    public void update(Teacher teacher){
        String sql="update teacher set tno=?,tname=?,dno=? where tid=?";
        jdbcTemplate.update(sql,new Object[]{teacher.getTno(),teacher.getTname(),teacher.getDno(),teacher.getTid()});
    }

    public void delete(int tid){
        String sql="delete from teacher where tid=?";
        jdbcTemplate.update(sql,new Object[]{tid});
    }
}
