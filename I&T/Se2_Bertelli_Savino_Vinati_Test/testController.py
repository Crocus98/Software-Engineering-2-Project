"""
Integration Tests for Controllers


HOW TO

First create an environment with python3 installed, then from terminal run
pip install mysql-connector-python
pip install pytest

Run the script:
from terminal run 
    pytest -s testController.py
or
    pytest -v testController.py

"""
import requests
import mysql.connector
#import pytest


#Connection to database
db = mysql.connector.connect(
    host = '127.0.0.1', 
    user = 'root', 
    passwd = 'Crostolo1apostolo', 
    db = 'se2_bertelli_savino_vinati', 
    port = 3306)

dbCursor = db.cursor()

#number of tests counter
class Counter:
    def __init__(self, counter):
        self.counter = counter
    
    def add(self):
        self.counter = self.counter + 1
        return self.counter
    
contatore = Counter(0)





print("--------------------------------------------------------------------------------------------")
print("INTEGRATION TEST FOR CONTROLLERS")
#print("-------------------------------------------------------------------------------------------")


#print("-------------------------------------------------------------------------------------------")
print("Test CheckLogin Controller")
#print("-------------------------------------------------------------------------------------------")

def testConnectionCheckLogin():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    r = requests.post(link, data={
    "mail":"farmer@mail.com",
    "pwd":"farmer"
    })
    print("  ")
    print(str(contatore.add()) + " - Connection test Login Page")
    assert r.status_code==200



def testLoginNoPassword():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    r = requests.post(link, data={
    "mail":"policymaker@mail.com",
    "pwd":""
    })
    trovaErrore=r.text.find("ERROR")
    #print(r.content)
    print(str(contatore.add()) + " - login failed No password test")
    assert trovaErrore==-1 #se diverso da -1 è stato trovato l'errore->login non avvenuto



def testLoginNoMail():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    r = requests.post(link, data={
    "mail":"",
    "pwd":"pass"
    })
    trovaErrore=r.text.find("ERROR")
    #print(r.content)
    print(str(contatore.add()) + " - login failed No mail test")
    assert trovaErrore==-1 #se diverso da -1 è stato trovato l'errore->login non avvenuto



def testLoginFailedWrongPassword():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    r = requests.post(link, data={
    "mail":"farmer@mail.com",
    "pwd":"farmer3"
    })
    trovaErrore=r.text.find("ERROR")
    #print("risultato: " + str(a))
    print(str(contatore.add()) + " - login failed wrong password test")
    assert trovaErrore!=-1 #se diverso da -1 è stato trovato l'errore->login non avvenuto



def testLoginFailedWrongMail():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    r = requests.post(link, data={
    "mail":"wrong@mail.com",
    "pwd":"farmer"
    })
    trovaErrore=r.text.find("ERROR")
    #print(r.content)
    print(str(contatore.add()) + " - login failed wrong mail test")
    assert trovaErrore!=-1 #se diverso da -1 è stato trovato l'errore->login non avvenuto



def testLoginFarmer():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    r = requests.post(link, data={
    "mail":"farmer@mail.com",
    "pwd":"farmer"
    })
    trovaTesto=r.text.find("Yearly Production Summary")
    #print(r.content)
    print(str(contatore.add()) + " - login for farmer test")
    assert trovaTesto!=-1 #se diverso da -1 è stato trovato l'errore->login non avvenuto


def testLoginPolicyMaker():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    r = requests.post(link, data={
    "mail":"policymaker@mail.com",
    "pwd":"policymaker"
    })
    trovaTesto=r.text.find("Ranking")
    #print(r.content)
    print(str(contatore.add()) + " - login for Policy Maker test")
    assert trovaTesto!=-1 #se diverso da -1 è stato trovato l'errore->login non avvenuto


#print("-------------------------------------------------------------------------------------------")
print("Test GoToLoginPage Controller")
#print("-------------------------------------------------------------------------------------------")

def testConnectionGoToLoginPage():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/GoToLoginPage'
    r = requests.post(link)
    print(str(contatore.add()) + " - Connection test GoToLoginPage")
    assert r.status_code==200


def testRedirectToLoginPage():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/GoToLoginPage'
    r = requests.post(link)
    trovaErrore=r.text.find("LOG IN NOW")
    #print(r.content)
    print(str(contatore.add()) + " - redirect to Login Page test")
    assert trovaErrore==-1 #se diverso da -1 è stato trovato l'errore->redirect non avvenuto



