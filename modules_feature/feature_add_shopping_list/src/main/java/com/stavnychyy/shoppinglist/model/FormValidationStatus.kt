package com.stavnychyy.shoppinglist.model

import org.threeten.bp.LocalDate


sealed class FormValidationStatus

data class FormValidStatus(val name: String, val purchaseDate: LocalDate) : FormValidationStatus()

object FormInvalidStatus : FormValidationStatus()
