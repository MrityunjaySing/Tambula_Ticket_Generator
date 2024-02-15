package com.onitotechnologies.tambolaticketgeneration.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onitotechnologies.tambolaticketgeneration.dto.TicketEntity;

@Repository
public interface TicketRepository  extends JpaRepository<TicketEntity, Long>{
	
}
