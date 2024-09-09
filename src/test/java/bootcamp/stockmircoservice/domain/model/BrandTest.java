package bootcamp.stockmircoservice.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandTest {

    @Test
    void getId_ShouldReturnId() {
        Brand brand = new Brand();
        brand.setId(1L);

        assertEquals(1L, brand.getId());
    }

    @Test
    void getName_ShouldReturnName() {
        Brand brand = new Brand();
        brand.setName("TestName");

        assertEquals("TestName", brand.getName());
    }

    @Test
    void getDescription_ShouldReturnDescription() {
        Brand brand = new Brand();
        brand.setDescription("TestDescription");

        assertEquals("TestDescription", brand.getDescription());
    }

    @Test
    void constructorWithNameAndDescription_ShouldInitializeFields() {
        Brand brand = new Brand("TestName", "TestDescription");

        assertEquals("TestName", brand.getName());
        assertEquals("TestDescription", brand.getDescription());
    }

    @Test
    void constructorWithIdNameAndDescription_ShouldInitializeFields() {
        Brand brand = new Brand(1L, "TestDescription", "TestName");

        assertEquals(1L, brand.getId());
        assertEquals("TestName", brand.getName());
        assertEquals("TestDescription", brand.getDescription());
    }
    @Test
    void setId_withValidId_setsId() {
        Brand brand = new Brand();
        brand.setId(1L);

        assertEquals(1L, brand.getId());
    }

    @Test
    void setId_withNullId_setsIdToZero() {
        Brand brand = new Brand();
        brand.setId(0L);

        assertEquals(0L, brand.getId());
    }

    @Test
    void setName_withValidName_setsName() {
        Brand brand = new Brand();
        brand.setName("Valid Name");

        assertEquals("Valid Name", brand.getName());
    }

    @Test
    void setName_withNullName_setsNameToNull() {
        Brand brand = new Brand();
        brand.setName(null);

        assertNull(brand.getName());
    }

    @Test
    void setDescription_withValidDescription_setsDescription() {
        Brand brand = new Brand();
        brand.setDescription("Valid Description");

        assertEquals("Valid Description", brand.getDescription());
    }

    @Test
    void setDescription_withNullDescription_setsDescriptionToNull() {
        Brand brand = new Brand();
        brand.setDescription(null);

        assertNull(brand.getDescription());
    }

    @Test
    void constructorWithNameAndDescription_setsFieldsCorrectly() {
        Brand brand = new Brand("TestName", "TestDescription");

        assertEquals("TestName", brand.getName());
        assertEquals("TestDescription", brand.getDescription());
    }

    @Test
    void constructorWithIdNameAndDescription_setsFieldsCorrectly() {
        Brand brand = new Brand(1L, "TestDescription", "TestName");

        assertEquals(1L, brand.getId());
        assertEquals("TestName", brand.getName());
        assertEquals("TestDescription", brand.getDescription());
    }
}