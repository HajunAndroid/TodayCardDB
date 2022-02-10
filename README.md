# TodayCard DB 테이블 개선
### 테이블 개선 사유
##### 기존에는 단일 테이블에 모든 데이터를 저장했습니다. 그 결과 데이터 중복 저장 발생 및 검색 성능 하락 등의 문제가 발생했습니다. 또한 A/B Test와 같은 데이터 기반 사용자 UX 개선 작업에 필요한 데이터 수집에 있어 한계에 도달했습니다. 즉, 기존 테이블에 기반해서는 앱에 새로운 기능을 추가하고 확장하는 데 어려움이 발생했습니다. 따라서 검색 성능을 향상하고 앱의 원활한 확장성을 위해 TodayCard DB 테이블을 개선했습니다. 그리고 안드로이드 권장 아키텍처인 MVVM을 위해 기존의 DBHelper가 아닌 Room을 사용했습니다.
### **[기존 테이블]**
<img src="https://user-images.githubusercontent.com/87768226/153307898-459969c7-1b78-473d-aa5d-bcb32e32bace.png" width="30%" height="30%">
<img src="https://user-images.githubusercontent.com/87768226/153307962-0be032eb-fc76-48c9-a608-7bd1dbdeb7f3.png" width="15%" height="15%">

### **[개선 테이블]**
<img src="https://user-images.githubusercontent.com/87768226/153307993-128ebb56-c245-4a68-9c2d-6741c8dcf22e.png" width="40%" height="40%">
<img src="https://user-images.githubusercontent.com/87768226/153308012-4494fa88-5d8e-4aae-93e3-4c7cc7bc8e0b.png" width="40%" height="40%">

##### - 개선 후 총 4개의 테이블이 생성되었습니다. Card, DailySpend, PayCard, PayCash. 각 테이블을 Android에 내장되어 있는 SQLiteDatabase를 이용해 생성 및 사용했습니다. 이때 Room으로 DB를 핸들링했습니다.
##### - Card 테이블은 결제한 카드 정보를 담고 있습니다. 결제 카드를 추가하고 삭제할 수 있습니다(기능 확장1). 그리고 결제한 결제한 카드별로 사용 내역을 조회할 수 있습니다(기능 확장2).
##### - PayCard 테이블은 카드로 결제한 내역을 담고 있습니다. PayCash 테이블은 현금으로 결제한 내역을 담고 있습니다. 즉, 카드 결제와 현금 결제를 따로 저장합니다. 따라서 연말정산 등을 보조할 수 있는 현금 결제 내역 조회 기능을 추가할 수 있습니다(기능 확장3).
##### - DailySpend 테이블은 날짜별로 당일 총 사용 금액을 담고 있습니다. '오늘의 카드' 앱의 많은 Activity/Fragment에서 DailySpend 테이블의 total 칼럼으로 빠르게 당일 총 사용 금액을 조회할 수 있습니다(조회 속도 개선).
#### Card 테이블 및 CardDAO
<img src="https://user-images.githubusercontent.com/87768226/153308088-f345eef3-9b16-4402-981c-612e02b6fe38.png" width="20%" height="20%">
<img src="https://user-images.githubusercontent.com/87768226/153310747-62e27e5f-1096-4328-afd0-21555671879d.JPG" width="55%" height="55%">

#### DailySpend테이블 및 DailySpendDAO
<img src="https://user-images.githubusercontent.com/87768226/153308095-7b9c8cc8-6b0a-45e6-b79a-6d7c579ab469.png" width="25%" height="25%">
<img src="https://user-images.githubusercontent.com/87768226/153308098-c68021cb-698c-443e-b353-8d2ccc5a843d.png">

#### PayCard 테이블 및 PayCardDAO
<img src="https://user-images.githubusercontent.com/87768226/153308101-9ae142d0-c4a9-4cae-aee9-69297899c564.png" width="55%" height="55%">
<img src="https://user-images.githubusercontent.com/87768226/153308111-ea89332e-860d-40e3-a2cb-2e436053d739.png" width="55%" height="55%">

#### PayCash 테이블 및 PayCashDAO
<img src="https://user-images.githubusercontent.com/87768226/153308119-7dc8593a-03a9-48ee-81f6-1cb85fa41580.png">
<img src="https://user-images.githubusercontent.com/87768226/153308127-56100641-ed14-4547-8d84-f896c12d8cdd.png" width="57%" height="57%">

#### Query 사용표
<img src="https://user-images.githubusercontent.com/87768226/153308361-c88a1627-ec77-471a-a122-f475d36d23c9.JPG">

###### (Row: Activity 및 Fragment, Col: 테이블)
##### - 위 표에서 알 수 있듯 DailySpend 테이블의 total 칼럼(당일 총 사용 금액)을 빈번하게 조회하고 있습니다. 기존 테이블에서는 total 값을 구할 때마다 전체 테이블을 순회하며 조건에 맞는 price를 합쳐야 했습니다. 하지만 이제는 DailySpend의 selectTotal 쿼리만으로 total 값을 빠르게 조회할 수 있습니다. 또한 PayCard 테이블과 PayCash 테이블을 분리했습니다. 따라서 현금 사용 내역만 따로 조회가 가능해 이를 활용한 기능을 추가할 수 있습니다. 예를 들어, 연말정산 중 현금 사용 내역 조회가 필요할 경우, 카드 사용 내역을 제외한 현금 사용 내역만 조회할 수 있습니다.
##### - 안드로이드 앱은 사용자의 활동 로그를 추적할 수 있습니다. 앱 사용자가 어떤 기능을 많이 사용하는지, 앱의 핵심 비즈니스가 수행되는 과정에서 사용자가 어떤 행동을 하는지 분석할 수 있습니다. 이를 분석해 앱에 기능을 변경하거나 새로운 기능을 추가할 수 있습니다. 따라서 앱의 수정 및 확장 가능성을 고려해 초기 DB 테이블을 설계해야 합니다. 현재 비즈니스 로직만 고려해 DB를 설계한다면, 사용자 데이터 분석에 기반해 향후 앱에 변화가 있을 경우 제약이 생깁니다.
##### - 즉, 기존 단일 테이블을 Card, DailySpend, PayCard, PayCash 총 4개의 테이블로 개선한 결과, 빈번하게 검색했던 '당일 총 사용 금액'의 검색 속도가 향상되었습니다. 또한 향후 추가될 앱의 새로운 기능에 유연하게 대응할 수 있습니다.
