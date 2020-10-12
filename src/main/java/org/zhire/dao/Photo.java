package org.zhire.dao;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "my_photo",indexes = {
        @Index(name = "p_id", columnList = "id")})
@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
public class Photo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "url", nullable = true, columnDefinition = "varchar(255) default ''" + " COMMENT '图片'")
    private String url;

    @Column(name = "thumb_url", nullable = true, columnDefinition = "varchar(255) default ''" + " COMMENT '缩略图'")
    private String thumbUrl;

    @Column(name = "title", nullable = true, columnDefinition = "varchar(255) default ''" + " COMMENT '标题'")
    private String title;

    @Column(name = "class_id", nullable = true, columnDefinition = "bigint default 0" + " COMMENT '分类ID'")
    private Long classId;

    @CreatedDate
    @Column(name = "ctime", nullable = true, columnDefinition = "bigint default 0")
    private Long ctime;



}
