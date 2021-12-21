/*** SIGNATURES ***/

abstract sig User {
	id: one Int,
	name: one Int,
	password: one Int,
	surname: one Int,
	mail : one Int
}
{ 
	id > 0 and
	name > 0 and
	password > 0 and
	surname > 0 and 
	mail > 0
}

sig PolicyMaker extends User {}


sig Farmer extends User {
	farm : one Farm,
	request : set Request,
	discussion : set Discussion
}


sig Agronomist extends User {
	area: lone Area,
	dailyPlan: set DailyPlan
}

sig Area {
	id: one Int,
	name: one Int,
	forecast: set Forecast,
	farm: some Farm
}
{ 
	id > 0
}

sig Forecast {
	id: one Int,
	classification: one Int,
	day: one DateTime
}
{
	id > 0
}

sig Farm { 
	id: one Int,
	address: one Int,
	dimension: one Int,
	farmProduction: set FarmProduction,
	humidityOfSoil: set HumidityOfSoil,
	waterData : set WaterData
}
{
	id > 0
}

sig FarmProduction {
	id: one Int,
	typeOfProduction: one Int,
	amountOfProduction: one Int,
	year: one DateTime,
	problemFaced: one Int,
}
{ 
	id > 0 and
	typeOfProduction >0 and
	amountOfProduction >= 0
}

sig HumidityOfSoil { 
	id: one Int,
	humidity: one Int,
	day: one DateTime,
}
{
	id > 0 and
	humidity >= 0
}

sig WaterData {
	id: one Int,
	quantity: one Int,
	day: one DateTime,
}
{
	id > 0 and
	quantity >= 0
}

sig DailyPlan {
	id: one Int,
	date: one DateTime,
	excecution: one Int, // 0 => NEW, 1 => PARTIALLYDONE, 2 => DONE
	visit: set Visit
}{
	id > 0 and
	excecution >= 0 and 
	excecution <=2
}

sig Visit {
	id: one Int,
	date: one DateTime,
	comment: one Int,
	visitState: one Int, 
	farm: one Farm,
}{
	id > 0 and
	visitState >= 0 and // 0 => NEW, 1 => DONE, 2 => NOTDONE
	visitState <= 2
}

sig Request {
	id: one Int,
	comment: one Int,
	typeOfRequest: one Int, // 0 => HELP, 1 => SUGGESTION
	date: one DateTime,
	requestState: one Int, // 0 => OPEN, 1 => CLOSED
	answer: set Answer
}{
	id > 0 and
	requestState >= 0 and 
	requestState <= 1 and 
	typeOfRequest >=0 and 
	typeOfRequest <=1
}



sig Answer {
	id: one Int,
	comment: one Int,
	typeOfRequest: one Int,  // 0 => HELP, 1 => SUGGESTION
	date: one DateTime,
	farmer: lone Farmer,
	agronomist: lone Agronomist
}{
	id > 0 and
	typeOfRequest >=0 and 
	typeOfRequest <=1
}

sig Discussion {
	id: one Int,
	date: one DateTime,
	title: one Int,
	post: some Post
}
{
	id > 0
}



sig Post {
	id: one Int,
	farmer: one Farmer,
	date: one DateTime,
	comment: one Int,
}
{
	id > 0
}

sig Time{
	hour:one Int,
	minute: one Int
}
{  // correct constraints not inserted to avoid long computation
	hour >0  and
	minute >0 
}


sig Date{
	day: one Int,
	month: one Int,
	year: one Int
}
{  // correct constraints not inserted to avoid long computation
	day >0 and 
	month >0 and
	year > 0
}


sig DateTime{
	date: Date,
	time: Time
}

/*** END  SIGNATURES ***/




/*** FUNCTION ***/
//Functions return a value

