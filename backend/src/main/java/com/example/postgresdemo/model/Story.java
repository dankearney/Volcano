package com.example.postgresdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;

@Entity
@Table(name = "stories", indexes = {@Index(name = "story1",  columnList="title", unique = true)})
public class Story extends AuditModel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long storyId; //primary key

    @Column()
    private Integer number;
    @Column(name="title")
    private String title;
    @Column()
    private String type;
    @Column()
    private String priority;
    @Column()
    private String label;
    @Column()
    private String[] status;
    @Column()
    private String resolution;
    @Column()
    private String description;
    @Column()
    private String attachment;
    @Column()
    private Long userId; //wrote getter, not setter
    @Column()
    private Long teamId; //wrote getter, not setter


    //getters
    public Long getStoryId() {
        return storyId;
    }

    public Integer getNumber() {
      return number;
    }

    public String getStoryTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getPriority() {
        return priority;
    }

    public String getLabel() {
        return label;
    }

    public String[] getStatus() {
        return status;
    }

    public String getResolution() {
        return resolution;
    }

    public String getDescription() {
        return description;
    }

    public String getAttachment() {
        return attachment;
    }

    public Long getUserId() {
      return userId;
    }

    public Long getTeamId() {
      return teamId;
    }

    //setters
    public void setStoryId(Long storyId) {
        this.storyId = storyId;
    }

    public void setNumber(Integer number) {
      this.number = number;
    }

    public void setStoryTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
