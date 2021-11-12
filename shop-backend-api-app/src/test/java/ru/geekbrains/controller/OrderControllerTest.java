package ru.geekbrains.controller;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.geekbrains.controller.dto.OrderDto;
import ru.geekbrains.controller.dto.OrderLineItemDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class OrderControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private SimpMessagingTemplate template;

    @Test
    public void testFindAllUnauthorized() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/order/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void testFindAllAuthorized() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/order/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void testFindOrdersForUsers() throws Exception {

       OrderDto order1= new OrderDto();
        OrderLineItemDto li1= new OrderLineItemDto(1L, 1L, 2L,"BigRed",  new BigDecimal("65.00"), 2);
        OrderLineItemDto li2= new OrderLineItemDto(2L, 1L, 3L,"BigYellow",  new BigDecimal("85.00"), 2);
        List<OrderLineItemDto> orderLineItems= new ArrayList<OrderLineItemDto>();
        orderLineItems.add(li1);
        orderLineItems.add(li2);
        order1.setId(1L);
        order1.setOrderDate( LocalDateTime.now());
        order1.setStatus("CREATED");
        order1.setUsername("admin");
        order1.setOrderLineItems(orderLineItems);


        OrderDto order2= new OrderDto();
        OrderLineItemDto li= new OrderLineItemDto(3L, 2L, 5L,"GoldenDragon",  new BigDecimal("55.00"), 1);
        List<OrderLineItemDto> orderLineItems1= new ArrayList<OrderLineItemDto>();
        orderLineItems.add(li);
        order2.setId(2L);
        order2.setOrderDate( LocalDateTime.now());
        order2.setStatus("CREATED");
        order2.setUsername("guest");
        order2.setOrderLineItems(orderLineItems1);

        OrderDto order3= new OrderDto();
        OrderLineItemDto li3= new OrderLineItemDto(3L, 3L, 5L,"GoldenDragon",  new BigDecimal("55.00"), 1);
        List<OrderLineItemDto> orderLineItems2= new ArrayList<OrderLineItemDto>();
        orderLineItems.add(li3);
        order3.setId(3L);
        order3.setOrderDate( LocalDateTime.now());
        order3.setStatus("CREATED");
        order3.setUsername("admin");
        order3.setOrderLineItems(orderLineItems2);

        mvc.perform(MockMvcRequestBuilders
                .get("/order/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }
}
