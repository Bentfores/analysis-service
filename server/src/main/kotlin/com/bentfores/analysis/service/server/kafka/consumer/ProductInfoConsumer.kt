package com.bentfores.analysis.service.server.kafka.consumer

import com.bentfores.analysis.service.ProductInfo.ProductsInfo
import com.bentfores.analysis.service.server.service.v1.AnalysisServiceV1
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProductInfoConsumer(
  private val analysisServiceV1: AnalysisServiceV1,
) {

  @KafkaListener(
    topics = ["\${system.kafka.topics.product-info.name}"],
    groupId = "\${spring.kafka.consumer.group-id}",
    containerFactory = "productInfoKafkaListenerContainerFactory",
  )
  @Transactional
  fun consume(
    @Payload productInfoEvent: ProductsInfo,
    @Header(KafkaHeaders.RECEIVED_KEY) key: String?,
    @Header(KafkaHeaders.RECEIVED_PARTITION) partition: Int,
    @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String
  ) {
    log.info { "ProductInfo event received: \n $productInfoEvent" }

    analysisServiceV1.updateSuppliers(productInfoEvent)
  }

  companion object {
    private val log = KotlinLogging.logger(this::class.java.simpleName)
  }
}