#print("-------------------------------------------------------------------------------------------")
print("Test GoToRankingPage Controller")
#print("-------------------------------------------------------------------------------------------")

def testGoToRankingPageNoDirectConnection():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/RankingPage'
    r = requests.get(link)
    #print(r.content)
    print(str(contatore.add()) + " - no direct connection to GoToRankingPage test")
    assert r.text.find("Log In")!=-1 #se == -1 connessione avvenuta -> test errato



def testGoToRankingPageConnection():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/RankingPage'
    r = s.get(link1)
    #print(r.content)
    print(str(contatore.add()) + " - connection to GoToRankingPage with session cookie test")
    assert r.status_code==200 #se == 200 connessione avvenuta



def testRankingFilterSelection1():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/RankingPage'
    r1 = s.get(link1)
    
    link2 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/RankingPage?start_date=&idArea=2&order_mode=true&limit_number=0'
    r2 = s.get(link2)
    print(str(contatore.add()) + " - change page after filter of GoToRankingPage with session cookie test")

    #print(r2.content)
    assert r1._content!=r2.content #se != la pagina ha modificato la tabella


def testRankingFilterSelection2():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/RankingPage?start_date=&idArea=1&order_mode=true&limit_number=0'
    r1 = s.get(link1)
    
    link2 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/RankingPage?start_date=&idArea=2&order_mode=true&limit_number=0'
    r2 = s.get(link2)
    print(str(contatore.add()) + " - change area of interest of GoToRankingPage with session cookie test")

    #print(r2.content)
    assert r1._content!=r2.content #se != la pagina ha modificato l'area di interesse'



def testRankingFilterSelection3():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/RankingPage?start_date=&idArea=1&order_mode=true&limit_number=0'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - change area of interest (1) of GoToRankingPage with session cookie test")

    #print(r2.content)
    assert r1.text.find("farmer3@mail.com")==-1 #se ==-1 il farmer3 appartenente all'area 2 non è stato trovato'

()

def testRankingFilterSelection4():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
      
    link2 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/RankingPage?start_date=&idArea=2&order_mode=true&limit_number=0'
    r2 = s.get(link2)
    print(str(contatore.add()) + " - change area of interest (2) of GoToRankingPage with session cookie test")

    #print(r2.content)
    assert r2.text.find("farmer2@mail.com")==-1 #se ==-1 il farmer2 appartenente all'area 1 non è stato trovato'



def testRankingFilterSelection5():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/RankingPage?start_date=&idArea=0&order_mode=true&limit_number=0'
    r1 = s.get(link1)
    
    link2 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/RankingPage?start_date=&idArea=0&order_mode=false&limit_number=0'
    r2 = s.get(link2)
    print(str(contatore.add()) + " - change order of records of GoToRankingPage with session cookie test")

    #print(r2.content)
    assert r1._content!=r2.content #se != la pagina ha modificato l'ordinamento'



def testRankingFilterSelection6():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/RankingPage?start_date=&idArea=0&order_mode=true&limit_number=0'
    r1 = s.get(link1)
    
    link2 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/RankingPage?start_date=&idArea=0&order_mode=true&limit_number=2'
    r2 = s.get(link2)
    print(str(contatore.add()) + " - change number of records of GoToRankingPage with session cookie test")

    #print(r2.content)
    assert r1._content!=r2.content #se != la pagina ha modificato il numero dei record'




def testRankingFilterSelection7():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/RankingPage?start_date=2022-01-17&idArea=0&order_mode=true&limit_number=0'
    r1 = s.get(link1)
    
    link2 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/RankingPage?start_date=2022-01-31&idArea=0&order_mode=true&limit_number=0'
    r2 = s.get(link2)
    print(str(contatore.add()) + " - change period of interest of GoToRankingPage with session cookie test")

    #print(r2.content)
    assert r1._content!=r2.content #se != la pagina ha modificato il periodo di interesse'



#print("-------------------------------------------------------------------------------------------")
print("Test GoToHomePolicyMaker Controller")
#print("-------------------------------------------------------------------------------------------")