fun compareTime(t1 : DateTime, t2 : DateTime) : one Int { 
	t1.date.year < t2.date.year implies -1 else (
	t1.date.year > t2.date.year implies 1 else (
	t1.date.month < t2.date.month implies -1 else (
	t1.date.month > t2.date.month implies 1 else (
	t1.date.day < t2.date.day implies -1 else (
	t1.date.day > t2.date.day implies 1 else (
	t1.time.hour < t2.time.hour implies -1 else (
	t1.time.hour > t2.time.hour implies 1 else (
	t1.time.minute < t2.time.minute implies -1 else (
	t1.time.minute > t2.time.minute implies 1 else 0
	)))))))))
}

fun compareDay(t1 : DateTime, t2 : DateTime) : one Int {
	t1.date.year < t2.date.year implies -1 else (
	t1.date.year > t2.date.year implies 1 else (
	t1.date.month < t2.date.month implies -1 else (
	t1.date.month > t2.date.month implies 1 else (
	t1.date.day < t2.date.day implies -1 else (
	t1.date.day > t2.date.day implies 1 else 0
	)))))
}

fun compareYear(t1 : DateTime, t2 : DateTime) : one Int {
	t1.date.year < t2.date.year implies -1 else (
	t1.date.year > t2.date.year implies 1 else 0
	)
}

/*** END FUNCTION ***/




/*** FACTS ***/
//Facts are considered true from the Alloy analyzer

/***Check Unique Id ***/
fact userUniqueId {
	no disjoint u1,u2 : User |
	u1.id = u2.id
}

fact areaUniqueId {
	no disjoint a1, a2 : Area |
	a1.id = a2.id
}

fact farmUniqueId{
	no disj f1,f2: Farm | 
	f1.id = f2.id
}

fact discussionUniqueId{
	no disj d1,d2: Discussion | 
	d1.id = d2.id
}

fact postUniqueId{
	no disj p1,p2: Post | 
	p1.id = p2.id
}

fact requestUniqueId{
	no disj r1,r2: Request | 
	r1.id = r2.id
}

fact answerUniqueId{
	no disj a1,a2:Answer | 
	a1.id = a2.id
}

fact dailiyPlanUniqueId{
	no disj d1,d2: DailyPlan | 
	d1.id = d2.id
}

fact visitsUniqueId{
	no disj v1,v2: Visit | 
	v1.id = v2.id
}

fact forecastUniqueId{
	no disj f1,f2: Forecast | 
	f1.id = f2.id
}

fact waterDataUniqueId{
	no disj w1,w2: WaterData | 
	w1.id = w2.id
}

fact humidityOfSoilUniqueId{
	no disj h1,h2: HumidityOfSoil | 
	h1.id = h2.id
}

fact farmProductionUniqueId{
	no disj f1,f2: FarmProduction|
	 f1.id = f2.id
}
/***End Check Unique Id ***/





/*** User Facts***/
fact userUniqueMail {
	no disjoint u1, u2 : User |
	u1.mail = u2.mail
}

/*** End User Facts***/





/*** Area and Farms Facts***/
//Two different farmers cannot have the same farm
fact eachFarmerOneFarm {
	no disjoint f1, f2 : Farmer |
	f1.farm.id = f2.farm.id
}

fact eachFarmMustBeAssociatedToFarmer {
	all f : Farm |
	one fr: Farmer |
	f in fr.farm
}

//Two different agronomists cannot have the same area
fact eachAgronomistOneArea{  
	no disjoint a1, a2 : Agronomist | 
	one a: Area |
	a1.area = a and a2.area = a
} 

fact farmUniqueAddress {
	no disjoint f1, f2 : Farm |
	 f1.address = f2.address
}

//Two different areas cannot contain the same farm
fact eachAreaContainsDifferentFarms {
	no disjoint a1, a2 : Area |
	a1.farm.id = a2.farm.id
}

fact eachFarmMustBeInAnArea {
	all f : Farm |
	one a : Area |
	f in a.farm
}


/*** End Area and Farms Facts***/


/*** Daily Plan Facts***/

