package com.example.library.domain.book.api;

import com.example.library.domain.RestDocsSupport;
import com.example.library.domain.book.domain.BookEntity;
import com.example.library.domain.book.enums.BookState;
import com.example.library.domain.book.service.BookService;
import com.example.library.domain.rent.api.RentController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BookController.class)
class BookControllerTest extends RestDocsSupport {

    @MockBean
    private BookService bookService;

    @Test
    @WithMockUser(username = "테스트_최고관리자", authorities = {"0"}) //권한 부여
    void 도서정보조회() throws Exception {
        //given
        BookEntity bookEntity = BookEntity.builder()
                .bookCode(1L)
                .bookAuthor("author")
                .bookContent("content")
                .bookImage("asd")
                .bookLocation("A1")
                .bookState(BookState.RENT_UNAVAILABLE)
                .bookPublisher("pub")
                .isbn("1234")
                .bookName("도서제목")
                .build()
                ;
        when(bookService.inquiryBook(1L)).thenReturn(bookEntity);

        //when
        mockMvc.perform(get("/book/1")
                .with(csrf())
        )
        //then
        .andExpect(jsonPath("$.data.bookCode").value(1L))
        ;

    }

}