def testGoToHomePolicyMakerNoDirectConnection():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomePolicyMaker'
    r = requests.get(link)
    #print(r.content)
    print(str(contatore.add()) + " - no direct connection to GoToHomePolicyMaker test")
    assert r.text.find("Log In")!=-1 #se == -1 connessione avvenuta -> test errato



def testGoToHomePolicyMakerConnection():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomePolicyMaker'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - connection GoToHomePolicyMaker with session cookie test")

    assert r1.text.find("Production summary ")!=-1 #se ==-1 la pagina non è aggiornata'



def testBestAreaGoToHomePolicyMaker():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomePolicyMaker'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - selection of best area GoToHomePolicyMaker with session cookie test")

    assert r1.text.find("Best Area:<br />Area2</div>")!=-1 #se ==-1 la query non è corretta'



def testWorstAreaGoToHomePolicyMaker():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomePolicyMaker'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - selection of worst area GoToHomePolicyMaker with session cookie test")

    assert r1.text.find("Worst Area:<br />Area3</div>")!=-1 #se ==-1 la query non è corretta'



def testBestFarmerGoToHomePolicyMaker():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomePolicyMaker'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - selection of best farmer GoToHomePolicyMaker with session cookie test")

    assert r1.text.find("Best Farmer:<br />Burpa Kalifa")!=-1 #se ==-1 la query non è corretta'



def testWorstFarmerGoToHomePolicyMaker():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomePolicyMaker'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - selection of worst farmer GoToHomePolicyMaker with session cookie test")

    assert r1.text.find("Worst Farmer:<br />Indah Cujjohe")!=-1 #se ==-1 la query non è corretta'



#print("-------------------------------------------------------------------------------------------")
print("Test GoToHomeFarmer Controller")
#print("-------------------------------------------------------------------------------------------")

def testGoToHomeFarmerNoConnection():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomeFarmer'
    r = requests.get(link)
    #print(r.content)
    print(str(contatore.add()) + " - no direct connection to HomeFarmer page test")
    assert r.text.find("Log In")!=-1 #se == -1 connessione avvenuta -> test errato

#testGoToHomeFarmerNoConnection()


def testGoToHomeFarmerConnection():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomeFarmer'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - GoToHomeFarmerPage connection with session cookie test")

    assert r1.text.find("Yearly Production Summary")!=-1 #se ==-1 non c'è caricamento della pagina'



def testGoToHomeFarmerGetRightMonthProductionAmount():
    #get data from db
    sql='SELECT sum(amount) FROM se2_bertelli_savino_vinati.production where idfarm="1" and date between (last_day(curdate() - interval 13 month)) and (last_day(curdate() - interval 1 month)) group by date_format(date, "%M")';
    dbCursor.execute(sql)
    data = dbCursor.fetchall()
    #convert data from list of tuple of decimal.decimal to list of strings
    listData=[]
    for i in data:
        l=([int(t) for t in str(i) if t.isdigit()])
        s="".join(str(e) for e in l)
        listData.append(s)
    #login
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #go to productionPage
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomeFarmer'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - GoToHomeFarmer get Right Month Production Amount session cookie test")
    #check prsence of all production data
    for d in listData:
        assert r1.text.find(d)!=-1 #se !=-1 la query non è corretta manca almeno uno dei dati di produzione'



def testGoToHomeFarmerGetRightWaterConsumptionAmount():
    #get data from db
    sql="SELECT sum(amount) FROM se2_bertelli_savino_vinati.waterconsumption where idfarm='1' and date between (last_day(curdate() - interval 13 month)) and (last_day(curdate() - interval 1 month))";
    dbCursor.execute(sql)
    data = dbCursor.fetchall()
    #convert data from list of tuple of decimal.decimal to list of strings
    listData=[]
    for i in data:
        l=([int(t) for t in str(i) if t.isdigit()])
        s="".join(str(e) for e in l)
        listData.append(s)
    #login
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #go to productionPage
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomeFarmer'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - GoToHomeFarmer get Right Month Water Consumption Amount session cookie test")
    #check prsence of all production data
    for d in listData:
        assert r1.text.find(d)!=-1 #se !=-1 se manca il dato di consumo dell'acqua'



