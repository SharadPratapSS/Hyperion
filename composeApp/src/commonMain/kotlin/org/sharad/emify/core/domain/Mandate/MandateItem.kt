package org.sharad.emify.core.domain.Mandate

data class MandateItem(
    val id: String,
    val name: String,
    val content: String,
    val paid: String,
    val unpaid: String,
    val status: MandateStatus
)
