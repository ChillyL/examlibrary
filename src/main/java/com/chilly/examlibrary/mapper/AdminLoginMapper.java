package com.chilly.examlibrary.mapper;


import com.chilly.examlibrary.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminLoginMapper {

    /**
     *
     * @param number
     * @param password
     * @return
     */
    Admin adminLogin(@Param("number") String number, @Param("password") String password);
}
