package com.rj.entity;

import com.sun.istack.Nullable;

import javax.persistence.*;

@Entity
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="application_id")
    private Application application;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinTable(name="incident_release",joinColumns = @JoinColumn(name="incident_fk"), inverseJoinColumns = @JoinColumn(name="release_fk"))
    private Release release;

    private String status;



    public Incident(){
        System.out.println("Test krne ka");
    }
    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", application=" + application +
                ", release=" + release +
                ", status='" + status + '\'' +
                '}';
    }

    public Incident(String title, String description, Application application, Release release, String status) {
        this.title = title;
        this.description = description;
        this.application = application;
        this.release = release;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release releaseDate) {
        this.release = release;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
