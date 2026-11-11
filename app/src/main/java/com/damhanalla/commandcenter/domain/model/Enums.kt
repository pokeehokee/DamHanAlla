package com.damhanalla.commandcenter.domain.model

enum class EntityStructure(val displayName: String) {
    LLC("Limited Liability Company (LLC)"),
    SOLE_PROPRIETORSHIP("Sole Proprietorship"),
    S_CORP("S-Corporation"),
    C_CORP("C-Corporation"),
    COOPERATIVE("Agricultural / Rural Cooperative"),
    NON_PROFIT("Non-Profit (501c3)")
}

enum class ServiceTier(val displayName: String) {
    AUDIT_ONLY("Intake & Eligibility Audit ($500-$750)"),
    HYBRID_PACKAGING("Audit + Back-End Success Bonus (5-8%)"),
    FULL_RETAINER("Continuous Compliance Retainer")
}

enum class ApplicationStage(val displayName: String, val order: Int) {
    IDENTIFIED("Identified", 1),
    ELIGIBILITY_VETTED("Eligibility Vetted", 2),
    INTAKE_COMPLETE("Intake Complete", 3),
    DRAFTING_NARRATIVE("Drafting Narrative", 4),
    CLIENT_REVIEW("Client Review & Approval", 5),
    SUBMITTED("Submitted to Agency", 6),
    UNDER_REVIEW("Under Agency Review", 7),
    AWARDED("Award Approved", 8),
    REJECTED("Declined / Ineligible", 9)
}

enum class TaskPriority(val level: Int, val label: String) {
    URGENT_HIGH(1, "Urgent / High"),
    MEDIUM(2, "Medium Priority"),
    LOW(3, "Routine / Low")
}

enum class TaskStatus(val displayName: String) {
    TODO("To Do"),
    IN_PROGRESS("In Progress"),
    BLOCKED_BY_CLIENT("Blocked: Awaiting Client Data"),
    COMPLETED("Completed")
}

enum class DocumentCategory(val displayName: String) {
    LEGAL_GOVERNANCE("Legal & Governance (Articles, Operating Agmt)"),
    CLIENT_FINANCIAL("Client Financials (Tax Returns, Balance Sheets)"),
    GRANT_GUIDELINE("Grant Solicitation / NOFO Guidance"),
    APPLICATION_EXHIBIT("Application Exhibit / Project Budget"),
    AWARD_OFFICIAL("Official Award Letter & Contract")
}

enum class InvoiceStatus(val label: String) {
    DRAFT("Draft"),
    SENT("Sent - Pending 14-Day Terms"),
    PAID("Paid"),
    OVERDUE("Past Due")
}