fact eachDailyPlanBelongToASingleAgronomist {
	all d: DailyPlan | 
	no disj a1,a2: Agronomist |
	d in a1.dailyPlan and d in a2.dailyPlan
}

fact allDailyPlansBelongToSomeAgronomists {
	all d: DailyPlan |
	one a: Agronomist |
	d in a.dailyPlan
}

fact agronomistWithoutAreaMeansNoDailyPlan{
	all a: Agronomist |
	#a.area = 0 implies #a.dailyPlan = 0
}

//Two different DailyPlans cannot have the same visits
fact differentDailyPlanDifferentVisit {
	all disj d1,d2: DailyPlan |
	no v: Visit |
	v in d1.visit and v in d2.visit 
}

fact eachVisitBelongToADailyPlan{
	all v: Visit | 
	one d: DailyPlan | 
	v in d.visit
}

// All visits inside the same daily plan cannot target the same farm
fact allDailyPlansVisitsDifferentFarms {
	all d: DailyPlan, disj v1,v2: Visit |
	(v1 in d.visit and v2 in d.visit) implies v1.farm != v2.farm 
}

//All agronomists visit only farms in their area
fact agronomistsVisitOnlyFamsInHisArea{
	all a: Agronomist | all d: DailyPlan |
	d in a.dailyPlan implies d.visit.farm in a.area.farm
}

//TwoAgronomist cannot have the same visits
fact differentAgronomistHasDifferentVisits{
	no disjoint a1, a2 : Agronomist |
	one v: Visit |
	v in a1.dailyPlan.visit and v in a2.dailyPlan.visit 
}

//All completed dailyplans cannot have visits not completed
fact allDailyPlansDoneHaveOnlyDoneVisits{
	all d: DailyPlan |
	no v: Visit |
	d.excecution = 2 and (v.visitState = 0 or v.visitState = 2)
}

// All visits in  partially done dailyplans must be either completed or done or failed (not done)
fact partiallyDoneDailyPlanNoNewVisits{
	all d: DailyPlan |
	all v: Visit |
	(v in d.visit and d.excecution = 1) implies
	(v.visitState = 1 or v.visitState = 2) 
}

// A partially done dailyplan must contain at least one failed (not done) visit
fact partiallyDoneDailyPlanAtLeastOneNotDoneVisit{
	all d: DailyPlan |
	some v: Visit |
	d.excecution = 1 implies 
	v in d.visit and v.visitState = 2
}

//All visits in a completed dailyplan must be completed
fact doneDailyPlanDoneAllVisits{
	all d: DailyPlan |
	all v: Visit |
	(v in d.visit and d.excecution = 2) implies v.visitState = 1
}

//All new dailyplans must have only new visits
fact newDailyPlanNewVisits{
	all d: DailyPlan |
	all v: Visit |
	(v in d.visit and d.excecution = 0) implies 
	v.visitState = 0 
}

//All dailyplans contains only visits with the same day
fact visitsAreInTheSameDayOfDailyPlan{
	all d: DailyPlan |
	all v: Visit |
	v in d.visit implies compareDay[d.date, v.date] = 0
}


//Two visits in the same dailyplan cannot have the same time
fact allDailyPlansHaveNoOverlappingVisits{
	all a: Agronomist, disj v1,v2: Visit |
	v1 in a.dailyPlan.visit and v2 in a.dailyPlan.visit implies(
		compareTime[v1.date, v2.date] = 1 
		or
		compareTime[v1.date, v2.date] = -1 
	)
} 


//Two dailyplans of the same agronomist cannot be related to the same day
fact twoDailyPlanSameAgronomistNoOverlap{
	all a: Agronomist, disj d1,d2: DailyPlan |
	d1 in a.dailyPlan and d2 in a.dailyPlan implies(
		compareDay[d1.date, d2.date] = 1 
		or
		compareDay[d1.date, d2.date] = -1 
	)
} 

