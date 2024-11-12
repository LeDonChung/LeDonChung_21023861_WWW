package vn.edu.iuh.fit.donchung.backend.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.donchung.backend.dtos.PageDto;
import vn.edu.iuh.fit.donchung.backend.exceptions.PostException;
import vn.edu.iuh.fit.donchung.backend.exceptions.UserException;
import vn.edu.iuh.fit.donchung.backend.services.PostService;
import vn.edu.iuh.fit.donchung.backend.utils.request.PostCommentRequest;
import vn.edu.iuh.fit.donchung.backend.utils.request.PostNewRequest;
import vn.edu.iuh.fit.donchung.backend.utils.request.PostUpdateRequest;
import vn.edu.iuh.fit.donchung.backend.utils.response.PostResponse;
@RestController
@RequestMapping("/api/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @Operation(summary = "Tạo bài viết mới", description = "Tạo một bài viết mới trong hệ thống", responses = {
            @ApiResponse(responseCode = "201", description = "Bài viết được tạo thành công"),
            @ApiResponse(responseCode = "400", description = "Dữ liệu không hợp lệ")
    })
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostNewRequest postRequest) throws PostException, UserException {
        PostResponse postResponse = postService.create(postRequest);
        return ResponseEntity.ok(postResponse);
    }

    @Operation(summary = "Lấy tất cả bài viết", description = "Lấy tất cả bài viết trong hệ thống", responses = {
            @ApiResponse(responseCode = "200", description = "Lấy bài viết thành công")
    })
    @GetMapping
    public ResponseEntity<PageDto<PostResponse>> getAllPosts(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        PageDto<PostResponse> posts = postService.getAll(page, size);
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Lấy bài viết theo ID", description = "Lấy thông tin chi tiết của bài viết theo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Lấy bài viết thành công"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy bài viết")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) throws PostException {
        PostResponse postResponse = postService.getById(id);
        return ResponseEntity.ok(postResponse);
    }

    @Operation(summary = "Cập nhật bài viết", description = "Cập nhật thông tin bài viết", responses = {
            @ApiResponse(responseCode = "200", description = "Cập nhật bài viết thành công"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy bài viết")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest postRequest) throws PostException {
        PostResponse postResponse = postService.update(id, postRequest);
        return ResponseEntity.ok(postResponse);
    }

    @Operation(summary = "Công khai bài viết", description = "Công khai bài viết ra ngoài trang chủ", responses = {
            @ApiResponse(responseCode = "200", description = "Công khai bài viết thành công"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy bài viết")
    })
    @PutMapping("/{id}/publish")
    public ResponseEntity<PostResponse> publishPost(@PathVariable Long id) throws PostException {
        PostResponse postResponse = postService.publish(id);
        return ResponseEntity.ok(postResponse);
    }

    @Operation(summary = "Thêm bình luận vào bài viết", description = "Thêm bình luận vào bài viết", responses = {
            @ApiResponse(responseCode = "200", description = "Thêm bình luận thành công"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy bài viết")
    })
    @PostMapping("/{id}/comments")
    public ResponseEntity<PostResponse> addCommentToPost(@PathVariable Long id, @RequestBody PostCommentRequest commentRequest) throws PostException {
        PostResponse postResponse = postService.addCommentToPost(id, commentRequest);
        return ResponseEntity.ok(postResponse);
    }
}
