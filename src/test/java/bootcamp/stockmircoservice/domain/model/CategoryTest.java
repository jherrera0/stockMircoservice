package bootcamp.stockmircoservice.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void getId_ShouldReturnId() {
        Category category = new Category();
        category.setId(1L);

        assertEquals(1L, category.getId());
    }

    @Test
    void getName_ShouldReturnName() {
        Category category = new Category();
        category.setName("TestName");

        assertEquals("TestName", category.getName());
    }

    @Test
    void getDescription_ShouldReturnDescription() {
        Category category = new Category();
        category.setDescription("TestDescription");

        assertEquals("TestDescription", category.getDescription());
    }
    @Test
    void constructorWithNameAndDescription_ShouldInitializeFields() {
        Category category = new Category("TestName", "TestDescription");

        assertEquals("TestName", category.getName());
        assertEquals("TestDescription", category.getDescription());
    }

    @Test
    void constructorWithIdNameAndDescription_ShouldInitializeFields() {
        Category category = new Category(1L, "TestName", "TestDescription");

        assertEquals(1, category.getId());
        assertEquals("TestName", category.getName());
        assertEquals("TestDescription", category.getDescription());
    }
}