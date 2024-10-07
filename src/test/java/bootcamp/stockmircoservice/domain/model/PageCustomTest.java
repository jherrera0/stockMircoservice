package bootcamp.stockmircoservice.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PageCustomTest {
    @Test
    void isEmpty_withEmptyItemsList_returnsTrue() {
        PageCustom<Object> pageCustom = new PageCustom<>(1, 10, 1, Collections.emptyList());
        assertTrue(pageCustom.isEmpty());
    }
    @Test
    void isEmpty_withNonEmptyItemsList_returnsFalse() {
        PageCustom<Object> pageCustom = new PageCustom<>(1, 10, 1, List.of(new Object()));
        assertFalse(pageCustom.isEmpty());
    }
    @Test
    void getItems_withNullItemsList_returnsNull() {
        PageCustom<Object> pageCustom = new PageCustom<>(1, 10, 1, null);
        assertNull(pageCustom.getItems());
    }
    @Test
    void setItems_withValidList_setsItemsCorrectly() {
        PageCustom<Object> pageCustom = new PageCustom<>();
        List<Object> items = List.of(new Object());
        pageCustom.setItems(items);
        assertEquals(items, pageCustom.getItems());
    }
    @Test
    void setItems_withNullList_setsItemsToNull() {
        PageCustom<Object> pageCustom = new PageCustom<>();
        pageCustom.setItems(null);
        assertNull(pageCustom.getItems());
    }
    @Test
    void constructor_withValidParameters_initializesFieldsCorrectly() {
        List<Object> items = List.of(new Object());
        PageCustom<Object> pageCustom = new PageCustom<>(1, 10, 2, items);
        assertEquals(1, pageCustom.getCurrentPage());
        assertEquals(10, pageCustom.getPageSize());
        assertEquals(2, pageCustom.getTotalPages());
        assertEquals(items, pageCustom.getItems());
    }

    @Test
    void constructor_withNoParameters_initializesFieldsToNull() {
        PageCustom<Object> pageCustom = new PageCustom<>();
        assertNull(pageCustom.getCurrentPage());
        assertNull(pageCustom.getPageSize());
        assertNull(pageCustom.getTotalPages());
        assertNull(pageCustom.getItems());
    }
    @Test
    void setTotalPages_withValidValue_setsTotalPagesCorrectly() {
        PageCustom<Object> pageCustom = new PageCustom<>();
        pageCustom.setTotalPages(5);
        assertEquals(5, pageCustom.getTotalPages());
    }
    @Test
    void setCurrentPage_withValidValue_setsCurrentPageCorrectly() {
        PageCustom<Object> pageCustom = new PageCustom<>();
        pageCustom.setCurrentPage(3);
        assertEquals(3, pageCustom.getCurrentPage());
    }
    @Test
    void setPageSize_withValidValue_setsPageSizeCorrectly() {
        PageCustom<Object> pageCustom = new PageCustom<>();
        pageCustom.setPageSize(20);
        assertEquals(20, pageCustom.getPageSize());
    }
}