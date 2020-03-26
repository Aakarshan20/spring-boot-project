package com.it.bootlauch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


/**
 * @author User
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="article")
@EntityListeners(AuditingEntityListener.class)
public class Article {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String author;

    private String title;

    @Column(length = 512)
    private String content;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(name = "updated_time")
    private Date updatedTime;

    //private List<Reader> reader;


}
