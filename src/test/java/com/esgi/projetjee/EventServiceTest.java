package com.esgi.projetjee;

import com.esgi.projetjee.repository.EventRepository;
import com.esgi.projetjee.domain.Event;
import com.esgi.projetjee.repository.InterestRepository;
import com.esgi.projetjee.service.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

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
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private InterestRepository interestRepository;

    @Captor
    private ArgumentCaptor<Event> captor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        eventRepository = mock(EventRepository.class);
        interestRepository = mock(InterestRepository.class);
        eventService = new EventService(eventRepository, interestRepository);
    }

    @Test
    public void should_add_event() {
        captor = ArgumentCaptor.forClass(Event.class);
        Event event = new Event("eventTest", new Date(), "Paris");

        eventService.createOrUpdateEvent(event);
        verify(eventRepository).save(captor.capture());

        Event actual = captor.getValue();

        assertThat(actual.equals(event));
    }

    @Test
    public void should_get_all_events() {
        Event event = new Event("eventTest", new Date(), "Paris");
        List<Event> storedEvents = new ArrayList<>();
        storedEvents.add(event);

        when(eventRepository.findAll()).thenReturn(storedEvents);

        List<Event> found = eventService.getEvents();

        assertThat(found.size() == 1);
        assertThat(found.get(0).getName().equals(event.getName()));
        assertThat(found.get(0).getDate().equals(event.getDate()));
        assertThat(found.get(0).getLocation().equals(event.getLocation()));
    }
}
