package com.subscription.events;

import com.subscription.domain.Hashtag;
import com.subscription.domain.Video;
import com.subscription.repositories.SubscriptionRepository;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;

@KafkaListener(groupId = "video-sm-consumers")
public class VideoWatchedListener {
	
	@Inject
	SubscriptionRepository repo;
	
	final String TOPIC_WATCHED="video-watched";

	@Topic(TOPIC_WATCHED)
	public void watchedVideo(@KafkaKey Long id, Video video) {
		System.out.printf("Video watched by userId: %d%n", id);
		
		for(Hashtag hashtag : video.getHashtags()) {
			System.out.println("recieved watched event for hashtag " + hashtag.getName());
		}
	}
	
}