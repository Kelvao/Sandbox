package digital.gok.sandbox

import digital.gok.sandbox.data.remote.WebServiceClient
import digital.gok.sandbox.data.remote.datasource.SandboxDataSource
import digital.gok.sandbox.data.repository.SandboxRepository
import digital.gok.sandbox.presentation.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val webServiceModules = module {
    single { WebServiceClient().webService }
}

val dataSourceModules = module {
    single { SandboxDataSource(get()) }
}

val repositoryModules = module {
    single { SandboxRepository(get()) }
}

val viewModels = module {
    viewModel { HomeViewModel(get()) }
}

val applicationModules = listOf(
    webServiceModules,
    dataSourceModules,
    repositoryModules,
    viewModels
)