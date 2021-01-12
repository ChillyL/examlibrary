package com.chilly.examlibrary.service;

import com.chilly.examlibrary.entity.Book;

import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/22
 */
public interface AdminBookService {

    /**
     * 书籍列表展示
     * @return
     */
    List<Book> listBook();

    /**
     * id查
     * @param book_id
     * @return
     */
    Book getBook(Long book_id);

    /**
     * 书名，查
     * @param book_title
     * @return
     */
    Book getBookByTitle(String book_title);

    /**
     * 模糊查询
     * @param book
     * @return
     */
    List<Book> listBookByTitleOrClassify(Book book);

    /**
     * 新增保存
     * @param book
     * @return
     */
    int saveBook(Book book);

    /**
     * 更改
     * @param book
     * @return
     */
    int updateBook(Book book);

    /**
     * 删除
     * @param book_id
     * @return
     */
    int deleteBook(Long book_id);
}
