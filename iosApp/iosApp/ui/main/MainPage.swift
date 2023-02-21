//
//  MainPage.swift
//  iosApp
//
//  Created by Jose Harold on 20/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MainPage: View {
    
    @StateObject var viewModel: MainPageViewModel
    
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


extension MainPage {
    private enum Localizable {
        static let pageTitle = "Skoovin´"
    }
    
}

struct MainPage_Previews: PreviewProvider {
    static var previews: some View {
        
        MainPage(viewModel: MainPageViewModel())
    }
}
