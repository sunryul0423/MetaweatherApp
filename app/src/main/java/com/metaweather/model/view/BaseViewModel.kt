package com.metaweather.model.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * 공통적으로 필요한 변수,함수 정의한 ViewModel
 * @author SR.Park
 * @since 2019.06.26
 * @property compositeDisposable Rx 구독 객체
 * @property networkError 네트워크 에러 메세지
 * @property showProgress 프로그래스 실행여부
 * @property addDisposable RxJava2를 이용한 메모리 할당
 * @property onCleared RxJava2를 이용한 메모리 해제
 */
abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    protected val throwable = MutableLiveData<Throwable>()
    protected val isProgress = MutableLiveData<Boolean>().apply { value = false }
    val networkError: LiveData<Throwable> get() = throwable
    val showProgress: LiveData<Boolean> get() = isProgress

    /**```
     * 비동기 작업인 Rx와 API 통신은 CompositeDisposable 관리
     * | ViewModel | Activity Lifecycle |과 연결되어 메모리릭 현상 예방
     * ```
     */
    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    /**
     * AAC Lifecycle onDestroy 호출 시 CompositeDisposable 비워줌으로 메모리 관리
     */
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}