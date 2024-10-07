package bootcamp.stockmircoservice.infrastructure.configuration.security;

import bootcamp.stockmircoservice.infrastructure.until.JwtConst;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {

    @Mock
    private UserDetailsService myUserDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void doFilterInternal_ValidJwt_SetsAuthentication() throws ServletException, IOException {
        String jwt = "validJwt";
        String authHeader = "Bearer " + jwt;

        when(request.getHeader(JwtConst.AUTHORIZATION)).thenReturn(authHeader);
        when(myUserDetailsService.loadUserByUsername(jwt)).thenReturn(userDetails);
        when(userDetails.getAuthorities()).thenReturn(List.of());

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals(jwt, SecurityContextHolder.getContext().getAuthentication().getCredentials());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternal_ValidJwt_ThrowsException(){
        String jwt = "validJwt";
        String authHeader = "Bearer " + jwt;

        when(request.getHeader(JwtConst.AUTHORIZATION)).thenReturn(authHeader);
        when(myUserDetailsService.loadUserByUsername(jwt)).thenThrow(new RuntimeException("User not found"));

        assertThrows(RuntimeException.class, () -> jwtAuthenticationFilter.doFilterInternal(request, response, filterChain));
    }
}