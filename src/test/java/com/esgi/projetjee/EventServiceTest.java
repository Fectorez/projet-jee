package com.esgi.projetjee;

import com.esgi.projetjee.domain.User;
import com.esgi.projetjee.repository.EventRepository;
import com.esgi.projetjee.domain.Event;
import com.esgi.projetjee.repository.InterestRepository;
import com.esgi.projetjee.repository.UserRepository;
import com.esgi.projetjee.service.EventService;
import com.esgi.projetjee.service.dto.EventDto;
import com.esgi.projetjee.service.dto.UserDto;
import com.esgi.projetjee.service.impl.EventServiceImpl;
import com.esgi.projetjee.service.mapper.EventMapper;
import com.esgi.projetjee.service.mapper.InterestMapper;
import com.esgi.projetjee.service.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {
  /*@TestConfiguration
        static class EventServiceContextConfiguration {
            @Bean
            public EventService eventService() {
                return new EventService();
            }
        }*/
    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private InterestRepository interestRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private InterestMapper interestMapper;

    @Mock
    private EventMapper eventMapper;

    @Mock
    private UserMapper userMapper;

    private static final String NAME = "Visite";
    private static final String USERNAME = "toto";
    private static final String PASSWORD = "toto";
    private static final Integer ID = 1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        eventRepository = mock(EventRepository.class);
        interestRepository = mock(InterestRepository.class);
        userRepository = mock(UserRepository.class);
        interestMapper = mock(InterestMapper.class);
        eventMapper = mock(EventMapper.class);
        userMapper = mock(UserMapper.class);
        eventService = new EventServiceImpl(eventRepository, interestRepository, eventMapper, interestMapper, userRepository, userMapper);
    }

    private User getUser() {
        User user = new User();
        user.setId(ID);
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        return user;
    }

    private UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(ID);
        userDto.setUsername(USERNAME);
        userDto.setPassword(PASSWORD);
        return userDto;
    }

    private Event getEvent() {
        Event event = new Event();
        event.setId(ID);
        event.setName(NAME);
        event.setUser(getUser());
        return event;
    }

    private EventDto getEventDto() {
        EventDto eventDto = new EventDto();
        eventDto.setId(ID);
        eventDto.setName(NAME);
        eventDto.setUserId(ID);
        return eventDto;
    }

    @Test
    public void should_add_event() {
        Event event = getEvent();
        EventDto eventDto = getEventDto();
        // When
        when(eventRepository.save((Event)(any()))).thenReturn(event);
        when(eventMapper.eventToEventDto((Event)(any()))).thenReturn(eventDto);
        when(eventMapper.eventDtoToEvent((EventDto)(any()))).thenReturn(event);

        // Then
        EventDto createdDto = eventService.create(eventDto);
        assertThat(createdDto).isEqualTo(eventDto);
    }

    @Test
    public void should_find_all() {
        // Given
        List<Event> storedEvents = new ArrayList<>();
        storedEvents.add(getEvent());

        // When
        when(eventRepository.findAll()).thenReturn(storedEvents);

        // Then
        assertThat(eventService.findAll().size()).isEqualTo(1);
    }
}