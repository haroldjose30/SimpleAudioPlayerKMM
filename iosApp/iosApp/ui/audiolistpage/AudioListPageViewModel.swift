//
//  MainPageViewModel.swift
//  iosApp
//
//  Created by Jose Harold on 20/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared


class AudioListPageViewModel: ObservableObject {
    
    @Published var audioEntryList:[AudioEntry] = []
    
    init(
        updateAudioUseCase: UpdateAudioUseCase,
        getAllFamilyListUseCase: GetAllFamilyListUseCase,
        updateFamilyListUseCase: UpdateFamilyListUseCase,
        deleteFamilyListUseCase: DeleteFamilyListUseCase
    ) {
        
        self.createFamilyListUseCase = createFamilyListUseCase
        self.getAllFamilyListUseCase = getAllFamilyListUseCase
        self.updateFamilyListUseCase = updateFamilyListUseCase
        self.deleteFamilyListUseCase = deleteFamilyListUseCase
    }
    
}
