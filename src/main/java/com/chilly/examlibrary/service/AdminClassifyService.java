package com.chilly.examlibrary.service;

import com.chilly.examlibrary.entity.Classify;

import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/21
 */
public interface AdminClassifyService {

    /**
     * 列表展示所有分类
     * @return
     */
    List<Classify> listClassify();

    /**
     * 查
     *
     * @param classify_id
     * @return
     */
    Classify getClassify(Long classify_id);

    /**
     * 按名字查
     *
     * @param classify_name
     * @return
     */
    Classify getClassifyByName(String classify_name);

    /**
     * 保存
     *
     * @param classify
     * @return 成功返回1
     */
    int saveClassify(Classify classify);

    /**
     * 更改
     *
     * @param classify
     * @return 成功返回1
     */
    int updateClassify(Classify classify);

    /**
     * 删除
     *
     * @param classify_id
     */
    int deleteClassify(Long classify_id);
}
