package com.chilly.examlibrary.service.Impl;

import com.chilly.examlibrary.entity.Book;
import com.chilly.examlibrary.entity.query.BookQuery;
import com.chilly.examlibrary.mapper.AdminBookMapper;
import com.chilly.examlibrary.mapper.UserBookMapper;
import com.chilly.examlibrary.service.UserBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/23
 */
@Service
public class UserBookServiceImpl implements UserBookService {

    //日志输出
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserBookMapper userBookMapper;

    @Resource
    private AdminBookMapper adminBookMapper;

    @Override
    public HashMap<String,List<BookQuery>> listBook() {

        HashMap<String,List<BookQuery>> listHashMap = new HashMap<>();

        List<BookQuery> bookQueryList = userBookMapper.listBook();

        //去重，获取所有标题
        HashSet<String> classify = new HashSet<String>();
        for (BookQuery bookQuery : bookQueryList){
            classify.add(bookQuery.getBook_classify_name());
        }
        logger.info("classify: ",classify);

        //按照相同标题分组存入
        for(String t : classify){
            List<BookQuery> bookQueries = new ArrayList<>();

            for (BookQuery bookQuery : bookQueryList){
                if (t.equals(bookQuery.getBook_classify_name()))
                    bookQueries.add(bookQuery);
            }

            listHashMap.put(t,bookQueries);

        }

        return listHashMap;
    }

    @Override
    public BookQuery getBookQuery(Long book_id) {
        return userBookMapper.getBookQuery(book_id);
    }

    @Override
    public Book getBook(Long book_id) {
        return adminBookMapper.getBook(book_id);
    }
}
