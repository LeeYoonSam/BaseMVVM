package com.ys.base.basemvvm.Kotlin

import android.content.Context
import android.test.mock.MockContext
import android.view.Gravity
import android.widget.LinearLayout
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class UsefulFunctionTest {

    /*
    Context를 Mock로 얻으려면 앱 빌드 그래들 파일에 아래 android { extention } 추가

    testOptions {
        unitTests {
            returnDefaultValues = true
        }
    }
     */
    private lateinit var context: Context
    private lateinit var usefulFunction: UsefulFunction

    @Before
    fun setUp() {
        context = MockContext()
        assertNotNull(context)

        usefulFunction = UsefulFunction()
        assertNotNull(usefulFunction)
    }

    @Test
    fun letTest() {
        var result1 = usefulFunction.letSample1()
        var result2 = usefulFunction.letSample2()

        assertEquals(10f, result1)
        assertEquals(10f, result2)
        assertEquals(result1, result2)

        var result3 = usefulFunction.letSafecallSample3(null)
        var result4 = usefulFunction.letSafecallSample3("Hello")
        var result5 = usefulFunction.letSafecallSample3("Test")

        assertFalse(result3)
        assertTrue(result4)
        assertTrue(result5)
        assertEquals(result4, result5)
    }

    @Test
    fun applyTest() {
        // apply() 함수를 사용하면 이를 다음과 같이 바꿀 수 있습니다. param을 사용하지 않고 직접 속성을 지정하는 것을 확인할 수 있습니다.
        val applyParam = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.CENTER_HORIZONTAL
            weight = 1f
            topMargin = 100
            bottomMargin = 100
        }

        assertNotNull(applyParam)

        assertEquals(Gravity.CENTER_HORIZONTAL, applyParam.gravity)
        assertEquals(1f, applyParam.weight)
        assertEquals(100, applyParam.topMargin)
        assertEquals(100, applyParam.bottomMargin)
    }
}