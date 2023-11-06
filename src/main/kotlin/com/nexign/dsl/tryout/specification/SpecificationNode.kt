package com.nexign.dsl.tryout.specification

data class SpecificationNode (
    val operationName: String,
    val nestedOperations: List<SpecificationNode> = emptyList(),
    val detailedDescription: String = "",
)
