package org.zerock.b01.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @ManyToOne(fetch = FetchType.LAZY)//다대 일 내가 기준으로 내가 많으니까 다 board는 하나니까 일
    private Board board;

    private String replyText;
    private String replyer;

}
