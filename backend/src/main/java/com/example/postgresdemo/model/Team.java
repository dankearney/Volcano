package com.example.postgresdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "teams", indexes = {@Index(name = "team1",  columnList="name", unique = true)})
public class Team extends AuditModel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long teamId; //primary key

    @Column(name="name")
    private String teamName;
    
    @Column
    private String password;

    @Column()
    private Long creatorId;

    @Transient
    private ArrayList<Story> storiesAttached;

    //getters
    public Long getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public Long getCreatorId() {
        return this.creatorId;
    }

    public ArrayList<Story> getStoriesAttached() {
    	return storiesAttached;
    }

    public String getPassword() {
        return password;
    }


    //setters
    public void setTeamId(Long id) {
    	this.teamId = id;
    }

    public void setTeamName(String teamName) {
    	this.teamName = teamName;
    }

    public void setCreatorId(Long creatorId) {
    	this.creatorId = creatorId;
    }

    public void setStoriesAttached(ArrayList<Story> stories) {
    	this.storiesAttached = stories;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