def testGoToHomeFarmerGetRightHumidityOfSoil():
    #get data from db
    sql="SELECT classification FROM se2_bertelli_savino_vinati.humidityofsoil where idfarm='1' and date=current_date()";
    dbCursor.execute(sql)
    data = dbCursor.fetchall()
    #print(data)
    #convert data from list of tuple of decimal.decimal to list(int)
    classification=([int(t) for t in str(data) if t.isdigit()])
    HumidityScale = {0:"Extremly Arid",1:"Very Arid",2:"Slightly Arid",3:"Balanced",4:"Slightly Humid",5:"Very Humid",6:"Extremely Humid",7:"Not available data"}
    #print(classification)
    #login
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #go to productionPage
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomeFarmer'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - GoToHomeFarmer get Right HumidityOfSoil session cookie test")
    #check presense of right humidity value
    if classification: #se la lista è non è vuota fa un assert sul valore della classificazione altrimenti seleziona "No Available Data" 
        assert r1.text.find(HumidityScale[classification[0]])!=-1 #se !=-1 se manca il dato di previsione'
    else:
        assert r1.text.find(HumidityScale[7])!=-1 #se !=-1 se manca il dato di previsione'
        


def testGoToHomeFarmerGetWeatherForecast():
    #get data from db
    sql="SELECT classification FROM se2_bertelli_savino_vinati.forecast where idarea='1' and date=current_date()";
    dbCursor.execute(sql)
    data = dbCursor.fetchall()
    #print(data)
    #convert data from list of tuple of decimal.decimal to list(int)
    classification=([int(t) for t in str(data) if t.isdigit()])
    WeatherScale = {0:"Extremly Heavy Rainfall",1:"Very Heavy Rainfall",2:"Heavy Rainfall",3:"Moderate Rainfall",4:"Light Rainfall",5:"Very Light Rainfall",6:"No Rainfall",7:"Not available data"}
    #print(classification)
    #login
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #go to productionPage
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomeFarmer'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - GoToHomeFarmer get Right Weather Forecast session cookie test")
    #check prsence of all production data
    if classification: #se la lista è non è vuota fa un assert sul valore della classificazione altrimenti seleziona "No Available Data" 
        assert r1.text.find(WeatherScale[classification[0]])!=-1 #se !=-1 se manca il dato di consumo dell'acqua'
    else:
        assert r1.text.find(WeatherScale[7])!=-1 #se !=-1 se manca il dato di consumo dell'acqua'



#print("-------------------------------------------------------------------------------------------")
print("Test GoToProductionPage Controller")
#print("-------------------------------------------------------------------------------------------")

def testGoToProductionPageNoDirectConnection():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Production'
    r = requests.get(link)
    #print(r.content)
    print(str(contatore.add()) + " - no direct connection to Production page test")
    assert r.text.find("Log In")!=-1 #se == -1 connessione avvenuta -> test errato




def testGoToProductionPageConnection():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Production'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - GoToProductionPage connection with session cookie test")

    assert r1.text.find("Production")!=-1 #se ==-1 la query non è corretta'



def testGoToProductionPageInsertProduction():
    #clean db
    sql = "DELETE FROM `se2_bertelli_savino_vinati`.`production` WHERE (`comment` = 'TESTA')"
    dbCursor.execute(sql)
    db.commit()    
    #login
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #go to productionPage
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Production'
    r1 = s.get(link1)
    #insert record
    link2 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Production'
    production={"type":"0","date":"2019-01-01","amount":"100","problem":"TESTA"}
    r2 = s.post(link2,production)
    print(str(contatore.add()) + " - GoToProductionPage insert data session cookie test")

    assert r2.text.find("TESTA")!=-1 #se ==-1 la query non è corretta'




def testGoToProductionPageInsertProductionWrongVegetable():
    #clean db
    sql = "DELETE FROM `se2_bertelli_savino_vinati`.`production` WHERE (`comment` = 'TESTA2')"
    dbCursor.execute(sql)
    db.commit()    
    #login
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #go to productionPage
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Production'
    r1 = s.get(link1)
    #insert record
    link2 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Production'
    production={"type":"9","date":"2019-01-01","amount":"100","problem":"TESTA2"}
    r2 = s.post(link2,production)
    print(str(contatore.add()) + " - GoToProductionPage not insert data with wrong vegetable session cookie test")

    assert r2.text.find("TESTA2")==-1 #se ==-1 la query non è corretta'



