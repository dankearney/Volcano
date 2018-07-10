package com.example.postgresdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "stories", indexes = {@Index(name = "story1",  columnList="name", unique = true)})
public class Story extends AuditModel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long storyId; //primary key

    @Column()
    private Integer number;

    @Column(name="name")
    private String storyName;

    @Column()
    private String type;

    @Column()
    private Long teamId;

    @Column()
    private Long chatId;

    @Column()
    private String priority;

    @Column()
    private String label;

    @Column()
    private String message;

    @Column()
    private String[] status;

    @Column()
    private String resolution;

    @Column()
    private String description;

    @Column()
    private String attachment;

    @Column()
    private Long creatorId; //wrote getter, not setter

    @Column()
    private String creatorNameSnapshot;

    @Transient
    private ArrayList<Card> cardsAttached;

    //getters
    public Long getStoryId() {
        return storyId;
    }

    public Long getCreatorId() {
        return this.creatorId;
    }

    public Long getChatId() {
      return this.chatId;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCreatorNameSnapshot() {
        return this.creatorNameSnapshot;
    }

    public Long getTeamId() {
      return teamId;
    }

    public Integer getNumber() {
      return number;
    }

    public String getStoryName() {
        return storyName;
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

    public ArrayList<Card> getCardsAttached() {
      return cardsAttached;
    }

    //setters

    public void setStoryId(Long storyId) {
        this.storyId = storyId;
    }

    public void setNumber(Integer number) {
      this.number = number;
    }

    public void setChatId(Long id) {
        this.chatId = chatId;
    }

    public void setCreatorId(Long id) {
        this.creatorId = id;
    }

    public void setCreatorNameSnapshot(String name) {
        this.creatorNameSnapshot = name;
    }

    public void setStoryName(String name) {
        this.storyName = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
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

    public void setCardsAttached(ArrayList<Card> cards) {
      this.cardsAttached = cards;
    }
}
