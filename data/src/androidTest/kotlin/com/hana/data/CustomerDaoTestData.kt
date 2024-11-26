package com.hana.data

import com.hana.domain.model.Address
import com.hana.domain.model.Company
import com.hana.domain.model.Customer
import com.hana.domain.model.Geo

val customerDaoTestData: List<Customer> = listOf(
    Customer(
        id = 1,
        name = "Leanne Graham",
        username = "Bret",
        email = "Sincere@april.biz",
        phone = "1-770-736-8031 x56442",
        website = "hildegard.org",
        address = Address(
            street = "Kulas Light",
            suite = "Apt. 556",
            city = "Gwenborough",
            zipcode = "92998-3874",
            geo = Geo(
                lat = "37.3159",
                lng = "81.1496"
            )
        ),
        company = Company(
            companyName = "Romaguera-Crona",
            catchPhrase = "Multi-layered client-server neural-net",
            bs = "harness real-time e-markets"
        )
    ),
    Customer(
        id = 2,
        name = "Ervin Howell",
        username = "Antonette",
        email = "Shanna@melissa.tv",
        phone =  "010-692-6593 x09125",
        website = "anastasia.net",
        address = Address(
            street = "Victor Plains",
            suite =  "Suite 879",
            city = "Wisokyburgh",
            zipcode = "90566-7771",
            geo = Geo(
                lat = "43.9509",
                lng = "34.4618"
            )
        ),
        company = Company(
            companyName = "Deckow-Crist",
            catchPhrase = "Proactive didactic contingency",
            bs = "synergize scalable supply-chains"
        )
    )
)