def testGoToProductionPageInsertProductionNoDate():
    #clean db
    sql = "DELETE FROM `se2_bertelli_savino_vinati`.`production` WHERE (`comment` = 'TESTA3')"
    dbCursor.execute(sql)
    db.commit()    
    #login
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #go to productionPage
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Production'
    r1 = s.get(link1)
    #insert record
    link2 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Production'
    production={"type":"0","date":"","amount":"100","problem":"TESTA3"}
    r2 = s.post(link2,production)
    print(str(contatore.add()) + " - GoToProductionPage insert data without date session cookie test")

    assert r2.text.find("TESTA3")!=-1 #se ==-1 la query non è corretta perchè non c'è il record



def testGoToProductionPageInsertProductionNoVoidAmount():
    #clean db
    sql = "DELETE FROM `se2_bertelli_savino_vinati`.`production` WHERE (`comment` = 'TESTA4')"
    dbCursor.execute(sql)
    db.commit()    
    #login
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #go to productionPage
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Production'
    r1 = s.get(link1)
    #insert record
    link2 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Production'
    production={"type":"0","date":"2019-01-01","amount":"","problem":"TESTA4"}
    r2 = s.post(link2,production)
    print(str(contatore.add()) + " - GoToProductionPage insert data only with amount >0 session cookie test")

    assert r2.text.find("TESTA4")==-1 #se !=-1 la query non è corretta perchè è stato inserito un record senza amount'



def testGoToProductionPageInsertProductionNoVoidComment():
    #clean db
    sql = "DELETE FROM `se2_bertelli_savino_vinati`.`production` WHERE (`amount` = '13131313')"
    dbCursor.execute(sql)
    db.commit()    
    #login
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #go to productionPage
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Production'
    r1 = s.get(link1)
    #insert record
    link2 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Production'
    production={"type":"0","date":"2019-01-01","amount":"13131313","problem":""}
    r2 = s.post(link2,production)
    print(str(contatore.add()) + " - GoToProductionPage insert data only with no void comment session cookie test")

    assert r2.text.find("13131313")==-1 #se !=-1 la query non è corretta perchè è stato inserito un record senza commento'



#print("-------------------------------------------------------------------------------------------")
print("Test GoToForumPage Controller")
#print("-------------------------------------------------------------------------------------------")

def testGoToForumPageNoDirectConnection():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Forum'
    r = requests.get(link)
    #print(r.content)
    print(str(contatore.add()) + " - no direct connection to Forum page test")
    assert r.text.find("Log In")!=-1 #se == -1 connessione avvenuta -> test errato


def testGoForumConnection():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Forum'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - ForumPage connection with session cookie test")

    assert r1.text.find("Create Discussion")!=-1 #se ==-1 non c'è caricamento della pagina'


def testGoToForumPageCreateDiscussion():
    #clean db
    sql = "DELETE FROM `se2_bertelli_savino_vinati`.`discussion` WHERE (`title` = 'TESTDISCUSSION');"
    dbCursor.execute(sql)
    db.commit()    
    #login
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #go to ForumPage
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Forum'
    r1 = s.get(link1)
    #insert record
    link2 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Forum'
    discussion={"title":"TESTDISCUSSION","comment":"TESTDISCUSSION"}
    r2 = s.post(link2,discussion)
    print(str(contatore.add()) + " - GoToForumPage create discussion session cookie test")

    assert r2.text.find("TESTDISCUSSION")!=-1 #se ==-1 la query non è corretta perchè è non stato inserita una discussione'



#print("-------------------------------------------------------------------------------------------")
print("Test GoToPostPage Controller")
#print("-------------------------------------------------------------------------------------------")

def testGoToPostPageNoDirectConnection():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Post'
    r = requests.get(link)
    #print(r.content)
    print(str(contatore.add()) + " - no direct connection to Forum page test")
    assert r.text.find("Log In")!=-1 #se == -1 connessione avvenuta -> test errato


