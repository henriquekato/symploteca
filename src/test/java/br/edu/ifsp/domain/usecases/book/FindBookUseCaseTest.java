package br.edu.ifsp.domain.usecases.book;

import br.edu.ifsp.domain.entities.book.Book;
import br.edu.ifsp.domain.entities.book.BookGender;
import br.edu.ifsp.domain.entities.book.BookStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindBookUseCaseTest {
    @Mock
    BookDAO bookDAO;

    @InjectMocks
    FindBookUseCase sut;

    static Book firstBook;
    static Book secondBook;

    @BeforeAll
    static void beforeAll(){
        firstBook = new Book(1, 1, 100, "book one", "author of book one", "publisher one", "1234", BookGender.DRAMA, BookStatus.AVAILABLE);
        secondBook = new Book(2, 1, 200, "book two", "author of book two", "publisher two", "2345", BookGender.TECHNICAL, BookStatus.AVAILABLE);
    }

    @Nested
    class findById{
        @Tag("UnitTest")
        @Test
        @DisplayName("should return book by id")
        void shouldReturnBookById() {
            when(bookDAO.findOne(1)).thenReturn(Optional.ofNullable(firstBook));
            assertThat(sut.findOne(1)).isEqualTo(Optional.ofNullable(firstBook));
        }

        @Tag("UnitTest")
        @Test
        @DisplayName("should return empty if book doesnt exist by id")
        void shouldReturnEmptyIfBookDoesntExistById() {
            when(bookDAO.findOne(3)).thenReturn(Optional.empty());
            assertThat(sut.findOne(3)).isEqualTo(Optional.empty());
        }

        @Tag("UnitTest")
        @Test
        @DisplayName("should throw an exception if id is null")
        void shouldThrowAnExceptionIfIdIsNull() {
            assertThatThrownBy(()->sut.findOne(null)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class findByIsbn{
        @Tag("UnitTest")
        @Test
        @DisplayName("should return book by isbn")
        void shouldReturnBookByIsbn() {
            when(bookDAO.findByIsnb(firstBook.getIsbn())).thenReturn(Optional.ofNullable(firstBook));
            assertThat(sut.findOneByIsbn(firstBook.getIsbn())).isEqualTo(Optional.ofNullable(firstBook));
        }

        @Tag("UnitTest")
        @Test
        @DisplayName("should empty if book doesnt exist by isbn")
        void shouldEmptyIfBookDoesntExistByIsbn() {
            when(bookDAO.findByIsnb(anyString())).thenReturn(Optional.empty());
            assertThat(sut.findOneByIsbn(firstBook.getIsbn())).isEqualTo(Optional.empty());
        }

        @Tag("UnitTest")
        @Test
        @DisplayName("should throw an exception if isbn is null")
        void shouldThrowAnExceptionIfIsbnIsNull() {
            assertThatThrownBy(()->sut.findOneByIsbn(null)).isInstanceOf(IllegalArgumentException.class);
        }

        @Tag("UnitTest")
        @Test
        @DisplayName("should throw an exception if isbn is empty")
        void shouldThrowAnExceptionIfIsbnIsEmpty() {
            assertThatThrownBy(()->sut.findOneByIsbn("")).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class findAll{
        @Tag("UnitTest")
        @Test
        @DisplayName("should return all registered books")
        void shouldReturnAllRegisteredBooks() {
            when(bookDAO.findAll()).thenReturn(List.of(firstBook, secondBook));
            assertThat(sut.findAll()).isEqualTo(List.of(firstBook, secondBook));
        }

        @Tag("UnitTest")
        @Test
        @DisplayName("should return an empty list if there are no books registered")
        void shouldReturnAnEmptyListIfThereAreNoBooksRegistered() {
            when(bookDAO.findAll()).thenReturn(Collections.emptyList());
            assertThat(sut.findAll()).isEqualTo(Collections.emptyList());
        }
    }
}
