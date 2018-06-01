package com.ys.base.basemvvm.Kotlin

import android.graphics.Point
import java.math.BigDecimal

/**
 * http://tourspace.tistory.com/118?category=797357
 *
 * 7.1.1 이항 산술 연산자 오버로딩
 * +, - 같은 연산자를 코틀린에서는 overloading해서 사용할 수 있습니다.
 * 객체끼리 더하거나 뺄때 원하는 동작을 함수로 구현하면 연산자를 이용해 이를 표현할 수 있습니다.
 */
data class BinaryArithmeticOperator(val x: Int, val y: Int)

operator fun BinaryArithmeticOperator.plus(other: BinaryArithmeticOperator): BinaryArithmeticOperator {
    return BinaryArithmeticOperator(x + other.x, y + other.y)

    /*
    val p1 = BinaryArithmeticOperator(10, 20)
    val p2 = BinaryArithmeticOperator(30, 40)
     */
}

operator fun BinaryArithmeticOperator.times(scale: Double): BinaryArithmeticOperator {
    return BinaryArithmeticOperator((x * scale).toInt(), (y * scale).toInt())
}


/*
7.1.2 복합 연산자

코틀린은 + 대응 함수인 plus를 overloading하면 += 로 자동으로 구현해 줍니다.
만약 =+의 동작을 따로 구현하고 싶다면 plusAssign 함수를 overloading하면 됩니다.
당연히 minusAssign, timesAssign, divAssign등의 함수도 지원합니다.

단 plus와 plusAssign 두개를 동시 구현하면 컴파일 오류가 발생합니다. (+ 에 대한 동작을 어떤걸 해야할지 모르기 때문이죠.)

코틀린은 컬렉션에도 해당 연산지를 제공합니다. 단 아래 규칙에 따릅니다.

+, - 는 항상 새로운 collection을 반환한다
mutable collection에서 +=, -=는 collection을 원소를 변경한다.(새로운 collection을 생성하지 않음)
불변 collection에서 +=, -=는 새로운 collection을 반환한다. 따라서 이를 받는 변수는 var로 선언되어야 한다
 */
fun ComplexOperator() {
    val list = arrayListOf(1,2)
    list += 3 // 기존 list에 3 추가
    val newList = list + listOf(3,4)
}


/*
7.1.3 단항 연산자

++, --처럼 단항 연산자 역시 overloading이 가능합니다.
단항 연산자 함수는 인자가 없습니다.
 */
operator fun BinaryArithmeticOperator.unaryMinus(): BinaryArithmeticOperator {
    return BinaryArithmeticOperator(-x, -y)
}

operator fun BigDecimal.inc() = this + BigDecimal.ONE