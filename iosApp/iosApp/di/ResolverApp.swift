//
//  ResolverApp.swift
//  iosApp
//
//  Created by Jose Harold on 21/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared

protocol ResolverProtocol {}

protocol ResolverAppProtocol: ResolverProtocol {
    
    @MainActor func resolve() -> AudioListPageViewModel
}

class ResolverApp: ResolverAppProtocol {}

extension ResolverAppProtocol {
    
    @MainActor
    func resolve() -> AudioListPageViewModel {
        
        AudioListPageViewModel(
            //usecasexxx: KoinApplication.shared.inject(),
        )
    }
}
