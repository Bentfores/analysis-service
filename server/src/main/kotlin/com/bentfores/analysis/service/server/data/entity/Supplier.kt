package com.bentfores.analysis.service.server.data.entity

import com.adron.bot.manager.server.data.misc.Auditable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "supplier")
data class Supplier(
  @Id
  @Column(name = "supplier_id")
  @GeneratedValue(strategy = GenerationType.UUID)
  var supplierId: UUID? = null,

  @Column(name = "article")
  var article: String,

  @Column(name = "profit_place")
  var profitPlace: BigDecimal,

  @Column(name = "supplier_name")
  var supplierName: String,

  @Column(name = "product_url")
  var productUrl: String,

  @Column(name = "price")
  var price: BigDecimal,

  @Column(name = "quantity")
  var quantity: BigDecimal,

  @Column(name = "rating")
  var rating: BigDecimal,

  @Column(name = "years")
  var years: BigDecimal,

  @Column(name = "image_url")
  var imageUrl: String,

  @Column(name = "supplier_image_url")
  var supplierImageUrl: String,

  @Column(name = "supplier_product_url")
  var supplierProductUrl: String,

  ) : Auditable()
