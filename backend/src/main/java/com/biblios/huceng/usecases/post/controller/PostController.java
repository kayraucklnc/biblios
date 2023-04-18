package com.biblios.huceng.usecases.post.controller;

import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.entity.Post;
import com.biblios.huceng.exception.PermissionDeniedException;
import com.biblios.huceng.startup.StartupService;
import com.biblios.huceng.usecases.post.dto.LikeStatus;
import com.biblios.huceng.usecases.post.dto.PostRequest;
import com.biblios.huceng.usecases.post.service.PostService;
import com.biblios.huceng.util.RoleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Comparator;


@RestController
@RequestMapping(path = "/api/post") //localhost::api/post
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final StartupService startupService;

    @PostMapping()
    public void createNewPost(@RequestBody PostRequest postRequest){
        postService.createPost(postRequest);
    }

    @GetMapping()
    public Collection<Post> returnAllPosts(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = startupService.getUser(auth.getName());

        if (!appUser.getRoles().stream().anyMatch(a -> a.getName().equals(RoleUtil.ROLE_ADMIN))) {
            throw new PermissionDeniedException("User doesn't have permissions to get all posts.");
        }
        return postService.returnAllPosts();
    }

    @GetMapping("/feed")
    public Collection<Post> returnFeed() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = startupService.getUser(auth.getName());
        return postService.getUserFeed(appUser);
    }

    @GetMapping("/{username}")
    public Collection<Post> returnAllFrom(@PathVariable String username){
        return startupService.getUser(username).getPosts().stream().sorted(new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                return o2.getTimestamp().compareTo(o1.getTimestamp());
            }
        }).toList();
    }

    @DeleteMapping("{postId}")
    public void deletePostById(@PathVariable String postId){
        postService.deletePostById(Long.valueOf(postId));
    }

    @PutMapping("/edit/{postId}")
    public void editPostById(@PathVariable String postId, @RequestBody PostRequest postRequest){
        postService.editPostById(Long.valueOf(postId), postRequest);
    }

    @PostMapping("/like/{postId}")
    public LikeStatus likePostById(@PathVariable String postId){
        return postService.likePostById(Long.valueOf(postId));
    }
}