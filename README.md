**Material Listing UI**

**Used Tech stack**

Compose, RoomDB, Retrofit, Dagger Hilt, Coroutine and other Compose library.


**Implementation Logic**

1. On App launch (No Network) -> Show empty state -> Show "Enable mobile data1" message -> Enable mobile data ->  Click "Refresh" Button to fetch data -> Show listing.
2. On App launch -> Fetch data -> Show Ui Listing.
3. On Pull to refresh -> Fetch data from api -> Insert list into database -> collect the holding list using Flow -> Present the UI Listing.


**Test Case**
1. Holding color based on value.
2. Total value calculation
3. Total Investment Calculation.
4. Today's PNL Calculation.
