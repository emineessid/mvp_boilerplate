package com.interco.e.daggerrxretrofit.ui

import com.comuto.authent.data.model.ApiToken
import com.interco.e.daggerrxretrofit.ui.splash.SplashPresenter
import com.interco.e.daggerrxretrofit.ui.splash.SplashRepesotery
import com.interco.e.daggerrxretrofit.ui.splash.SplashViewInterface
import com.interco.e.daggerrxretrofit.utils.TrampolineSchedulerRule
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

/**
 * Created by Emine on 04/01/2019.
 */
class SplashActivityPresenterTest {

    @Rule
    @JvmField
    val schedulers = TrampolineSchedulerRule()


    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Mock
    private var splashView: SplashViewInterface? = null

    @Mock
    lateinit var splashRepesotery: SplashRepesotery

    @InjectMocks
    lateinit var splashPresenter: SplashPresenter

    lateinit var appitoken: ApiToken

    @Before
    fun setUp() {
        appitoken = ApiToken("any", "any", 200, 200, "emine", null)

    }


    @Test
    fun ` Request  token with an Error should display message `() {
        //given
        given(splashRepesotery.requestToken()).willReturn(Single.error(Throwable(" Random Error ")))
        //when
        splashPresenter.requestValidToken()
        //then
        then(splashView).apply {
            should()!!.toggleLoading(true)
            should()!!.toggleLoading(false)
            should()!!.showMessageError(ArgumentMatchers.anyString())
        }
    }

    @Test
    fun ` Request valide token should Call navigate To home `() {
        //given
        given(splashRepesotery.requestToken()).willReturn(Single.just(appitoken))
        //when
        splashPresenter.requestValidToken()
        //then
        then(splashView).apply {
            should()!!.toggleLoading(true)
            should()!!.toggleLoading(false)
            should()!!.navigateTohome()

        }
    }
}


