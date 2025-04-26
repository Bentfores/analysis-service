package com.bentfores.analysis.service.server.controller.v1

import com.bentfores.analysis.service.api.model.ParsedSupplier
import com.bentfores.analysis.service.api.model.SendMessageRequest
import com.bentfores.analysis.service.api.model.SuppliersInfo
import com.bentfores.analysis.service.api.v1.AnalysisApi
import com.bentfores.analysis.service.server.service.v1.AnalysisServiceV1
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AnalysisController(
  private val analysisServiceV1: AnalysisServiceV1
) : AnalysisApi {

  override fun suppliersInfoByGet(article: String): ResponseEntity<List<SuppliersInfo>> {
    return ResponseEntity.ok(
      analysisServiceV1.getSuppliers(article)
    )
  }

  override fun suppliersSearchByPost(article: String): ResponseEntity<Unit> {
    analysisServiceV1.postSuppliersSearch(article)
    return ResponseEntity.status(200).build()
  }

  override fun sendMessageByPost(sendMessageRequest: SendMessageRequest): ResponseEntity<Unit> {
    analysisServiceV1.sendMessage(sendMessageRequest.productUrl, sendMessageRequest.article, sendMessageRequest.supplierId)
    return ResponseEntity.status(200).build()
  }

  override fun suppliersParsedByPost(parsedSupplier: List<ParsedSupplier>): ResponseEntity<Unit> {
    analysisServiceV1.saveParsedSuppliers(parsedSupplier)
    return ResponseEntity.status(200).build()
  }
}