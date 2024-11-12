package vn.edu.iuh.fit.donchung.backend.mappers;

import org.mapstruct.*;
import vn.edu.iuh.fit.donchung.backend.entities.Post;
import vn.edu.iuh.fit.donchung.backend.utils.request.PostNewRequest;
import vn.edu.iuh.fit.donchung.backend.utils.request.PostRequest;
import vn.edu.iuh.fit.donchung.backend.utils.request.PostUpdateRequest;
import vn.edu.iuh.fit.donchung.backend.utils.response.PostResponse;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class, PostCommentMapper.class})
public interface PostMapper {
    @Mapping(target = "parent.id", source = "parent")
    @Mapping(target = "author.id", source = "author")
    Post toEntity(PostRequest postRequest);

    @Mapping(target = "parent.id", source = "parent")
    @Mapping(target = "author.id", source = "author")
    Post toEntity(PostNewRequest postRequest);


    @Mapping(target = "parent", source = "parent.id")
    PostResponse toDto(Post post);

    @Mapping(target = "parent.id", source = "parent")
    @Mapping(target = "author.id", source = "author")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Post partialUpdate(PostUpdateRequest postRequest, @MappingTarget Post post);

}