package br.edu.ifsp.domain.usecases.book;

import br.edu.ifsp.domain.entities.book.Book;
import br.edu.ifsp.domain.entities.book.BookGender;
import br.edu.ifsp.domain.entities.book.BookStatus;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateBookUseCaseTest {
    @Mock
    BookDAO bookDAO;

    @InjectMocks
    UpdateBookUseCase sut;

    static Book createdBook;
    static Book updatedBook;

    @BeforeAll
    static void beforeAll() {
        createdBook = new Book(1, 1, 100, "book one", "author of book one", "publisher one", "1234", BookGender.DRAMA, BookStatus.AVAILABLE);
        updatedBook = new Book(1, 2, 200, "book two", "author of book one", "publisher one", "1234", BookGender.DRAMA, BookStatus.AVAILABLE);
    }

    @Tag("UnitTest")
    @Test
    @DisplayName("should update book")
    void shouldUpdateBook() {
        when(bookDAO.findOne(createdBook.getId())).thenReturn(Optional.ofNullable(createdBook));
        when(bookDAO.update(updatedBook)).thenReturn(true);
        assertThat(sut.update(updatedBook)).isTrue();
    }

    @Tag("UnitTest")
    @Test
    @DisplayName("should throw exception if book doesnt exist")
    void shouldThrowExceptionIfBookDoesntExist() {
        when(bookDAO.findOne(createdBook.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(()->sut.update(updatedBook)).isInstanceOf(EntityNotFoundException.class);
    }
}
