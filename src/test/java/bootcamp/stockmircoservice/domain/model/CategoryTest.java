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
    @Test
    void setName_withValidName_setsName() {
        Category category = new Category();
        category.setName("Valid Name");

        assertEquals("Valid Name", category.getName());
    }

    @Test
    void setName_withNullName_setsNameToNull() {
        Category category = new Category();
        category.setName(null);

        assertNull(category.getName());
    }

    @Test
    void setDescription_withValidDescription_setsDescription() {
        Category category = new Category();
        category.setDescription("Valid Description");

        assertEquals("Valid Description", category.getDescription());
    }

    @Test
    void setDescription_withNullDescription_setsDescriptionToNull() {
        Category category = new Category();
        category.setDescription(null);

        assertNull(category.getDescription());
    }

    @Test
    void setId_withValidId_setsId() {
        Category category = new Category();
        category.setId(1L);

        assertEquals(1L, category.getId());
    }

    @Test
    void setId_withNullId_setsIdToNull() {
        Category category = new Category();
        category.setId(null);

        assertNull(category.getId());
    }
}