package bootcamp.stockmircoservice.adapters.driving.http.dto.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PageCustomResponseTest {
    @Test
    void constructor_withValidParameters_initializesFieldsCorrectly() {
        List<Object> items = List.of(new Object());
        PageCustomResponse<Object> pageCustomResponse = new PageCustomResponse<>(1, 10, 2, items);
        assertEquals(1, pageCustomResponse.getCurrentPage());
        assertEquals(10, pageCustomResponse.getPageSize());
        assertEquals(2, pageCustomResponse.getTotalPages());
        assertEquals(items, pageCustomResponse.getItems());
    }

    @Test
    void constructor_withNoParameters_initializesFieldsToNull() {
        PageCustomResponse<Object> pageCustomResponse = new PageCustomResponse<>();
        assertNull(pageCustomResponse.getCurrentPage());
        assertNull(pageCustomResponse.getPageSize());
        assertNull(pageCustomResponse.getTotalPages());
        assertNull(pageCustomResponse.getItems());
    }

    @Test
    void setCurrentPage_withValidValue_setsCurrentPageCorrectly() {
        PageCustomResponse<Object> pageCustomResponse = new PageCustomResponse<>();
        pageCustomResponse.setCurrentPage(3);
        assertEquals(3, pageCustomResponse.getCurrentPage());
    }


    @Test
    void setPageSize_withValidValue_setsPageSizeCorrectly() {
        PageCustomResponse<Object> pageCustomResponse = new PageCustomResponse<>();
        pageCustomResponse.setPageSize(20);
        assertEquals(20, pageCustomResponse.getPageSize());
    }


    @Test
    void setTotalPages_withValidValue_setsTotalPagesCorrectly() {
        PageCustomResponse<Object> pageCustomResponse = new PageCustomResponse<>();
        pageCustomResponse.setTotalPages(5);
        assertEquals(5, pageCustomResponse.getTotalPages());
    }


    @Test
    void setItems_withNonNullList_setsItemsCorrectly() {
        PageCustomResponse<Object> pageCustomResponse = new PageCustomResponse<>();
        List<Object> items = List.of(new Object());
        pageCustomResponse.setItems(items);
        assertEquals(items, pageCustomResponse.getItems());
    }
    
}