package com.chilly.examlibrary.service.Impl;

import com.chilly.examlibrary.entity.Classify;
import com.chilly.examlibrary.mapper.AdminClassifyMapper;
import com.chilly.examlibrary.service.AdminClassifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/21
 */
@Service
public class AdminClassifyServiceImpl implements AdminClassifyService {

    @Resource
    private AdminClassifyMapper adminClassifyMapper;

    @Override
    public List<Classify> listClassify() {
        return adminClassifyMapper.listClassify();
    }

    @Override
    public Classify getClassify(Long classify_id) {
        return adminClassifyMapper.getClassify(classify_id);
    }

    @Override
    public Classify getClassifyByName(String classify_name) {
        return adminClassifyMapper.getClassifyByName(classify_name);
    }

    @Override
    public int saveClassify(Classify classify) {
        return adminClassifyMapper.saveClassify(classify);
    }

    @Override
    public int updateClassify(Classify classify) {
        return adminClassifyMapper.updateClassify(classify);
    }

    @Override
    public int deleteClassify(Long classify_id) {
        return adminClassifyMapper.deleteClassify(classify_id);
    }
}
