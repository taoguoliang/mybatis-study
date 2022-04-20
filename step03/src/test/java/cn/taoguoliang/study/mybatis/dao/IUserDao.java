package cn.taoguoliang.study.mybatis.dao;

public interface IUserDao {

    String queryUserInfoById(String uId);

    Integer queryUserAge(String uId);

}