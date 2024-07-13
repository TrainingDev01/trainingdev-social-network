package com.trainingdev.td_bs_management_notification.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainingdev.td_bs_management_notification.dto.input.NotificationDetail;
import com.trainingdev.td_bs_management_notification.dto.input.NotificationRequest;
import com.trainingdev.td_bs_management_notification.exception.NotificationNotFoundException;
import com.trainingdev.td_bs_management_notification.service.NotificationService;
import com.trainingdev.td_bs_management_notification.utils.UtilData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NotificationService notificationService;

    @Value("${properties.messages.error.notifications-not-found}")
    private String notificationsNotFoundError;


    @Test
    public void createNotification_when_return_201() throws Exception {
        NotificationRequest request = UtilData.createNotificationRequest();
        NotificationDetail notificationDetail = UtilData.createNotificationDetail();
        when(notificationService.createNotification(request)).thenReturn(notificationDetail);
        mockMvc.perform(post("/api/v1/notification")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createNotification_when_return_400() throws Exception {
        NotificationRequest request = new NotificationRequest();
        mockMvc.perform(post("/api/v1/notification")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAllNotifications_when_return_200() throws Exception {
        List<NotificationDetail> notificationDetailList = List.of(UtilData.createNotificationDetail());
        when(notificationService.getAllNotifications(anyInt())).thenReturn(notificationDetailList);
        mockMvc.perform(get("/api/v1/notification/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].senderUser.id").value(notificationDetailList.get(0).getSenderUser().getId()))
                .andExpect(jsonPath("$.[0].receptorUser.id").value(notificationDetailList.get(0).getReceptorUser().getId()))
                .andExpect(jsonPath("$.[0].description").value(notificationDetailList.get(0).getDescription()));
    }

    @Test
    public void getAllNotifications_when_return_404() throws Exception {
        when(notificationService.getAllNotifications(anyInt())).thenThrow(new NotificationNotFoundException(
                String.valueOf(HttpStatus.NOT_FOUND.value()), notificationsNotFoundError));
        mockMvc.perform(get("/api/v1/notification/5"))
                .andExpect(status().isNotFound());
    }

}