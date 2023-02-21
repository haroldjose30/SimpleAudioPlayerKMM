//
//  MainPage.swift
//  iosApp
//
//  Created by Jose Harold on 20/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AudioListPage: View {
    
    @StateObject var viewModel: AudioListPageViewModel
    
    var body: some View {
        VStack {
            Text(Localizable.pageTitle)
                .font(.title)
            
            ForEach(viewModel.audioEntryList, id: \.self) { audioEntry in
                Text(audioEntry)
                    .padding()
                    .background(Color.blue)
            }
           
            Spacer()
            
        }
    }
}


extension AudioListPage {
    private enum Localizable {
        static let pageTitle = "Skoovin´"
    }
    
}

struct AudioListPage_Previews: PreviewProvider {
    static var previews: some View {
        
        AudioListPage(viewModel: AudioListPageViewModel())
    }
}
