package com.bentfores.analysis.service.server.props

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "system")
class SystemProperties(
  var kafka: KafkaBlock = KafkaBlock()
) {

  class KafkaBlock(
    var topics: TopicsBlock = TopicsBlock(),
    var consumer: ConsumerBlock = ConsumerBlock(),
  )

  data class TopicsBlock(
    var productInfo: TopicBlock = TopicBlock()
  )

  data class ConsumerBlock(
    var schemaRegistryUrl: String = "",
  )

  data class TopicBlock(
    var name: String = "",
    var protobufValueType: String = "",
  )
}