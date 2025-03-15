package com.bentfores.analysis.service.server.feign

import org.apache.logging.log4j.util.Strings
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "api.external")
data class FeignProperties(
  var goodsAndSellerManagementUrl: String = Strings.EMPTY,
  var goodsAggregatorUrl: String = Strings.EMPTY
)