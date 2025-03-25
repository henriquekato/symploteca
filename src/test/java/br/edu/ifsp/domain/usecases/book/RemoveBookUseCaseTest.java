package br.edu.ifsp.domain.usecases.book;

import br.edu.ifsp.domain.entities.book.Book;
import br.edu.ifsp.domain.entities.book.BookGender;
import br.edu.ifsp.domain.entities.book.BookStatus;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RemoveBookUseCaseTest {
    @Mock
    BookDAO bookDAO;

    @InjectMocks
    RemoveBookUseCase sut;

    static Book firstBook;
    static Book secondBook;

    @BeforeAll
    static void beforeAll() {
        firstBook = new Book(1, 1, 100, "book one", "author of book one", "publisher one", "1234", BookGender.DRAMA, BookStatus.AVAILABLE);
        secondBook = new Book(2, 1, 200, "book two", "author of book two", "publisher two", "2345", BookGender.TECHNICAL, BookStatus.AVAILABLE);
    }

    @Nested
    class removeById {
        @Tag("UnitTest")
        @Test
        @DisplayName("should remove book by id")
        void shouldRemoveBookById() {
            when(bookDAO.findOne(firstBook.getId())).thenReturn(Optional.ofNullable(firstBook));
            when(bookDAO.deleteByKey(firstBook.getId())).thenReturn(true);
            assertThat(sut.remove(firstBook.getId())).isTrue();
        }

        @Tag("UnitTest")
        @Test
        @DisplayName("should throw exception if id doesnt exist")
        void shouldThrowExceptionIfIdDoesntExist() {
            when(bookDAO.findOne(firstBook.getId())).thenReturn(Optional.empty());
            assertThatThrownBy(()->sut.remove(firstBook.getId())).isInstanceOf(EntityNotFoundException.class);
        }

        @Tag("UnitTest")
        @Test
        @DisplayName("should throw exception if id is null")
        void shouldThrowExceptionIfIdIsNull() {
            assertThatThrownBy(()->sut.remove((Integer) null)).isInstanceOf(EntityNotFoundException.class);
        }
    }

    @Nested
    class removeByBook {
        @Tag("UnitTest")
        @Test
        @DisplayName("should remove book")
        void shouldRemoveBook() {
            when(bookDAO.findOne(firstBook.getId())).thenReturn(Optional.ofNullable(firstBook));
            when(bookDAO.delete(firstBook)).thenReturn(true);
            assertThat(sut.remove(firstBook)).isTrue();
        }

        @Tag("UnitTest")
        @Test
        @DisplayName("should throw exception if id doesnt exist")
        void shouldThrowExceptionIfIdDoesntExist() {
            when(bookDAO.findOne(firstBook.getId())).thenReturn(Optional.empty());
            assertThatThrownBy(()->sut.remove(firstBook)).isInstanceOf(EntityNotFoundException.class);
        }

        @Tag("UnitTest")
        @Test
        @DisplayName("should throw exception if book is null")
        void shouldThrowExceptionIfBookIsNull() {
            assertThatThrownBy(()->sut.remove((Book) null)).isInstanceOf(EntityNotFoundException.class);
        }
    }
}
