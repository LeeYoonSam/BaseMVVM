package com.ys.base.basemvvm.Kotlin

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.Toast

/**
 * 코틀린의 유용한 함수들 - let, appply, run, with
 * http://www.androidhuman.com/lecture/kotlin/2016/07/06/kotlin_let_apply_run_with/
 */
class UsefulFunction: AppCompatActivity() {

    /*

    let()
    let()은 함수를 호출하는 객체를 이어지는 블록의 인자로 넘기고, 블록의 결과값을 반환합니다.

    해당 함수의 형태 및 원문 설명은 다음과 같습니다.

    fun <T, R> T.let(block: (T) -> R): R

    Calls the specified function block with this value as its argument and returns its result.

    사용 예
    함수를 호출한 객체를 인자로 받으므로, 이를 사용하여 다른 메서드를 실행하거나 연산을 수행해야 하는 경우 사용할 수 있습니다.

    커스텀 뷰에서 Padding 값을 지정할 때 일반적으로 다음과 같이 코드를 작성합니다.

     */
    fun letSample(context: Context) {
        val padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, DisplayMetrics())
                .let { padding ->
                    Log.d(this::class.java.name, "Padding: $padding")
                }

        // 람다식의 인자가 한 개일 경우, 인자 이름을 생략하고 it을 사용하여 코드를 간략하게 할 수 있습니다.
        val padding2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, DisplayMetrics())
                .let { Log.d(this::class.java.name, "Padding: $it") }



        // let()을 안전한 호출(Safe Calls - ?.)과 함께 사용하면 if (null != obj) ... 를 대체할 수 있습니다.
        // null일 수 있는 변수 obj
        var obj: String? = null

        // ...작업 수행...

        // obj가 null이 아닐 경우 작업 수행 (기존 방식)
        if (null != obj) {
            Toast.makeText(context, obj, Toast.LENGTH_LONG).show()
        }

        // obj가 null이 아닐 경우 작업 수행 (Safe calls + let 사용)
        obj?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }


    /*
    apply()
    apply()는 함수를 호출하는 객체를 이어지는 블록의 리시버 로 전달하고, 객체 자체를 반환합니다.

    리시버란, 바로 이어지는 블록 내에서 메서드 및 속성에 바로 접근할 수 있도록 할 객체를 의미합니다. (접근 제어자에 따라 접근 가능한 범위에 한함)

    해당 함수의 형태 및 원문 설명은 다음과 같습니다.

    fun <T> T.apply(block: T.() -> Unit): T

    Calls the specified function block with this value as its receiver and returns this value.

    사용 예
    특정 객체를 생성하면서 함께 호출해야 하는 초기화 코드가 있는 경우 사용할 수 있습니다.

    새로운 LayoutParams 객체를 생성하고 속성을 지정하는 코드를 예로 들어봅시다. 여러 줄에 걸쳐 새로 선언한 변수 param를 사용하여 속성을 지정하고 있습니다.
     */
    fun applySample() {
        val param = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
        param.gravity = Gravity.CENTER_HORIZONTAL
        param.weight = 1f
        param.topMargin = 100
        param.bottomMargin = 100

        // apply() 함수를 사용하면 이를 다음과 같이 바꿀 수 있습니다. param을 사용하지 않고 직접 속성을 지정하는 것을 확인할 수 있습니다.
        val applyParam = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.CENTER_HORIZONTAL
            weight = 1f
            topMargin = 100
            bottomMargin = 100
        }
    }


    /*
    run()
    run() 함수는 인자가 없는 익명 함수처럼 동작하는 형태와 객체에서 호출하는 형태 총 두 가지가 있습니다.

    객체 없이 run() 함수를 사용하면 인자 없는 익명 함수처럼 사용할 수 있습니다. 이어지는 블럭 내에서 처리할 작업들을 넣어줄 수 있으며, 일반 함수와 마찬가지로 값을 반환하지 않거나 (Unit) 특정 값을 반환할 수도 있습니다.
    해당 함수의 형태 및 원문 설명은 다음과 같습니다.

    fun <R> run(block: () -> R): R
    Calls the specified function block and returns its result.

    객체에서 run() 함수를 호출할 경우, 호출하는 객체를 이어지는 블록의 리시버로 전달하고, 블록의 결과값을 반환합니다.
    해당 함수의 형태 및 원문 설명은 다음과 같습니다.

    fun <T, R> T.run(block: T.() -> R): R
    Calls the specified function block with this value as its receiver and returns its result.


    사용 예
    객체에서 이 함수를 호출하는 경우 객체를 리시버로 전달받으므로, 특정 객체의 메서드나 필드를 연속적으로 호출하거나 값을 할당할 때 사용합니다.

    apply()와 적용 예가 유사하지만, apply()는 새로운 객체를 생성함과 동시에 연속된 작업이 필요할 때 사용하고 run()은 이미 생성된 객체에 연속된 작업이 필요할 때 사용한다는 점이 조금 다릅니다.

    다음은 AppCompatActivity의 액션바 속성을 연속하여 변경하는 예를 보여줍니다. run()메서드에서도 안전한 호출(Safe Calls)를 사용할 수 있으며, 이를 통해 액션바가 null이 아닐 경우에만 블록 내 명령들이 실행됩니다.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(android.R.drawable.ic_dialog_alert)
        }
    }


    /*
    with()
    with() 함수는 인자로 받는 객체를 이어지는 블록의 리시버로 전달하며, 블록의 결과값을 반환합니다.

    해당 함수의 형태 및 원문 설명은 다음과 같습니다.

    fun <T, R> with(receiver: T, block: T.() -> R): R
    Calls the specified function block with the given receiver as its receiver and returns its result.

    with() 함수는 사실상 run()함수와 기능이 거의 동일하며, 리시버로 전달할 객체가 어디에 위치하는지만 다릅니다.
    run() 함수는 with() 함수를 좀 더 편리하게 사용하기 위해 let()함수와 with()함수를 합쳐놓은 형태라 보아도 무방합니다. 즉, run() 함수는 다음과 같이 표현할 수 있습니다.
     */
    fun withSample() {
        supportActionBar?.let {
            with(it) {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(android.R.drawable.ic_dialog_alert)
            }
        }
    }

    // 이와 같이 기능은 똑같지만 run() 함수가 안전한 호출(Safe Calls)을 지원하는데 반해, with() 함수는 이를 자체적으로 지원하지 않으므로 특별한 경우가 아니라면 run() 함수를 더 자주 사용하게 될 것으로 보입니다.
}