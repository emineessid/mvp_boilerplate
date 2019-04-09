package com.interco.e.daggerrxretrofit.ui.search

import com.interco.e.daggerrxretrofit.utils.TrampolineSchedulerRule
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class SearchActivityPresenterTest {

    @Rule
    @JvmField
    val schedulers = TrampolineSchedulerRule()


    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!


    @Mock
    private var searchActivityViewInterface: SearchActivityViewInterface? = null

    @Mock
    lateinit var searchActivityRepesotery: SearchActivityRepesotery

    @InjectMocks
    lateinit var searchActivityPresenter: SearchActivityPresenter


    @Test
    fun ` Request  token with an Error should display message `() {
        //given
        BDDMockito.given(searchActivityRepesotery.requestLocations("g", "p")).willReturn(Observable.error(Throwable(" une erreur")))
        //when
        searchActivityPresenter.getLocations("g", "p")
        //then
        BDDMockito.then(searchActivityViewInterface).apply {
            should()!!.toggleLoading(true)
            should()!!.toggleLoading(false)
            should()!!.showErrorGetLocations(ArgumentMatchers.anyString())
        }
    }


    @Test
    fun ` empty `() {
        //given

        //when
        searchActivityPresenter.getLocations("", "")
        //then
        BDDMockito.then(searchActivityViewInterface).apply {

            should()!!.showErrorGetLocations(" Epmty data !")
        }
    }


}