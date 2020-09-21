package com.rj.service;

import com.rj.entity.Incident;

public interface IncidentService {
 Iterable<Incident> listIncidents();
 void saveIncident(Incident incident);
 Incident getIncident(long id);
 void deleteIncident(long id);
}
