package com.esgi.projetjee;

import com.esgi.projetjee.domain.Interest;
import com.esgi.projetjee.repository.EventRepository;
import com.esgi.projetjee.repository.InterestRepository;
import com.esgi.projetjee.repository.UserRepository;
import com.esgi.projetjee.service.dto.InterestDto;
import com.esgi.projetjee.service.impl.InterestServiceImpl;
import com.esgi.projetjee.service.mapper.EventMapper;
import com.esgi.projetjee.service.mapper.InterestMapper;
import com.esgi.projetjee.service.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InterestServiceTest {

    @InjectMocks
    private InterestServiceImpl interestService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private InterestRepository interestRepository;

    @Mock
    private InterestMapper interestMapper;

    @Mock
    private EventMapper eventMapper;

    @Mock
    private UserMapper userMapper;

    private static final String NAME = "Jardinage";
    private static final Integer ID = 1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        eventRepository = mock(EventRepository.class);
        interestRepository = mock(InterestRepository.class);
        interestMapper = mock(InterestMapper.class);
        eventMapper = mock(EventMapper.class);
        userMapper = mock(UserMapper.class);
        interestService = new InterestServiceImpl(interestRepository, eventRepository, interestMapper, eventMapper, userMapper);
    }

    private Interest getInterest() {
        Interest interest = new Interest();
        interest.setId(ID);
        interest.setName(NAME);
        return interest;
    }

    private InterestDto getInterestDto() {
        InterestDto interestDto = new InterestDto();
        interestDto.setId(ID);
        interestDto.setName(NAME);
        return interestDto;
    }

    @Test
    public void should_add_interest() {
        // Given
        Interest interest = getInterest();
        InterestDto interestDto = getInterestDto();

        // When
        when(interestRepository.save((Interest)(any()))).thenReturn(interest);
        when(interestMapper.interestToInterestDto((Interest)(any()))).thenReturn(interestDto);
        when(interestMapper.interestDtoToInterest((InterestDto)(any()))).thenReturn(interest);

        // Then
        InterestDto createdDto = interestService.create(interestDto);
        assertThat(createdDto).isEqualTo(interestDto);
    }

    @Test
    public void should_find_all() {
        // Given
        List<Interest> storedInterests = new ArrayList<>();
        storedInterests.add(getInterest());

        // When
        when(interestRepository.findAll()).thenReturn(storedInterests);

        // Then
        assertThat(interestService.findAll().size()).isEqualTo(1);
    }
}