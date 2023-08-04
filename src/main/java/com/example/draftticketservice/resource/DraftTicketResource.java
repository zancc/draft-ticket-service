package com.example.draftticketservice.resource;

import com.example.draftticketservice.context.DraftTicketContext;
import com.example.draftticketservice.resource.mapper.context.DraftTicketContextMapper;
import com.example.draftticketservice.resource.mapper.response.DraftTicketResponseMapper;
import com.example.draftticketservice.resource.model.request.DraftTicketRequest;
import com.example.draftticketservice.resource.model.response.DraftTicketResponse;
import com.example.draftticketservice.service.DraftTicketCalculationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/draft")
@RestController
public class DraftTicketResource {

    private final DraftTicketContextMapper contextMapper;
    private final DraftTicketResponseMapper responseMapper;
    private final DraftTicketCalculationService draftTicketCalculationService;

    public DraftTicketResource(DraftTicketContextMapper contextMapper,
                               DraftTicketResponseMapper responseMapper,
                               DraftTicketCalculationService draftTicketCalculationService) {
        this.contextMapper = contextMapper;
        this.responseMapper = responseMapper;
        this.draftTicketCalculationService = draftTicketCalculationService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DraftTicketResponse> getDraftTicketPricing(@RequestBody DraftTicketRequest draftTicketRequest) {
        DraftTicketContext context = contextMapper.toContext(draftTicketRequest);
        draftTicketCalculationService.resolveDraftPrices(context);
        DraftTicketResponse response = responseMapper.toResponse(context);

        return ResponseEntity.ok(response);
    }
}
