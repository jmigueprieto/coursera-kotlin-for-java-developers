package rationals

import java.lang.IllegalArgumentException
import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.ZERO

class Rational(private val numerator: BigInteger, private val denominator: BigInteger) : Comparable<Rational> {

    init {
        if (denominator == ZERO) {
            throw IllegalArgumentException()
        }
    }

    operator fun plus(other: Rational): Rational {
        val n = numerator * other.denominator + other.numerator * denominator
        val d = denominator * other.denominator
        return n divBy d
    }

    operator fun minus(other: Rational): Rational {
        val n = numerator * other.denominator - other.numerator * denominator
        val d = denominator * other.denominator
        return n divBy d
    }

    operator fun times(other: Rational): Rational {
        val n = numerator * other.numerator
        val d = denominator * other.denominator
        return n divBy d
    }

    operator fun div(other: Rational): Rational {
        val n = numerator * other.denominator
        val d = denominator * other.numerator
        return n divBy d
    }

    operator fun unaryMinus(): Rational = Rational(-numerator, denominator)

    override fun compareTo(other: Rational): Int {
        val a = this.numerator * other.denominator divBy this.denominator * other.denominator
        val b = other.numerator * this.denominator divBy other.denominator * this.denominator
        return (a.numerator).compareTo(b.numerator)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Rational) return false

        val a = this.normalize()
        val b = other.normalize()

        return a.numerator == b.numerator
                && a.denominator == b.denominator
    }

    override fun toString(): String {
        return if (denominator == ONE || numerator % denominator == ZERO) {
            (numerator / denominator).toString()
        } else {
            val r = normalize()
            return "${r.numerator}/${r.denominator}"
        }
    }

    private fun normalize(): Rational {
        val gcd = numerator.gcd(denominator)
        return if (denominator < ZERO) {
            Rational(-numerator / gcd, -denominator / gcd)
        } else {
            Rational(numerator / gcd, denominator / gcd)
        }
    }

    override fun hashCode(): Int {
        var result = numerator.hashCode()
        result = 31 * result + denominator.hashCode()
        return result
    }
}

infix fun BigInteger.divBy(other: BigInteger): Rational = Rational(this, other)
infix fun Int.divBy(other: Int): Rational = Rational(this.toBigInteger(), other.toBigInteger())
infix fun Long.divBy(other: Long): Rational = Rational(this.toBigInteger(), other.toBigInteger())

fun String.toRational(): Rational {
    val numbers = split("/")
    return when (numbers.size) {
        1 -> Rational(numbers[0].toBigInteger(), 1.toBigInteger())
        2 -> Rational(numbers[0].toBigInteger(), numbers[1].toBigInteger())
        else -> throw IllegalArgumentException()
    }
}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}