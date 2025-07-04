package com.socialpost.social_posts.database.repository

import com.socialpost.social_posts.database.model.Post
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface PostRepository : MongoRepository<Post, ObjectId> {
    fun findByOwnerId(ownerId: ObjectId): List<Post>
}
