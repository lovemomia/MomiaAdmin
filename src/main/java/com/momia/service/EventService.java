package com.momia.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.momia.entity.Event;

public interface EventService {

	public Event findEventId(int id);
	public List<Event> findEvents();
	public int delete(int id);
	public int insert(Event event);
	public int update(Event event);
	public Event formEvent(HttpServletRequest req,int falg);
	
}
