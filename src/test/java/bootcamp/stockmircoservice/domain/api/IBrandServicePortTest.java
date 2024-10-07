package bootcamp.stockmircoservice.domain.api;

import bootcamp.stockmircoservice.domain.model.Brand;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IBrandServicePortTest {
    @Test
    void saveBrand_ShouldSaveBrandSuccessfully() {
        IBrandServicePort brandServicePort = mock(IBrandServicePort.class);
        Brand brand = new Brand();

        brandServicePort.saveBrand(brand);

        verify(brandServicePort, times(1)).saveBrand(brand);
    }

}