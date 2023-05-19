//
//  GithubRepositoryRow.swift
//  iosApp
//
//  Created by Nika Mgaloblishvili on 19.05.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GithubRepositoryRow: View {
    var repo: GithubRepositoryModel

    var body: some View {
        HStack() {
            VStack(alignment: .leading, spacing: 10.0) {
                Text("Repo name: \(repo.name)")
            }
            Spacer()
        }
    }
}

extension GithubRepositoryRow {
}
