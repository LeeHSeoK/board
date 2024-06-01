package org.zerock.b01.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResopnseDTO<E> {       //여러종류의 데이터를 주고받을 수있도록 <E>제내릭을 적어준다.
    private int page;
    private int size;
    private int total;

    //시작페이지번호
    private int start;
    private int end;


    private boolean prev;
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResopnseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        if(total<=0){
            return;
        }
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.page/10.0)) * 10;
        this.start = this.end - 9;

        int last = (int)(Math.ceil(total/(double)size));
        this.end = end > last? last : end;

        this.prev = this.start > 1;
        this.next = total > this.end * this.size;


    }
}
