package org.zerock.b01.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"imageSet","replies"})
//@ToString
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

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    @BatchSize(size=20)
    private Set<BoardImage> imageSet = new HashSet<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @BatchSize(size=20)
    private List<Reply> replies;

    public void change(String title, String content, String name){
        this.title = title;
        this.content = content;
        this.name = name;
    }

    public void addImage(String uuid, String FileName){
        BoardImage image = BoardImage.builder()
                .uuid(uuid)
                .fileName(FileName)
                .board(this)
                .ord(imageSet.size())
                .build();
        imageSet.add(image);
    }

    public void clearImage(){
        imageSet.forEach(boardImage -> boardImage.changeBoard(null));
        this.imageSet.clear();
    }
}