/*** End Daily Plan Facts***/




/*** Request Facts***/
fact eachRequestHasAFarmer {
	all r : Request |
	one f : Farmer | 
	r in f.request
}

//Two different farmers cannot have the same request
fact allRequestDifferentFarmer{
	all r: Request |
	no disj f1,f2: Farmer |
	r in f1.request and r in f2.request
}

fact allAnswersBelongToOneRequest{
	all a : Answer |
	one  r : Request |
	a in r.answer
}

fact eachAnswerOneReplier{
	all a: Answer |
	#(a.farmer + a.agronomist) = 1
}

fact allAnswersCannotHaveDifferentRequest {
	all a: Answer | 
	no disj r1,r2: Request |
	a in r1.answer and a in r2.answer

}

//All answers must be newer than their request
fact noAnswersOlderThanRequest{
	all r: Request |
	no a: Answer |
	a in r.answer and compareTime[a.date, r.date] = -1
}

//All answers must be of the same type as the request
fact allAnswerOfSameTypeAsRequests{
	all r: Request |
	all a: Answer |
	a in r.answer implies a.typeOfRequest = r.typeOfRequest
}

/*** End Request Facts***/


/*** Discussion Facts***/
//Two farmers cannot have the same discussion
fact differentFarmersCannotHaveSameDiscussion{
	no disjoint f1,f2: Farmer |
	one d: Discussion |
	d in f1.discussion  and d in f2.discussion
}

//All discussions are written by one farmer
fact allDiscussionsHaveOneFarmer {
	all d: Discussion |
	one f: Farmer |
	d in f.discussion
}

//Two discussions cannot contain the same post
fact differentDiscussionsCannotHaveSamePost {
	all disjoint d1, d2 : Discussion |
	no p: Post |
	p in d1.post and p in  d2.post
}

//All posts are written by a farmer
fact allPostsAreWrittenByAFarmer{
	all p: Post |
	one f: Farmer |
	f in p.farmer
}

//All posts of a discussion are created after the discussion
fact noPostIsBeforeDiscussion{
	all d: Discussion |
	all p: Post |
	p in d.post implies (
		compareTime[p.date, d.date] = 1
	)
}

//All posts must have a discussion
fact allPostsMustHaveOneDiscussion{
	all p: Post |
	one d:Discussion |
	p in d.post
}

/*** End Discussion Facts***/

/*** Humidity, Water, Forecasts Facts***/
//All possible constraints not inserted because not directly part of the scope

// All farms production data must be associated to one farm
fact eachProductionMustBeAssociatedToFarm {
	all fp : FarmProduction |
	one f : Farm |
	fp in f.farmProduction 
}

//Two different farms cannot have the same production
fact eachProductionBelongsToOnlyOneFarm {
	all disjoint f1, f2: Farm |
	no fp: FarmProduction |
	fp in f1.farmProduction and fp in f2.farmProduction
}

// All humidity data must be associated to a farm
fact eachHumidityOfSoilMustBeAssociatedToFarm {
	all h : HumidityOfSoil |
	one f : Farm |
	h in f.humidityOfSoil
}

//Two different farms cannot have the same humidity data
fact eachProductionBelongsToOnlyOneFarm {
	all disjoint f1, f2: Farm |
	no h : HumidityOfSoil |
	h in f1.humidityOfSoil and h in f2.humidityOfSoil
}

// All water data must be associated to a farm
fact eachWaterDataMustBeAssociatedToFarm {
	all w : WaterData |
	one f : Farm |
	w in f.waterData
}

//Two different farms cannot have the same water data
fact eachWaterDataBelongsToOnlyOneFarm {
	all disjoint f1, f2: Farm |
	no w : WaterData |
	w in f1.waterData and w in f2.waterData
}

// All forecast must be associated to an area
fact eachForecastMustBeAssociatedToArea {
	all f : Forecast |
	one a : Area |
	f in a.forecast
}

