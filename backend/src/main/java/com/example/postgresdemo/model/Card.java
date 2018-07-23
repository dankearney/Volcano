package com.example.postgresdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.repository.UserRepository;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Entity
@Table(name = "cards", indexes = {@Index(name = "card1",  columnList="name", unique = false)})
public class Card extends AuditModel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long cardId; //primary id

    @Column(name="name")
    private String cardName;
    @Column()
    private String type;
    @Column()
    private Long storyId; //wrote getters, but not setters
    @Column()
    private Long teamId; 
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
    private Long creatorId;
    @Column()
    private Long assigneeId;

    @Transient
    private User creator;

    @Transient
    private User assignee;

    //getters
    public Long getCardId() {
        return cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public Long getTeamId() {
      return teamId;
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

    public Long getStoryId() {
      return storyId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    //setters
    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setTeamId(Long teamId) {
      this.teamId = teamId;
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

    public User getCreator() {
        return creator;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User user) {
        assignee = user;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public void setCreator(User user) {
        creator = user;
    }

}