def testGoToPostConnection():
    #clean db
    sql = "DELETE FROM `se2_bertelli_savino_vinati`.`discussion` WHERE (`title` = 'TESTPOST');"
    dbCursor.execute(sql)
    db.commit()
    #login
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #insert discussion
    link2 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Forum'
    discussion={"title":"TESTPOST","comment":"TESTPOST"}
    r2 = s.post(link2,discussion)
    #get discussion id
    sql = "SELECT max(id) FROM `se2_bertelli_savino_vinati`.`discussion` where title='TESTPOST';"
    dbCursor.execute(sql)
    data = dbCursor.fetchall()
    tempInt=([int(t) for t in str(data) if t.isdigit()])
    id="".join(str(e) for e in tempInt)
    #print(id)
    #connection to Post Page
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Post?discussion='+id
    r1 = s.get(link1)
    print(str(contatore.add()) + " - PostPage connection to last discussion with session cookie test")
    assert r1.text.find("TESTPOST")!=-1


def testGoToPostNewPostCreation():
    #clean db
    sql = "DELETE FROM `se2_bertelli_savino_vinati`.`discussion` WHERE (`title` = 'TESTPOST2');"
    dbCursor.execute(sql)
    db.commit()
    #login
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #insert discussion
    link2 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Forum'
    discussion={"title":"TESTPOST2","comment":"TESTPOST2"}
    r2 = s.post(link2,discussion)
    #get discussion id
    sql = "SELECT max(id) FROM `se2_bertelli_savino_vinati`.`discussion` where title='TESTPOST2';"
    dbCursor.execute(sql)
    data = dbCursor.fetchall()
    tempInt=([int(t) for t in str(data) if t.isdigit()])
    id="".join(str(e) for e in tempInt)
    #print(id)
    #new post creation
    link3 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Post'
    post={"comment":"TESTPOST2","id":id}
    r3 = s.post(link3,post)
    #connection to Post Page
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Post?discussion='+id
    r1 = s.get(link1)
    print(str(contatore.add()) + " - PostPage creation of a Post into an exsisting discussion with session cookie test")
    assert r1.text.find("TESTPOST2")!=-1


def testGoToPostNoVoidPostCreation():
    #clean db
    sql = "DELETE FROM `se2_bertelli_savino_vinati`.`discussion` WHERE (`title` = 'TESTPOST3');"
    dbCursor.execute(sql)
    db.commit()
    #login
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #insert discussion
    link2 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Forum'
    discussion={"title":"TESTPOST3","comment":"TESTPOST3"}
    r2 = s.post(link2,discussion)
    #get discussion id
    sql = "SELECT max(id) FROM `se2_bertelli_savino_vinati`.`discussion` where title='TESTPOST3';"
    dbCursor.execute(sql)
    data = dbCursor.fetchall()
    tempInt=([int(t) for t in str(data) if t.isdigit()])
    id="".join(str(e) for e in tempInt)
    #print(id)
    #new post creation
    link3 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Post'
    post={"comment":"","id":id}
    r3 = s.post(link3,post)
    #get number of exsisting posts
    sql = "SELECT count(*) FROM se2_bertelli_savino_vinati.post where iddiscussion="+id
    dbCursor.execute(sql)
    data = dbCursor.fetchall()
    nPost=([int(t) for t in str(data) if t.isdigit()])
    print(str(contatore.add()) + " - No creation of a void Post into an exsisting discussion with session cookie test")
    assert nPost[0]==1 


def testNoConnectionToNonExistingDiscussion():
    #clean db
    sql = "DELETE FROM `se2_bertelli_savino_vinati`.`discussion` WHERE (`title` = 'TESTPOST4');"
    dbCursor.execute(sql)
    db.commit()
    #login
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"farmer@mail.com","pwd":"farmer"}
    r = s.post(link,data)
    #insert discussion
    link2 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Forum'
    discussion={"title":"TESTPOST4","comment":"TESTPOST4"}
    r2 = s.post(link2,discussion)
    #get discussion id
    sql = "SELECT max(id) FROM `se2_bertelli_savino_vinati`.`discussion`;"
    dbCursor.execute(sql)
    data = dbCursor.fetchall()
    tempInt=([int(t) for t in str(data) if t.isdigit()])
    tempInt[0]+=10
    id="".join(str(e) for e in tempInt)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/Post?discussion='+id
    r1 = s.get(link1)
    print(str(contatore.add()) + " - get Error for non exsisting discussion with session cookie test")
    assert r1.text.find("ERROR")!=-1  #se ==-1 ERROR non trovato 

"""
code for mock data

UPDATE `se2_bertelli_savino_vinati`.`forecast` SET `value`= (FLOOR(RAND()*(b-a+1))+a) WHERE (`classification` = '5');

"""



