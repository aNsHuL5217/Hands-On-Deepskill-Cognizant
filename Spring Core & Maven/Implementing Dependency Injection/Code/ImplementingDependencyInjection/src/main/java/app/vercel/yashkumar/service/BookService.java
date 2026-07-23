package app.vercel.ayannadaf.service;

import app.vercel.ayannadaf.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    public BookService() {
        System.out.println("BookService Created");
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void printBookRepository() {
        System.out.println(bookRepository);
    }
}
