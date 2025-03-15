package com.bentfores.analysis.service.server.kafka.config

import com.bentfores.analysis.service.ProductInfo
import com.bentfores.analysis.service.server.props.SystemProperties
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig
import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializerConfig.SPECIFIC_PROTOBUF_VALUE_TYPE
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@Configuration
class KafkaConsumerConfig(
  private val kafkaProperties: KafkaProperties,
  private val systemProperties: SystemProperties,
) {
  @Bean
  fun productInfoConsumerFactory(): ConsumerFactory<String, ProductInfo> =
    DefaultKafkaConsumerFactory(
      baseConsumerConfig().apply {
        this[SPECIFIC_PROTOBUF_VALUE_TYPE] =
          systemProperties.kafka.topics.productInfo.protobufValueType
      }
    )

  @Bean
  fun productInfoKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, ProductInfo> =
    ConcurrentKafkaListenerContainerFactory<String, ProductInfo>().apply {
      consumerFactory = productInfoConsumerFactory()
    }

  private fun baseConsumerConfig() = hashMapOf(
    ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
    ConsumerConfig.GROUP_ID_CONFIG to kafkaProperties.consumer.groupId,
    ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to kafkaProperties.consumer.keyDeserializer,
    ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to kafkaProperties.consumer.valueDeserializer,
    AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG to systemProperties.kafka.consumer.schemaRegistryUrl
  )
}