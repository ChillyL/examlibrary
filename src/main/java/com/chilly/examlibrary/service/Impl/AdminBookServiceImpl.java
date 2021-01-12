package com.chilly.examlibrary.service.Impl;

import com.chilly.examlibrary.entity.Book;
import com.chilly.examlibrary.mapper.AdminBookMapper;
import com.chilly.examlibrary.service.AdminBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/22
 */

@Service
public class AdminBookServiceImpl implements AdminBookService {

    @Resource
    private AdminBookMapper adminBookMapper;

    @Override
    public List<Book> listBook() {
        return adminBookMapper.listBook();
    }

    @Override
    public Book getBook(Long book_id) {
        return adminBookMapper.getBook(book_id);
    }

    @Override
    public Book getBookByTitle(String book_title) {
        return adminBookMapper.getBookByTitle(book_title);
    }

    @Override
    public List<Book> listBookByTitleOrClassify(Book book) {
        return adminBookMapper.listBookByTitleOrClassify(book);
    }

    @Override
    public int saveBook(Book book) {
        return adminBookMapper.saveBook(book);
    }

    @Override
    public int updateBook(Book book) {
        return adminBookMapper.updateBook(book);
    }

    @Override
    public int deleteBook(Long book_id) {

        Book book = getBook(book_id);
        File file = new File(book.getBook_data());//根据指定的文件名创建File对象

        if (  file.exists() && file.isFile() ){ //要删除的文件存在且是文件

            if ( file.delete()){
                return adminBookMapper.deleteBook(book_id);
            }else{
                return 0;
            }
        }else{
            return adminBookMapper.deleteBook(book_id);
        }
    }
}
