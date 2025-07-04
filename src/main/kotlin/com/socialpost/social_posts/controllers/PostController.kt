package com.socialpost.social_posts.controllers

import com.socialpost.social_posts.controllers.PostController.PostResponse
import com.socialpost.social_posts.database.model.Post
import com.socialpost.social_posts.database.repository.PostRepository
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.bson.types.ObjectId
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.time.Instant

// POST http://localhost:8085/posts
// GET http://localhost:8085/posts?ownerId=abc
// DELETE http://localhost:8085/posts/abc

@RestController
@RequestMapping("/posts")
class PostController(
    private val postRepository: PostRepository
) {

    data class PostRequest(
        val id: String?,
        @field:NotBlank(message = "Title can't be blank.")
        val title: String,
        val body: String
    )

    data class PostResponse(
        val id: String,
        val title: String,
        val body: String,
        val createdAt: Instant,
    )

    @PostMapping
    fun save(
        @Valid @RequestBody body: PostRequest
    ): PostResponse {
        val ownerId = SecurityContextHolder.getContext().authentication.principal as String
        require(ObjectId.isValid(ownerId)) { "Invalid ownerId: $ownerId" }
        println("OwnerId from token: $ownerId")
        val post = postRepository.save(
            Post(
                id = body.id?.let { ObjectId(it) } ?: ObjectId.get(),
                title = body.title,
                body = body.body,
                createdAt = Instant.now(),
                ownerId = ObjectId(ownerId)
            )
        )
        return post.toResponse()
    }

    @GetMapping
    fun findByOwnerId(): List<PostResponse> {
        val ownerId = SecurityContextHolder.getContext().authentication.principal as String
        return postRepository.findByOwnerId(ObjectId(ownerId)).map {
            it.toResponse()
        }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(@PathVariable id: String) {
        val post = postRepository.findById(ObjectId(id)).orElseThrow {
            IllegalArgumentException("Post not found.")
        }
        val ownerId = SecurityContextHolder.getContext().authentication.principal as String
        if(post.ownerId.toHexString() == ownerId) {
            postRepository.deleteById(ObjectId(id))
        }
    }

}

private fun Post.toResponse(): PostResponse {
    return PostResponse(
        id = id.toHexString(),
        title = title,
        body = body,
        createdAt = createdAt,
    )
}