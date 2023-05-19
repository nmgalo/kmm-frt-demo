import SwiftUI
import shared


struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

     var body: some View {
         NavigationView {
             listView()
             .navigationBarTitle("Github repositories")
             .navigationBarItems(trailing:
                 Button("Reload") {
                     self.viewModel.loadRepos()
             })
         }
     }

     private func listView() -> AnyView {
             switch viewModel.repos {
                 case .loading:
                     return AnyView(Text("Loading...").multilineTextAlignment(.center))
                 case .result(let repos):
                     return AnyView(List(repos) { repo in
                         GithubRepositoryRow(repo: repo)
                     })
                 case .error(let description):
                     return AnyView(Text(description).multilineTextAlignment(.center))
             }
         }
}

extension ContentView {
	enum LoadableRepositories {
        case loading
        case result([GithubRepositoryModel])
        case error(String)
    }

    @MainActor
    class ViewModel: ObservableObject {
        let sdk: GithubSDK
        @Published var repos = LoadableRepositories.loading

        init(sdk: GithubSDK) {
            self.sdk = sdk
            self.loadRepos()
        }

        func loadRepos() {
            Task {
                do {
                    self.repos = .loading
                    let repos = try await sdk.getRepos()
                    self.repos = .result(repos)
                } catch {
                    self.repos = .error(error.localizedDescription)
                }
            }
        }
    }
}

extension GithubRepositoryModel: Identifiable { }
