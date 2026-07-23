package app.yashkumar.LibraryManagement.service;
import app.yashkumar.LibraryManagement.entity.Book;
import app.yashkumar.LibraryManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public String addBook(Book book) {
        try{
            bookRepository.save(book);
            return "Book Added Successfully";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public Book getBookById(int id){
        try{
            return bookRepository.findById(id).orElse(new Book());
        }catch (Exception e){
            return new Book();
        }
    }

    public String deleteBookById(int id){
        try{
            bookRepository.deleteById(id);
            return "Book Deleted Successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String updateBookById(Book book){
        try{
            bookRepository.save(book);
            return "Book Updated Successfully";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}