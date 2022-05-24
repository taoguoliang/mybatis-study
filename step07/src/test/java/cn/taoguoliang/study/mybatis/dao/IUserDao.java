package cn.taoguoliang.study.mybatis.dao;

import cn.taoguoliang.study.mybatis.pojo.dto.User;

public interface IUserDao {

    User queryUser(String uId);

}

