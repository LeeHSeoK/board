package org.zerock.b01.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
    private Long rno;
    @NotNull
    private Long bno; //게시글에 딸려있는 댓글이기 때문에 게시글 정보도 필요함
    @NotEmpty
    private String replyText;
    @NotEmpty
    private String replyer;
    private LocalDateTime regDate, modDate;
}
