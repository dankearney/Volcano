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
@Table(name = "chat")
public class Chat extends AuditModel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long chatId; //primary id

    @Column()
    private String message;
    @Column()
    private Long creatorId;
    @Column()
    private String creatorNameSnapshot; // Let's just dump the username here for perf

    public void setCreatorNameSnapshot(String name) {
        this.creatorNameSnapshot = name;
    }

    public void setCreatorId(Long userId) {
        this.creatorId = creatorId;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCreatorNameSnapshot() {
        return this.creatorNameSnapshot;
    }

}