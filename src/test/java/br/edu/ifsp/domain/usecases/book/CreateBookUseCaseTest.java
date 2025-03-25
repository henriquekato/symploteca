package br.edu.ifsp.domain.usecases.book;

import br.edu.ifsp.domain.entities.book.Book;
import br.edu.ifsp.domain.entities.book.BookGender;
import br.edu.ifsp.domain.entities.book.BookStatus;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateBookUseCaseTest {
    @Mock
    BookDAO bookDAO;

    @InjectMocks
    CreateBookUseCase sut;

    static Book firstBook;
    static Book secondBook;

    @BeforeAll
    static void beforeAll(){
        firstBook = new Book(1, 100, "book one", "author of book one", "publisher one", "1234", BookGender.DRAMA, BookStatus.AVAILABLE);
        secondBook = new Book(1, 200, "book two", "author of book two", "publisher two", "1234", BookGender.TECHNICAL, BookStatus.AVAILABLE);
    }

    @Tag("UnitTest")
    @Test
    @DisplayName("should create a new book")
    void shouldCreateANewBook() {
        when(bookDAO.findByIsnb(anyString())).thenReturn(Optional.empty());
        when(bookDAO.create(firstBook)).thenReturn(1);
        assertThat(sut.insert(firstBook)).isEqualTo(1);
    }

    @Tag("UnitTest")
    @Test
    @DisplayName("should throw an exception if isnb was already registered")
    void shouldThrowAnExceptionIfIsnbWasAlreadyRegistered() {
        when(bookDAO.findByIsnb("1234")).thenReturn(Optional.ofNullable(firstBook));
        assertThatThrownBy(()->sut.insert(secondBook)).isInstanceOf(EntityAlreadyExistsException.class);
    }

    @Tag("UnitTest")
    @Test
    @DisplayName("should create a book only one time")
    void shouldCreateABookOnlyOneTime() {
        when(bookDAO.findByIsnb(anyString())).thenReturn(Optional.empty()).thenReturn(Optional.ofNullable(firstBook));
        when(bookDAO.create(firstBook)).thenReturn(1);
        assertThat(sut.insert(firstBook)).isEqualTo(1);
        assertThatThrownBy(()->sut.insert(firstBook)).isInstanceOf(EntityAlreadyExistsException.class);
    }
}
