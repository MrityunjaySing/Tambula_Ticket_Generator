package com.onitotechnologies.tambolaticketgeneration.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onitotechnologies.tambolaticketgeneration.dto.TicketEntity;
import com.onitotechnologies.tambolaticketgeneration.service.TambolaService;

@RestController
@RequestMapping("/api/tambola")
public class TambolaController {

	 @Autowired
	    private TambolaService tambolaService;

	    @PostMapping("/generate/{n}")
	    public ResponseEntity<Map<String, List<List<Integer>>>> generateTickets(@PathVariable int n) {
	        Map<String, List<List<Integer>>> response = tambolaService.generateTambolaSets(n);
	        return ResponseEntity.ok(response);
	    }

	    @GetMapping("/tickets")
	    public ResponseEntity<List<TicketEntity>> getAllTickets() {
	        List<TicketEntity> tickets = tambolaService.getAllTickets();
	        return ResponseEntity.ok(tickets);
	    }
}


