package nicestring

fun String.isNice(): Boolean {
    val condition1 = !contains("b[uae]".toRegex())
    val condition2 = count { it in "aeiou" } >= 3
    val condition3 = zipWithNext().any { (a, b) -> a == b }

    return listOf(condition1, condition2, condition3).count { it } >= 2
}