package cn.taoguoliang.study.mybatis.dao;

import cn.taoguoliang.study.mybatis.pojo.dto.User;

public interface IUserDao {

    User queryUser(Long uId);

    User queryUserInfo(User user);
}

