package com.bentfores.analysis.service.server.feign

import com.bentfores.analysis.service.server.external.goodsAggregator.GoodsAggregatorApi
import com.bentfores.analysis.service.server.external.goodsAndSellerManagement.GoodsAndSellerManagementApi
import feign.Capability
import feign.micrometer.MicrometerCapability
import feign.okhttp.OkHttpClient
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.cloud.openfeign.FeignClientBuilder
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ImportAutoConfiguration(FeignAutoConfiguration::class)
@ComponentScan
class FeignConfig(
  private val feignProperties: FeignProperties
) {

  @Bean
  fun okHttpClient(): OkHttpClient = OkHttpClient()

  @Bean
  fun feignTracing(registry: MeterRegistry): Capability = MicrometerCapability(registry)

  @Bean
  fun goodsAndSellerManagementApiFeign(applicationContext: ApplicationContext): GoodsAndSellerManagementApi =
    FeignClientBuilder(applicationContext)
      .forType(GoodsAndSellerManagementApi::class.java, "goodsAndSellerManagementApi")
      .url(feignProperties.goodsAndSellerManagementUrl)
      .build()

  @Bean
  fun goodsAggregatorApiFeign(applicationContext: ApplicationContext): GoodsAggregatorApi =
    FeignClientBuilder(applicationContext)
      .forType(GoodsAggregatorApi::class.java, "goodsAggregatorApi")
      .url(feignProperties.goodsAggregatorUrl)
      .build()
}