//Two different areas cannot have the same forecasts
fact eachForecastBelongsToOnlyOneArea {
	all disjoint a1, a2: Area |
	no f : Forecast |
	f in a1.forecast and f in a2.forecast
}

/*** EndHumidity, Water, Forecasts Facts***/

/*** END FACTS ***/



/*** ASSERTS ***/
//An assertion is a logical condition (true or false)

assert eachFarmBelongsToAFarmer{
	#Farmer = #Farm
}

assert  agronomistsVisitsInsideTheirArea{
	all a: Agronomist |
	all v: Visit |
	v in  a.dailyPlan.visit implies v.farm in a.area.farm
}

assert postsAreMoreOrEqualThanDiscussions {
	#Post >= #Discussion
}

/*** END ASSERTS ***/


/**** CHECKS  ****/
//A check tries to find a counterexample in which an assert is violated

check postsAreMoreOrEqualThanDiscussions for 4

check agronomistsVisitsInsideTheirArea for 4

check eachFarmBelongsToAFarmer for 4


/**** END CHECKS  ****/


/*** PREDICATES ***/
pred buildBasicWorldDailyPlans {}

pred buildBasicWorldRequests {}

pred buildBasicWorldDiscussions {}

pred buildComplexWorld {}

//This predicate is supposed to be inconsistent
pred error { 
	one f: Farmer, disjoint a1,a2: Agronomist |
	f.farm in a1.area.farm and f.farm in a2.area.farm
}
/*** END PREDICATES ***/

/*** RUNS ***/

//This runs a predicate that is supposed to be inconsistent
run error for 8

//This shows daily plans
run buildBasicWorldDailyPlans for 10 but exactly 0 PolicyMaker, exactly 2 Agronomist, exactly 3 Farmer, 
exactly 2 Area, exactly 0 Forecast, exactly 3 Farm, exactly 0 WaterData, exactly 0 FarmProduction, 
exactly 0 HumidityOfSoil, exactly 4 DailyPlan, exactly 6 Visit, exactly 0 Request, exactly 0 Answer,
exactly 0 Discussion, exactly 0 Post, exactly 4 DateTime, exactly 2 Date, exactly 2 Time

//This shows requests
run buildBasicWorldRequests for 10 but exactly 0 PolicyMaker, exactly 2 Agronomist, exactly 3 Farmer, 
exactly 2 Area, exactly 0 Forecast, exactly 3 Farm, exactly 0 WaterData, exactly 0 FarmProduction, 
exactly 0 HumidityOfSoil, exactly 0 DailyPlan, exactly 0 Visit, exactly 6 Request, exactly 5 Answer,
exactly 0 Discussion, exactly 0 Post, exactly 3 DateTime, exactly 3 Date, exactly 3 Time

//This shows discussions
run buildBasicWorldDiscussions for 10 but exactly 0 PolicyMaker, exactly 0 Agronomist, exactly 3 Farmer, 
exactly 2 Area, exactly 0 Forecast, exactly 3 Farm, exactly 0 WaterData, exactly 0 FarmProduction, 
exactly 0 HumidityOfSoil, exactly 0 DailyPlan, exactly 0 Visit, exactly 0 Request, exactly 0 Answer,
exactly 1 Discussion, exactly 2 Post, exactly 2 DateTime, exactly 2 Date, exactly 2 Time

//This shows everything
run buildComplexWorld for 10 but exactly 2 PolicyMaker, exactly 2 Agronomist, exactly 3 Farmer, 
exactly 2 Area, exactly 3 Forecast, exactly 3 Farm, exactly 3 WaterData, exactly 4 FarmProduction, 
exactly 3 HumidityOfSoil, exactly 4 DailyPlan, exactly 6 Visit, exactly 5 Request, exactly 4 Answer,
exactly 5 Discussion, exactly 6 Post, exactly 4 DateTime, exactly 2 Date, exactly 2 Time

/***END RUNS ***/
