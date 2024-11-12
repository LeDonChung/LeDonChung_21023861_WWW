package vn.edu.iuh.fit.donchung.backend.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.donchung.backend.dtos.PageDto;
import vn.edu.iuh.fit.donchung.backend.entities.Post;
import vn.edu.iuh.fit.donchung.backend.entities.PostComment;
import vn.edu.iuh.fit.donchung.backend.entities.User;
import vn.edu.iuh.fit.donchung.backend.exceptions.PostException;
import vn.edu.iuh.fit.donchung.backend.exceptions.UserException;
import vn.edu.iuh.fit.donchung.backend.mappers.PostCommentMapper;
import vn.edu.iuh.fit.donchung.backend.mappers.PostMapper;
import vn.edu.iuh.fit.donchung.backend.repositories.PostCommentRepository;
import vn.edu.iuh.fit.donchung.backend.repositories.PostRepository;
import vn.edu.iuh.fit.donchung.backend.repositories.UserRepository;
import vn.edu.iuh.fit.donchung.backend.services.PostService;
import vn.edu.iuh.fit.donchung.backend.utils.request.PostCommentRequest;
import vn.edu.iuh.fit.donchung.backend.utils.request.PostNewRequest;
import vn.edu.iuh.fit.donchung.backend.utils.request.PostUpdateRequest;
import vn.edu.iuh.fit.donchung.backend.utils.response.PostResponse;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PostCommentMapper postCommentMapper;
    @Autowired
    private PostCommentRepository postCommentRepository;
    @Override
    public PostResponse create(PostNewRequest postRequest) throws PostException, UserException {

        User author = userRepository.findById(postRequest.getAuthor()).orElseThrow(() -> new UserException("Tác giả không tồn tại."));

        Post parent = null;
        if(postRequest.getParent() != null) {
            parent = postRepository.findById(postRequest.getParent()).orElseThrow(() -> new PostException(String.format("Bài viết gốc %s không tồn tại.", postRequest.getParent())));
        }



        Post post = postMapper.toEntity(postRequest);
        post.setAuthor(author);
        post.setParent(parent);
        post.setUpdatedAt(Timestamp.from(Instant.now()));
        post.setCreatedAt(Timestamp.from(Instant.now()));

        if(postRequest.getPublished() ) {
            post.setPublishedAt(Timestamp.from(Instant.now()));
        }

        post = postRepository.save(post);
        return postMapper.toDto(post);
    }

    @Override
    public PageDto<PostResponse> getAll(int page, int size) {
        Page<Post> pagePost = postRepository.findAll(PageRequest.of(page, size));

        return PageDto.<PostResponse>builder()
                .page(page)
                .size(size)
                .totalPages(pagePost.getTotalPages())
                .values(pagePost.map(postMapper::toDto).toList())
                .build();
    }

    @Override
    public PostResponse getById(Long id) throws PostException {

        Post post = postRepository.findById(id).orElse(null);
        if(post != null) {
            return postMapper.toDto(post);
        }
        throw new PostException("Bài viết không tồn tại.");
    }

    @Override
    public PostResponse update(Long id, PostUpdateRequest postRequest) throws PostException {
        Post post = postRepository.findById(id).orElse(null);
        if(post == null) {
            throw new PostException("Bài viết không tồn tại.");
        }

        post = postMapper.partialUpdate(postRequest, post);
        post.setUpdatedAt(Timestamp.from(Instant.now()));
        if(postRequest.getPublished() && post.getPublishedAt() == null) {
            post.setPublishedAt(Timestamp.from(Instant.now()));
        }
        post = postRepository.save(post);
        return postMapper.toDto(post);
    }

    @Override
    public PostResponse publish(Long id) throws PostException {
        Post post = postRepository.findById(id).orElse(null);
        if(post != null) {
            post.setPublishedAt(Timestamp.from(Instant.now()));
            post = postRepository.save(post);
            return postMapper.toDto(post);
        }
        throw new PostException("Bài viết không tồn tại.");
    }

    @Override
    public PostResponse addCommentToPost(Long id, PostCommentRequest commentRequest) throws PostException {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostException("Bài viết không tồn tại"));

        // Tạo đối tượng PostComment
        PostComment comment = PostComment.builder()
                .post(post)
                .parent(commentRequest.getParent() != null ? postCommentRepository.findById(commentRequest.getParent()).orElse(null) : null)
                .title(commentRequest.getTitle())
                .content(commentRequest.getContent())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .published(true)
                .build();

        comment = postCommentRepository.save(comment);

        post = postRepository.findById(comment.getPost().getId()).orElse(null);

        if(post == null) {
            throw new PostException("Thêm bình luận không thành công.");
        }
        return postMapper.toDto(post);
    }
}
