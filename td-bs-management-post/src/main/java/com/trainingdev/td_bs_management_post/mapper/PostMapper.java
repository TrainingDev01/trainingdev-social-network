package com.trainingdev.td_bs_management_post.mapper;

import com.trainingdev.td_bs_management_post.dto.input.PostModified;
import com.trainingdev.td_bs_management_post.dto.input.PostRequest;
import com.trainingdev.td_bs_management_post.dto.output.PostDetail;
import com.trainingdev.td_bs_management_post.entities.PostEntity;
import com.trainingdev.td_bs_management_post.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "userEntity")
    PostEntity postRequestToPostEntity(PostRequest postRequestDTO, UserEntity userEntity);

    @Mapping(target = "creationDate", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "modificationDate", dateFormat = "dd/MM/yyyy")
    PostDetail postEntityToPostDetail(PostEntity postEntity);

    @Mapping(target = "id", source = "id")
    void updatePostEntityFromModified(PostModified postModifiedDTO, @MappingTarget PostEntity postEntity);

    List<PostDetail> postEntitiesToPostDetailList(List<PostEntity> postEntities);
}
