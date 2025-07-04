package com.socialpost.social_posts.database.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("posts")
data class Post(
    val title: String,
    val body: String,
    val createdAt: Instant,
    val ownerId: ObjectId,
    @Id val id : ObjectId = ObjectId.get()
)