package vn.edu.iuh.fit.donchung.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.donchung.backend.entities.PostComment;
import vn.edu.iuh.fit.donchung.backend.utils.request.PostCommentRequest;
import vn.edu.iuh.fit.donchung.backend.utils.response.PostCommentResponse;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {PostMapper.class, UserMapper.class})
public interface PostCommentMapper {
    @Mapping(target = "post.id", source = "post")
    @Mapping(target = "parent.id", source = "parent")
    PostComment toEntity(PostCommentRequest request);

    @Mapping(target = "post", source = "post.id")
    @Mapping(target = "parent", source = "parent.id")
    PostCommentResponse toDto(PostComment postComment);
    @Mapping(target = "post.id", source = "post")
    @Mapping(target = "parent.id", source = "parent")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PostComment partialUpdate(PostCommentRequest PostCommentRequest, @MappingTarget PostComment postComment);
}