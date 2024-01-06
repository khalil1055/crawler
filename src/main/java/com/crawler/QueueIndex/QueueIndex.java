package com.crawler.QueueIndex;


import jakarta.persistence.*;

@Entity(name = "queue_index")
public class QueueIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long ndx;

    public QueueIndex() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNdx() {
        return ndx;
    }

    public void setNdx(Long ndx) {
        this.ndx = ndx;
    }

    @Override
    public String toString() {
        return "QueueIndex{" +
                "id=" + id +
                ", ndx='" + ndx + '\'' +
                '}';
    }
}
