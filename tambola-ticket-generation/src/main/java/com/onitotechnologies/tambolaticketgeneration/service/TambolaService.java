package com.onitotechnologies.tambolaticketgeneration.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onitotechnologies.tambolaticketgeneration.dto.TicketEntity;
import com.onitotechnologies.tambolaticketgeneration.repository.TicketRepository;

@Service
public class TambolaService {
	  @Autowired
	    private TicketRepository ticketRepository;

	    public Map<String, List<List<Integer>>> generateTambolaSets(int n) {
	        Map<String, List<List<Integer>>> ticketsMap = new HashMap<>();

	        for (int i = 1; i <= n; i++) {
	            List<List<Integer>> tambolaSet = generateTambolaSet();
	            ticketsMap.put(String.valueOf(i), tambolaSet);
	            saveTambolaSetToDatabase(i, tambolaSet);
	        }

	        return ticketsMap;
	    }

	    private List<List<Integer>> generateTambolaSet() {
	        List<List<Integer>> tambolaSet = new ArrayList<>();
	        for (int i = 0; i < 6; i++) {
	            tambolaSet.add(generateTicket());
	        }
	        return tambolaSet;
	    }

	    private List<Integer> generateTicket() {
	        List<Integer> ticket = new ArrayList<>();
	        List<Integer> numbers = new ArrayList<>();
	        for (int i = 1; i <= 90; i++) {
	            numbers.add(i);
	        }
	        Collections.shuffle(numbers);

	        for (int i = 0; i < 3; i++) { // Generate 3 rows
	            int start = i * 10 + 1;
	            int end = start + 9;
	            List<Integer> row = new ArrayList<>();
	            for (int j = start; j <= end; j++) {
	                if (numbers.isEmpty()) {
	                    row.add(0); // If all numbers used, add 0 to the ticket
	                } else {
	                    row.add(numbers.remove(0));
	                }
	            }
	            ticket.addAll(row); // Append the row to the ticket
	        }
	        return ticket;
	    }

	    private void saveTambolaSetToDatabase(int setId, List<List<Integer>> tambolaSet) {
	        for (List<Integer> ticket : tambolaSet) {
	            TicketEntity ticketEntity = new TicketEntity();
	            ticketEntity.setSetId(setId);
	            ticketEntity.setTicket(ticket.toString());
	            ticketRepository.save(ticketEntity);
	        }
	    }

	    public List<TicketEntity> getAllTickets() {
	        return ticketRepository.findAll();
	    }
}
