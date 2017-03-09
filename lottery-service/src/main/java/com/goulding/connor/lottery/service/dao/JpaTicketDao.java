package com.goulding.connor.lottery.service.dao;

import com.goulding.connor.lottery.service.entity.LineEntity;
import com.goulding.connor.lottery.service.entity.TicketEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

/**
 * @author Connor Goulding
 */
public class JpaTicketDao implements TicketDao {

    private EntityManager em;

    public JpaTicketDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<TicketEntity> readTickets() {
        final TypedQuery<TicketEntity> query = em.createQuery(
                "select t from TicketEntity t", TicketEntity.class);
        return query.getResultList();
    }

    @Override
    public TicketEntity createTicket(List<LineEntity> lines) {
        TicketEntity ticketEntity = new TicketEntity(UUID.randomUUID().toString(), lines, null);
        em.persist(ticketEntity);
        return ticketEntity;
    }

    @Override
    public TicketEntity readTicket(String ticketUuid) {
        return em.find(TicketEntity.class, ticketUuid);
    }

    @Override
    public TicketEntity updateTicket(TicketEntity ticket) {
        return em.merge(ticket);
    }
}
