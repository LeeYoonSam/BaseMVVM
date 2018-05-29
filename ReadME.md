# MVVM(Model-View-ViewModel)


## MVVM의 주요 패턴

* View - ViewModel에게 유저의 동작을 알림

* ViewModel - View에 관련된 데이터 스트림을 드러냄

* DataModel - 데이터 소스를 추상화


MVVM패턴은 View와 ViewModel의 양방향 데이터 바인딩을 지원합니다.
그리고 View와 ViewModel은 many-to-one 관계가 있다고 할 수 있습니다.
View는 ViewModel의 참조자를 가지게 되지만, ViewModel은 View에대한 정보가 전혀 없게 됩니다.
데이터 소비자(consumer)는 데이터 공급자(producer)에 대해서 알아야 하지만 데이터 공급자(이 경우엔 ViewModel)는 데이터 소비자가 누구인지 알지도, 신경쓰지도 않습니다.


### DataModel
DataModel은 데이터를 이벤트 스트림을 통해서 소비 가능하게(consumable) 노출시킵니다. RxJava의 Observable 을 이용해서 말이죠. 그것은 네트워크 계층이나 데이터베이스 또는 shared preferences 등의 다양한 소스로 부터 데이터를 구성합니다. 그리고 쉽게 소비가능한 데이터를 누구든지 필요한 것들에 노출시킵니다. DataModel은 모든 비지니스 로직을 가지고 있게 됩니다.
단일 책임 법칙(single responsibility principle)에 대해서 우리가 강조하는 것은 나중에 DataModel을 만들도록 이끌 것입니다. 예를들어, 출력값을 API 서비스와 데이터베이스 계층으로부터 받아와 구성하는 ArticleDataModel이 있다고 합시다. 이 DataModel은 age filter를 적용하여 최근의 뉴스들이 데이터베이스로부터 받아지도록 하기 위해서 비지니스 로직을 다루게 됩니다.


