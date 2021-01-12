package com.chilly.examlibrary.service;

import com.chilly.examlibrary.entity.Book;
import com.chilly.examlibrary.entity.query.BookQuery;

import java.util.HashMap;
import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/23
 */
public interface UserBookService {

    /**
     * 书籍列表展示
     * @return
     */
    HashMap<String,List<BookQuery>> listBook();

    /**
     * id查名
     * @param book_id
     * @return
     */
    BookQuery getBookQuery(Long book_id);

    /**
     * id查详情
     * @param book_id
     * @return
     */
    Book getBook(Long book_id);

}
