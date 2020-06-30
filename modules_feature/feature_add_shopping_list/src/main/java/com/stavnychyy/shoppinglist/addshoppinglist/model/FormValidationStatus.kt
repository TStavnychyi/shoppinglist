package com.stavnychyy.shoppinglist.addshoppinglist.model

import org.threeten.bp.LocalDate


sealed class FormValidationStatus

data class FormValidStatus(val name: String, val purchaseDate: LocalDate) : FormValidationStatus()

object FormInvalidStatus : FormValidationStatus()
