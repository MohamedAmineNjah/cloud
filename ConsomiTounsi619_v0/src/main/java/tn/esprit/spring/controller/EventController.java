package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.EventCategory;
import tn.esprit.spring.sevice.interfece.IEventService;

@RestController
public class EventController {
	@Autowired
	IEventService ES;
	
	/**********************************Admin**********************************/
	@PostMapping("/add-Event")
	@ResponseBody
	public Event addEvent(@RequestBody Event ev) {
		Event event = ES.addEvent(ev);
		return event;
	}
	
	@GetMapping("/retrieve-all-Events")
	public List<Event> getEvents(){
		List<Event> list = ES.eventsLists();
		return list;
	}
	
	//My Id
	@GetMapping("/myId")
	public Long getMyId() {
		return UserController.USERCONNECTED.getId();
		}
	
	@GetMapping("/retrieve-Event-ById/{id}")
	public Event getEventById(@PathVariable long id) {
		return ES.findbyId(id);
		}
	
	@GetMapping("/retrieve-Event-ByName/{name}")
	public Event getEventByName(@PathVariable String name) {
		final Event ev = ES.findEventByName(name);
		return ev;
		}
	
	@GetMapping("/retrieve-Events-ByCategory/{category}")
	public List<Event> findEventByCategory(@PathVariable EventCategory category) {
		List<Event> list = ES.filterEvent(category);
		return list;
		}
	
	@PutMapping("/update-Event/{id}")
	@ResponseBody
	public Event updateEvent(@PathVariable Long id) {
		Event event = new Event();
		return ES.updateEvent(id, event);
	}
	
	@DeleteMapping("/delete-Event/{event-id}")
	@ResponseBody
	public void deleteEvent(@PathVariable("event-id") Long eventID) {
		ES.deleteEvent(eventID);
	}
	/**********************************User**********************************/
//	@PostMapping("/add-Participation/{eid}")
//	@ResponseBody
//	public void addParticipation(@PathVariable("eid") Long eid) {
//		Long uid = UserController.USERCONNECTED.getId();
//		ES.addParticipation(uid, eid);
//	}
	
}
