package com.cdmp.rickmorty.domain.model

import com.cdmp.rickmorty.presentation.ErrorDisplay

data class DomainError(val cause: Throwable) {
    fun toDisplay() = ErrorDisplay(cause.message ?: "Unknown error")
}