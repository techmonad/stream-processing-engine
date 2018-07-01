package com.techmonad.streaming

import com.techmonad.streaming.core.SentimentAnalyzer
import com.techmonad.streaming.json.JsonHelper._
import com.techmonad.streaming.kafka.KafkaUtility
import com.techmonad.streaming.persist.PersistenceFactory
import com.techmonad.streaming.util.DateUtil
import org.apache.spark.streaming.StreamingContext

object StreamingProcessor {

  def process(ssc: StreamingContext, topics: List[String], indexName: String, `type`: String): Unit = {
    val messages = KafkaUtility.createDStreamFromKafka(ssc, topics)
    val tweets = messages.map { record => parse(record.value()).extract[Map[String, String]] }
    val tweetSentiments =
      tweets.map { tweet =>
        val sentiment = SentimentAnalyzer.getSentiment(tweet("text"))
        val esDate = DateUtil.getESDateFormat(tweet("created_at"))
        tweet + ("sentiment" -> sentiment) + ("created_at" -> esDate)
      }
    PersistenceFactory.persist(tweetSentiments, indexName, `type`)
  }

}
