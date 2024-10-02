package com.trainingdev.td_bs_management_post.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailList {

    private List<PostDetail> postList;
}
