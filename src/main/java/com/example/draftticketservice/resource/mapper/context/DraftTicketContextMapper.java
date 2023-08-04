package com.example.draftticketservice.resource.mapper.context;

import com.example.draftticketservice.context.DraftTicketContext;
import com.example.draftticketservice.context.TicketCalculationData;
import com.example.draftticketservice.exception.MissingDataException;
import com.example.draftticketservice.resource.model.request.DraftTicketRequest;
import com.example.draftticketservice.resource.model.request.PassengerInformation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DraftTicketContextMapper {

    private final TicketCalculationDataMapper ticketCalculationDataMapper;

    public DraftTicketContextMapper(TicketCalculationDataMapper ticketCalculationDataMapper) {
        this.ticketCalculationDataMapper = ticketCalculationDataMapper;
    }

    public DraftTicketContext toContext(DraftTicketRequest request) {
        if (request == null) {
            throw new MissingDataException("Missing request data");
        }

        return DraftTicketContext.builder()
                .destination(request.destination())
                .calculationDataList(mapToCalculationDataList(request.passengers()))
                .build();
    }

    private List<TicketCalculationData> mapToCalculationDataList(List<PassengerInformation> passengerInformationList) {
        return passengerInformationList.stream()
                .map(ticketCalculationDataMapper::toContext)
                .toList();
    }
}
