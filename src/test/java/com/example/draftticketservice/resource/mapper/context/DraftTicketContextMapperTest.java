package com.example.draftticketservice.resource.mapper.context;

import com.example.draftticketservice.context.DraftTicketContext;
import com.example.draftticketservice.context.TicketCalculationData;
import com.example.draftticketservice.enums.DestinationEnum;
import com.example.draftticketservice.exception.MissingDataException;
import com.example.draftticketservice.resource.model.request.DraftTicketRequest;
import com.example.draftticketservice.resource.model.request.PassengerInformation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DraftTicketContextMapperTest {

    @Mock
    private TicketCalculationDataMapper ticketCalculationDataMapper;

    @InjectMocks
    private DraftTicketContextMapper draftTicketContextMapper;

    @Test
    void testMapToResponse() {
        // Prepare
        var draftTicketRequest = createDraftTicketRequest();
        var ticketCalculationData = TicketCalculationData.builder().build();
        var expectedResult = createDraftTicketContext(ticketCalculationData);

        // Mock
        when(ticketCalculationDataMapper.toContext(draftTicketRequest.getPassengers().get(0))).thenReturn(ticketCalculationData);

        // Perform
        var result = draftTicketContextMapper.toContext(draftTicketRequest);

        // Verify
        assertThat(result)
                .isNotNull()
                .isEqualTo(expectedResult);
    }

    @Test
    void testMapToContextWhenNull_ThrowsException() {
        assertThrows(MissingDataException.class, () -> draftTicketContextMapper.toContext(null));
    }

    private DraftTicketRequest createDraftTicketRequest() {
        return DraftTicketRequest.builder()
                .destination(DestinationEnum.LONDON)
                .passengers(Collections.singletonList(PassengerInformation.builder().build()))
                .build();
    }

    private DraftTicketContext createDraftTicketContext(TicketCalculationData ticketCalculationData) {
        return DraftTicketContext.builder()
                .destination(DestinationEnum.LONDON)
                .calculationDataList(Collections.singletonList(ticketCalculationData))
                .build();
    }


}
