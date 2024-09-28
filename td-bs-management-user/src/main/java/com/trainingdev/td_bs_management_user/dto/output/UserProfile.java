package com.trainingdev.td_bs_management_user.dto.output;


import com.trainingdev.td_bs_management_user.dto.business.PostDetail;
import com.trainingdev.td_bs_management_user.dto.input.UserDetail;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private UserDetail userDetail;
    private List<PostDetail> postList;
}
