package org.zerock.b01.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //primarykey로 쓰겠다(auto Increasement). identity,sequence,table,auto
    private Long bno;


    private String id;
    @Column(length=500, nullable=false)
    private String title;
    @Column(length=2000, nullable=false)
    private String content;
    @Column(length=500, nullable=false)
    private String name;

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }
}
