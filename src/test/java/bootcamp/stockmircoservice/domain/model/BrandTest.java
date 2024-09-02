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
    void setId_ShouldSetId() {
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
    void setName_ShouldSetName() {
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
    void setDescription_ShouldSetDescription() {
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
}