### ViewModel
ViewModel은 앱의 View를 위한 model입니다. View가 추상화 된 것이죠. ViewModel은 DataModel로부터 필요한 데이터를 받고, UI 로직을 적용한 뒤 View가 소비하는 데이터를 노출시킵니다. DataModel과 비슷하게, ViewModel은 Observable을 통해서 데이터를 노출시킵니다.
우리는 이를 적용하면서 ViewModel에 대한 두가지를 배웠습니다:
ViewModel은 단순히 어떤 이벤트가 아닌 View의 상태를 노출시켜야 합니다. 예를들어, 만약 User 객체의 이름과 이메일을 나타내야 한다면, 두개의 스트림을 만드는 것 보다는, DisplayableUser 을 만들어 이 두가지를 하나로 감싸는 것이 더 좋습니다. 이 스트림이 매번 이름과 이메일이 바뀔때마다 정보를 내보낼 것입니다. 이런 방법으로, 우리는 View가 항상 User 의 상태를 표시하도록 하였습니다.
우리는 유저의 모든 동작들이 ViewModel을 통하도록 만들었고, 모든 뷰의 로직들이 ViewModel에 있도록 하였습니다.
이 [두가지 토픽을위해 MVVM + RxJava 를 사용하는 중에 자주하는 실수](http://upday.github.io/blog/mvvm_rx_common_mistakes/) 라는 블로그 포스팅을 작성하였으니 확인해 보세요.


### View
View는 앱에서 유저 인터페이스의 실질적인 부분입니다. Activity, Fragment 안드로이드 View 도 이 View가 될 수 있습니다.
이 View의 onResume() onPause() 에서 이벤트 소스를 바인딩, 언바인딩 하게됩니다.


```
private final CompositeSubscription mSubscription = new CompositeSubscription();

@Override
public void onResume() {
    super.onResume();
    mSubscription.add(mViewModel.getSomeData()
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(this::updateView,
                                this::handleError));
}

@Override
public void onPause() {
    mSubscription.clear();
    super.onPause();
}
```


만약 MVVM의 View가 커스텀 안드로이드 View 라면, 그 바인딩을 생성자에서 해야 할 것입니다. 메모리 누수로 이어지는, subscription이 계속 남아있게 되는 경우를 피하기 위해서, 언바인딩이 onDetachedFromWindow 에서 이루어집니다.

```
private final CompositeSubscription mSubscription = new CompositeSubscription();

    public MyView(Context context, MyViewModel viewModel) {
        ...
        mSubscription.add(mViewModel.getSomeData()
                         .observeOn(AndroidSchedulers.mainThread())
                         .subscribe(this::updateView,
                                    this::handleError));
    }

    @Override
    public void onDetachedFromWindow() {
        mSubscription.clear();
        super.onDetachedFromWindow();
    }
}
```



## Model-View-ViewModel 클래스들의 테스트 가능성
Model-View-ViewModel을 사랑하게된 가장 주된 이유는 테스트하기 쉽다는 점이었습니다.

### DataModel
제어 반전 패턴 (inversion of control pattern)의 사용이 우리의 코드에 많은 부분을 차지하게 되었고, Android 클래스들을 덜 사용함에 따라 DataModel의 테스트를 구현하는 것이 용이해졌습니다.

### ViewModel
우리는 View와 유닛 테스트, 이 둘을 ViewModel로 부터 받은 데이터를 소비하는 것들로 여기며 구현하였습니다.
ViewModel은 UI나 Android 클래스들로부터 완전히 분리되었고, 결국 유닛 테스트 하기 쉽게 되었습니다.

ViewModel이 단순히 DataModel로부터 받은 데이터를 노출시키는 역할을 하는 예시를 살펴봅시다:

```
public class ViewModel {
    private final IDataModel mDataModel;

    public ViewModel(IDataModel dataModel) {
        mDataModel = dataModel;
    }

    public Observable<Data> getSomeData() {
        return mDataModel.getSomeData();
    }
}
```


이 경우에 ViewModel을 위한 테스트를 구현하는 것은 아주 쉽습니다. Mockito의 도움을 받아, DataModel을 모킹하고(mocking) 반환하는 데이터를 조정합니다.
그다음, getSomeData() 를 통해 얻은 Observable 을 구독할때(subscribe), 예상되는 데이터가 내보내졌는지를 확인합니다.

```
public class ViewModelTest {

    @Mock
    private IDataModel mDataModel;

    private ViewModel mViewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mViewModel = new ViewModel(mDataModel);
    }

    @Test
    public void testGetSomeData_emitsCorrectData() {
        SomeData data = new SomeData();
        Mockito.when(mDataModel.getSomeData()).thenReturn(Observable.just(data));
        TestSubscriber<SomeData> testSubscriber = new TestSubscriber<>();

        mViewModel.getSomeData().subscribe(testSubscriber);

        testSubscriber.assertValue(data);
    }
}
```

만약 ViewModel이 안드로이드 클래스에 접근할 필요가 있으면, Provider 를 호출하는 wrapper를 생성합니다.
예를들어, 안드로이드 resource들에 접근하기 위해서 IResourceProvider 를 생성하고, 이 객체가 String getString(@StringRes final int id) 와 같은 메소드들을 노출하게 됩니다.
IResourceProvider 의 구현은 Context 의 참조자를 가지고 있게 되지만, ViewModel은 단지 IResourceProvider를 가지고 있게 되는 것이죠.
위에 언급하였듯이, 그리고 자주하는 실수에 대한 블로그 포스트에 남겼듯이, 우리는 model 객체를 데이터의 상태를 담기 위해서 만듭니다.
이렇게 하는 것은 훨씬더 높은 수준의 테스트 가능성을 가지게 해주며, ViewModel로 부터 내보내진 데이터를 컨트롤 할 수있도록 해줍니다.



### View
UI 로직이 최소화 되었기 때문에, View는 Espresso로 테스트하기 쉽게 되었습니다.
우리는 DaggerMock과 MockWebServer과 같은 라이브러리들을 가지고 UI 테스트의 안정성을 개선하였습니다.

---

## 출처
[안드로이드 Architecture 패턴 part3: MVVM](https://medium.com/nspoons/안드로이드-architecture-패턴-part-3-모델-뷰-뷰모델-model-view-viewmodel-688f8d93d557)# BaseMVVM
