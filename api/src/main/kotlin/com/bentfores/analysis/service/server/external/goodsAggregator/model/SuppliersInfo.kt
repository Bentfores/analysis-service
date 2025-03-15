package com.bentfores.analysis.service.server.external.goodsAggregator.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class SuppliersInfo(
  @JsonProperty("article", required = true) val article: String,
  @JsonProperty("name", required = true) val name: String,
  @JsonProperty("profitPlace") val profitPlace: BigDecimal,
  @JsonProperty("imageUrl", required = true) val imageUrl: String,
  @JsonProperty("price", required = true) val price: BigDecimal,
  @JsonProperty("quantity", required = true) val quantity: BigDecimal,
  @JsonProperty("rating", required = true) val rating: BigDecimal,
  @JsonProperty("years", required = true) val years: BigDecimal,
  @JsonProperty("supplierUrl", required = true) val supplierUrl: String,
  @JsonProperty("supplierImageUrl", required = true) val supplierImageUrl: String,
  @JsonProperty("supplierProductUrl", required = true) val supplierProductUrl: String
)