package com.bentfores.analysis.service.server.external.goodsAggregator.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Products(
  @JsonProperty("article", required = true) val article: String,
  @JsonProperty("name") val name: String,
  @JsonProperty("imageUrl", required = true) val imageUrl: String,
  @JsonProperty("profitPlace", required = true) val profitPlace: String
)
