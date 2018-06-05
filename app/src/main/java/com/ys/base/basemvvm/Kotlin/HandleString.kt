package com.ys.base.basemvvm.Kotlin

import android.util.Log

class HandleString {

    /*
     기존코드

     void greet(String name) {
        // 문자열 출력을 위해 %s 치환자 사용
        System.out.println(String.format("Hello, %s", name));
    }

    void add(int a, int b) {
        // 정수형 출력을 위해 %d 치환자 사용
        System.out.println(String.format("%d + %d is %d", a, b, a + b));
    }
    */

    fun greet(name: String) {
        // 값 name을 직접 문자열 내에 대입합니다.
        System.out.println("Hello, $name. Your hashCode is ${name.hashCode()}.")
    }

    fun add(a: Int, b: Int) {
        // 값 a, b와 a + b의 계산값을 대입합니다.
        // 표현식을 사용하는 경우 '$'기호와 중괄호를 함께 사용합니다.
        System.out.println("$a + $b is ${a + b}")
    }


//// "Hello, Hana Song" 출력
//    greet("Hana Song")
//
//// "1 + 3 = 4" 출력
//    add(1, 3)


    // 여러줄 일때
    fun multipleLine() {
        val heroes = """
            D.va
            Lucio
            Mercy
            Soldier: 76
            """
    }

    // 코드 가독성이 떨어질때 구분문자(|) 앞을 공백으로 채운 후 trimMargin()을 사용
    fun multipleLine2() {
        val heroes = """
        |D.Va
        |Lucio
        |Mercy
        |Soldier: 76
        """.trimMargin()

        // Other logics...
    }


    // 리스트 문자열 합치기
    fun joinToString() {
        val items = listOf("D.Va", "Lucio", "Mercy", "Soldier: 76")

        // "D.Va, Lucio, Mercy, Soldier: 76" 출력
        System.out.println(items.joinToString())
    }

    // 리스트 문자열 합치기 구분자 추가
    fun joinToStringConcat() {
        val items = listOf("D.Va", "Lucio", "Mercy", "Soldier: 76")

        // "D.Va-Lucio-Mercy-Soldier: 76" 출력
        System.out.println(items.joinToString("-"))
    }


    // 객체의 문자 조합
    class HeroName(val firstName: String, val lastName: String)
    val items = listOf(HeroName("Hana", "Song"), HeroName("Jack", "Morrison"))

    fun joinObject() {
        // HeroName 클래스를 문자열로 변환하는 식을 추가합니다.
        // "Hana Song, Jack Morrison" 출력
        System.out.println(items.joinToString { "${it.firstName} ${it.lastName}" })
    }
}