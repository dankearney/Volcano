package com.example.postgresdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;

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
    private Long userId; //wrote getters, but not setters


    //getters
    public Long getCardId() {
        return cardId;
    }

    public String getCardName() {
        return cardName;
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

    public Long getUserId() {
        return userId;
    }

    //setters
    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
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
