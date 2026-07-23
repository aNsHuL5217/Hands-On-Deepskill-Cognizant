package app.vercel.yashkumar;

import app.vercel.ayannadaf.repository.BookRepository;
import app.vercel.ayannadaf.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = context.getBean(BookService.class);
        BookRepository bookRepository = context.getBean(BookRepository.class);
    }
}
