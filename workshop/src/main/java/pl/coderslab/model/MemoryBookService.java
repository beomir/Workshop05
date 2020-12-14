package pl.coderslab.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@RestController
//@EnableAutoConfiguration
//@RequestMapping("/books")
@Component
public class MemoryBookService implements BookService {

    public List<Book> books;
    private static Long nextId = 6L;

    public MemoryBookService() {
        books = new ArrayList<>();
        books.add(new Book(1L, "9788324631766", "Game of Throne", "George Martin", "Zysk", "fantasy"));
        books.add(new Book(2L, "9788324627738", "Krzyzacy", "Henryk Sienkiwicz", "TT", "fantasy"));
        books.add(new Book(3L, "9780130819338", "Pan Wołodyjowski", "Henryk Sienkiewicz", "TT", "fantasy"));
        books.add(new Book(4L, "3901283092103", "Chłopi", "Władysław Reymont", "ZNAK", "fantasy"));
        books.add(new Book(5L, "9140298320983", "Samozwaniec", "Jacek Komuda", "Fabryka Słów", "fantasy"));
    }


    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public void add(Book book) {
        book.setId(nextId++);
        books.add(book);
    }

    @Override
    public Optional<Book> get(Long id) {
        return books.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    @Override
    public void delete(Long id) {
        if (get(id).isPresent()) {
            books.remove(this.get(id).get());
        }
    }

    @Override
    public void update(Book book) {
        if (this.get(book.getId()).isPresent()) {
            int indexOf = books.indexOf(this.get(book.getId()).get());
            books.set(indexOf, book);
        }
    }


}