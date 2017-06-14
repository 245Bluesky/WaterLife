package com.hlp.dao;

/**
 * Created by Administrator on 2017/6/11.
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hlp.model.UserVO;
import com.hlp.service.UserDaoImp;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class UserDao implements UserDaoImp {
    private JdbcTemplate jdbcTemplate;

    /*
     * 查询所有的数据信息
     * (non-Javadoc)
     * @see UserDAOInterface#find()
     */
    public List find(){
        String sql="select * from user";
        return jdbcTemplate.query(sql, new UserMapper());
    }
    /*
     *
     * 使用rowMapper 19行用到， 因为query方法不能直接放回一个数组，所以我们只能通过rowMapper赋值给uservo;
     *
     * RowMapper可以将数据中的每一行封装成用户定义的类，在数据库查询中，如果返回的类型是用户自定义的类型则需要包装
     */
    private static final class UserMapper implements RowMapper{


        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            // TODO Auto-generated method stub
            UserVO uservo=new UserVO();
            uservo.setId(rs.getInt("id"));
            uservo.setName(rs.getString("name"));
            uservo.setPwd(rs.getString("pwd"));
            return uservo;
        }


    }
    /*
     * 删除信息
     * (non-Javadoc)
     * @see UserDAOInterface#delete(int)
     */
    public void delete(int id) {
        // TODO Auto-generated method stub
        String sql="delete from user where id=?";
        jdbcTemplate.update(sql,id);
    }
    /*
     * 增加信息
     * (non-Javadoc)
     * @see UserDAOInterface#insert(UserVO)
     */
    public void insert(UserVO uservo) {
        // TODO Auto-generated method stub
        String sql=" insert into user (name,pwd) values(?,?)";
        jdbcTemplate.update(sql,new Object[]{
                uservo.getName(),uservo.getPwd()
        });
    }
    /*
     * 查询信息
     * (non-Javadoc)
     * @see UserDAOInterface#select(int)
     */
    public UserVO selectById(int id) {
        // TODO Auto-generated method stub
        String sql="select * from user where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<UserVO>(UserVO.class),id);

    }

    public UserVO selectByName(String name,String pwd){
        // TODO Auto-generated method stub
        String sql="select * from user where name=? and pwd=?";
        try{
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<UserVO>(UserVO.class),name,pwd);
        }catch(Exception e){
            return null;
        }

    }
    /*
     * 更新信息
     * (non-Javadoc)
     * @see UserDAOInterface#update(UserVO)
     */
    public void update(UserVO uservo) {
        // TODO Auto-generated method stub
        String sql="update user set name=?,pwd=? where id=?";
        jdbcTemplate.update(sql,uservo.getName(),uservo.getPwd(),uservo.getId());
    }
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
