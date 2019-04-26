package com.company.enroller.persistence;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Meeting;
import com.company.enroller.model.Participant;

@Component("meetingService")
public class MeetingService {

	DatabaseConnector connector;

	public MeetingService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<Meeting> getAll() {
//		String hql = "FROM Meeting";
//		Query query = connector.getSession().createQuery(hql);
//		return query.list();
		return connector.getSession().createCriteria(Meeting.class).list();
	}
	
	public Meeting findByID(long id)
	{
		return (Meeting) connector.getSession().get(Meeting.class, id);
	}

	public void add(Meeting meeting) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().save(meeting);
		transaction.commit();
	}

	public void delete(Meeting meeting) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().delete(meeting);
		transaction.commit();
	}
	
	public void update(Meeting meeting) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().update(meeting);
		transaction.commit();
	}

	public void addParticipantToTheMeeting(Long id, Participant participant) {
		Meeting meeting = findByID(id);
		meeting.addParticipant(participant);
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().update(meeting);
		transaction.commit();
	}

	public void removeParticipant(Long id, Participant participant) {
		Meeting meeting = findByID(id);
		meeting.removeParticipant(participant);
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().update(meeting);
		transaction.commit();
	}

}
