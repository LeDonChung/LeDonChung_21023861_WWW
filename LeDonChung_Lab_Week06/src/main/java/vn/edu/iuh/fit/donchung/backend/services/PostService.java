package vn.edu.iuh.fit.donchung.backend.services;

import vn.edu.iuh.fit.donchung.backend.dtos.PageDto;
import vn.edu.iuh.fit.donchung.backend.exceptions.PostException;
import vn.edu.iuh.fit.donchung.backend.exceptions.UserException;
import vn.edu.iuh.fit.donchung.backend.utils.request.PostCommentRequest;
import vn.edu.iuh.fit.donchung.backend.utils.request.PostNewRequest;
import vn.edu.iuh.fit.donchung.backend.utils.request.PostUpdateRequest;
import vn.edu.iuh.fit.donchung.backend.utils.response.PostResponse;

public interface PostService {
    public PostResponse create(PostNewRequest postRequest) throws PostException, UserException;
    public PageDto<PostResponse> getAll(int page, int size);

    public PostResponse getById(Long id) throws PostException;

    public PostResponse update(Long id, PostUpdateRequest postRequest) throws PostException;

    PostResponse publish(Long id) throws PostException;
    PostResponse addCommentToPost(Long id, PostCommentRequest commentRequest) throws PostException;
}
