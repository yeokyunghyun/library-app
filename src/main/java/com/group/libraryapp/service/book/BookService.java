package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;

    public BookService(BookRepository bookRepository,UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    //
    @Transactional
    public void loanBook(BookLoanRequest request) { //userName과 bookName을 받아오고있는상태

        Book book = bookRepository.findByName(request.getBookName()).orElseThrow(IllegalArgumentException::new);
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(request.getBookName(),false)) throw new IllegalArgumentException();

        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
        user.save(book.getName());
    }


    @Transactional
    public void returnBook(BookReturnRequest request) {
        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
        user.doReturn(request.getBookName());
    }
    //책 대출과 책 반납
}
