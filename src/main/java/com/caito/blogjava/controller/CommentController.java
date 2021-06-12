package com.caito.blogjava.controller;

import com.caito.blogjava.dto.CommentNew;
import com.caito.blogjava.dto.CommentResponse;
import com.caito.blogjava.entity.Comment;
import com.caito.blogjava.service.implementation.CommentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentNew commentNew){
        return new ResponseEntity<CommentResponse>(commentService.createComment(commentNew), HttpStatus.CREATED);
    }


    @GetMapping("/all/{article_id}")
    public ResponseEntity<List<Comment>> getAll(@PathVariable("article_id") Long article_id){
        return new ResponseEntity<List<Comment>>(commentService.getComments(article_id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<CommentResponse>(commentService.getComment(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable("id") Long id,
                                                         @RequestBody CommentNew commentNew){
        return new ResponseEntity<CommentResponse>(commentService.updateComment(id,commentNew), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id){
        return new ResponseEntity(HttpStatus.OK);
    }
}