package br.com.williamfranco.composewithmvvm.src.features.settings.view_models

import br.com.williamfranco.composewithmvvm.src.fakes.FakeSettingRepository
import br.com.williamfranco.composewithmvvm.src.features.settings.models.SettingModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SettingViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `observes initial theme from repository`() = runTest {
        val viewModel = SettingViewModelImpl(
            FakeSettingRepository(SettingModel(isDarkTheme = false)),
        )

        advanceUntilIdle()

        assertFalse(viewModel.state.value.isDarkTheme)
    }

    @Test
    fun `updates theme when changed`() = runTest {
        val viewModel = SettingViewModelImpl(FakeSettingRepository())

        advanceUntilIdle()
        viewModel.changeTheme(isDarkTheme = true)
        advanceUntilIdle()

        assertTrue(viewModel.state.value.isDarkTheme)
    }
}
