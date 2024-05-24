package org.zerock.b01.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Reply", indexes = {@Index(name="idx_reply_board_bno",columnList = "board_bno")})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    //지연로딩
    //fetch = FetchType.EAGER = 일대 다일때 사용한다.
    @ManyToOne(fetch = FetchType.LAZY)//다대 일 내가 기준으로 내가 많으니까 다 board는 하나니까 일
    private Board board;

    private String replyText;
    private String replyer;

    public void changeText(String Text){
        this.replyText = Text;
    }
}
