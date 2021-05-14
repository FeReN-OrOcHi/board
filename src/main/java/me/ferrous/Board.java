package me.ferrous;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue
    private Integer index;

    @NotNull(message = "Title can not be null")
    @Size(min = 1, message = "Title length must be least 1 long")
    private String title;

    @NotNull(message = "Content can not be Null")
    @Size(min = 1, message = "Content length must be least 1 long")
    private String content;

    private Date creationDate;

    private Date updateDate;

    @PrePersist
    public void creationDate() {
        this.creationDate = new Date();
        this.updateDate = new Date();
    }

    @Builder
    public Board(int index, String title, String content, Date creationDate, Date updateDate) {
        this.index = index;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }
}
