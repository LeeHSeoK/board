package org.zerock.b01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageRequestDTO {
    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int pageSize = 10;
    private String type; //t,c,w, tc, tw, cw, twc
    private String keyword;
    private String link;

    public String[] getTypes(){
        if(type==null || type.isEmpty()){
            return null;
        }
        return type.split("");
    }

    public Pageable getPageable(String...props){
        return PageRequest.of(this.page-1,this.pageSize, Sort.by(props).descending());
    }

    public String getLink(){
        if(link==null || link.isEmpty()){
            StringBuilder builder = new StringBuilder();
            builder.append("page="+this.page);
            builder.append("&pageSize="+this.pageSize);
            if(type!=null && type.length()>0){
                builder.append("&type="+type);
            }
            if(keyword!=null && keyword.length()>0){
                try{
                    builder.append("&keyword="+ URLEncoder.encode(keyword,"UTF-8"));
                }catch(UnsupportedEncodingException e){
                    e.printStackTrace();
                }

            }
            link = builder.toString();
        }
        return link;
    }

}

