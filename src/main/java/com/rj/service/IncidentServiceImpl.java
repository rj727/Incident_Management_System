package com.rj.service;

import com.rj.entity.Application;
import com.rj.entity.Incident;
import com.rj.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    @Override
    public Iterable<Incident> listIncidents() {
        return incidentRepository.findAll();
    }
    @Override
    public void saveIncident(Incident incident)
    {
        incidentRepository.save(incident);
    }

    @Override
    public Incident getIncident(long id){ return incidentRepository.findById(id).get();}

    @Override
    public void deleteIncident(long id){ incidentRepository.deleteById(id);}
}
