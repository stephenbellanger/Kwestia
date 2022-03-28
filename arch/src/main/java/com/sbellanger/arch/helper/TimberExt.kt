package com.sbellanger.arch.helper

val Any.timberConcreteClassLinkTag: String
    get() = this.javaClass.fileNameTimberTag

val <T> Class<T>.fileNameTimberTag: String
    get() {
        return if (this.isKotlinClass()) {
            "(${this.simpleName}.kt:1)"
        } else {
            "(${this.simpleName}.java:1)"
        }
    }

fun Class<*>.isKotlinClass(): Boolean {
    return this.declaredAnnotations.any {
        it.annotationClass.qualifiedName == "kotlin.Metadata"
    }
}
