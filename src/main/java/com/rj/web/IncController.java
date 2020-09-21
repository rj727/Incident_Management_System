package com.rj.web;

import com.rj.entity.Application;
import com.rj.entity.Incident;
import com.rj.entity.Release;
import com.rj.service.ApplicationService;
import com.rj.service.IncidentService;
import com.rj.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Controller
public class IncController {
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private IncidentService incidentService;
    @Autowired
    private ReleaseService releaseService;

    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    public void setIncidentService(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    public void setReleaseService(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @GetMapping("/applications")
    public String retrieveApplications(Model model)
    {
        model.addAttribute("applications",applicationService.listApplications());
        return "applications";
    }

    @RequestMapping("/applications/new")
    public String showNewApplicationPage(Model model) {
        Application application=new Application();
        model.addAttribute("application",application);
        return "new_application";
    }
    @RequestMapping(value = "/saveApplication", method = RequestMethod.POST)
    public String saveApplication(@ModelAttribute("application") Application application) {
        applicationService.saveApplication(application);

        return "redirect:/";
    }

    @RequestMapping("/applications/edit/{id}")
    public ModelAndView showEditApplicationPage(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("edit_application");
        Application application = applicationService.getApplication(id);
        mav.addObject("application", application);

        return mav;
    }
    @RequestMapping("/applications/delete/{id}")
    public String deleteApplication(@PathVariable(name = "id") long id) {
        applicationService.deleteApplication(id);
        return "redirect:/";
    }




    @GetMapping("/incidents")
    public String retrieveIncidents(Model model)
    {
        try
        {
            model.addAttribute("incidents",incidentService.listIncidents());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "incidents";
    }

    @RequestMapping("/incidents/new")
    public String showNewIncidentPage(Model model)
    {
        try
        {
            Incident incident=new Incident();

            List<Application> applicationList=new LinkedList<>();
            List<Release> releaseList=new LinkedList<>();
            for(Application a:applicationService.listApplications())
            {
                applicationList.add(a);
            }

            for(Release r:releaseService.listReleases())
            {
                releaseList.add(r);
            }
            model.addAttribute("incident",incident);
            model.addAttribute("applications",applicationList);
            model.addAttribute("releases",releaseList);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "new_incident";
    }
    @RequestMapping(value = "/saveIncident", method = RequestMethod.POST)
    public String saveIncident(@ModelAttribute("incident") Incident incident) {
        try
        {
            incidentService.saveIncident(incident);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return "redirect:/";
    }

    @RequestMapping("/incidents/edit/{id}")
    public ModelAndView showEditIncidentPage(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("edit_incident");
        Incident incident = incidentService.getIncident(id);
        mav.addObject("incident", incident);
        List<Application> applications=new LinkedList<>();
        for(Application a: applicationService.listApplications()){
            applications.add(a);

        }
        mav.addObject("applications",applications);

        return mav;
    }
    @RequestMapping("/incidents/delete/{id}")
    public String deleteIncident(@PathVariable(name = "id") long id) {
        incidentService.deleteIncident(id);
        return "redirect:/";
    }




    @GetMapping("/releases")
    public String retrieveReleases(Model model)
    {
        model.addAttribute("releases",releaseService.listReleases());
        return "releases";
    }
    @RequestMapping("/releases/new")
    public String showNewReleasePage(Model model) {
        Release release=new Release();
        model.addAttribute("release",release);
        return "new_release";
    }
    @RequestMapping(value = "/saveRelease", method = RequestMethod.POST)
    public String saveRelease(@ModelAttribute("release") Release release) {
        releaseService.saveRelease(release);

        return "redirect:/";
    }

    @RequestMapping("/releases/edit/{id}")
    public ModelAndView showEditReleasePage(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("edit_release");
        Release release = releaseService.getRelease(id);
        mav.addObject("release", release);

        return mav;
    }
    @RequestMapping("/releases/delete/{id}")
    public String deleteRelease(@PathVariable(name = "id") long id) {
        releaseService.deleteRelease(id);
        return "redirect:/";
    }

}
