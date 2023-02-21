//
//  KoinApplication+UseCase.swift
//  iosApp
//
//  Created by Jose Harold on 20/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

extension KoinApplication {
    
    private static let keyPaths: [PartialKeyPath<Koin>] = [
        //\.createFamilyListUseCase,
    ]
    
    static func inject<T>() -> T {
        
        shared.inject()
    }
    
    func inject<T>() -> T {
        
        for partialKeyPath in Self.keyPaths {
            guard let keyPath = partialKeyPath as? KeyPath<Koin, T> else { continue }
            return koin[keyPath: keyPath]
        }
        
        fatalError("\(T.self) is not registered with KoinApplication")
    }
}
