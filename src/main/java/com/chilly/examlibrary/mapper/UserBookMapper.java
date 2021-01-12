package com.chilly.examlibrary.mapper;

import com.chilly.examlibrary.entity.Book;
import com.chilly.examlibrary.entity.query.BookQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/23
 */
@Mapper
public interface UserBookMapper {

    /**
     * 书籍列表展示
     * @return
     */
    List<BookQuery> listBook();

    /**
     * id查名
     * @param book_id
     * @return
     */
    BookQuery getBookQuery(Long book_id);
}
