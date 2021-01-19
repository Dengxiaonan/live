package requireproject.repository.requireapplicant.entity;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentEntity {
   private String id;
   private String commentator;//评论人
   private LocalDateTime editTime;//评论时间
   private String content;//评论内容
   private String rid;

}
