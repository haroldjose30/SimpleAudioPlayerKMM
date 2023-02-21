//
//  KoinApplication.swift
//  iosApp
//
//  Created by Jose Harold on 20/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared

typealias KoinApplication = Koin_coreKoinApplication
typealias Koin = Koin_coreKoin


extension KoinApplication {
    static let shared = companion.start()
    
    @discardableResult
    static func start() -> KoinApplication {
        shared
    }
}

