package com.bentfores.analysis.service.server.external.goodsAggregator.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class SuppliersInfo(
  @JsonProperty("article", required = true) val article: String,
  @JsonProperty("profitPlace") val profitPlace: BigDecimal,
  @JsonProperty("imageUrl", required = true) val imageUrl: String,
  @JsonProperty("price", required = true) val price: BigDecimal,
  @JsonProperty("minOrder", required = true) val minOrder: BigDecimal,
  @JsonProperty("rating", required = true) val rating: BigDecimal,
  @JsonProperty("years", required = true) val years: BigDecimal,
  @JsonProperty("supplierName", required = true) val supplierName: String,
  @JsonProperty("supplierUrl", required = true) val supplierUrl: String,
  @JsonProperty("supplierImageUrl", required = true) val supplierImageUrl: String,
  @JsonProperty("supplierProductUrl", required = true) val supplierProductUrl: String
)