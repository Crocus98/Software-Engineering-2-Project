"""
Integration Tests for Controllers

Methods of use:
from terminal run 
    pytest -s testController.py
or
    pytest -v testController.py

"""
import requests
#import json
#import pytest

class Counter:
    def __init__(self, counter):
        self.counter = counter
    
    def add(self):
        self.counter = self.counter + 1
        return self.counter
    
    
contatore = Counter(0)

print("-------------------------------------------------------------------------------------------")
print("INTEGRATION TEST FOR CONTROLLERS")

#print("-------------------------------------------------------------------------------------------")
print("Test CheckLogin Controller")
#print("-------------------------------------------------------------------------------------------")

def testConnectionCheckLogin():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    r = requests.post(link, data={
    "mail":"farmer@mail.com",
    "pwd":"farmer"
    })
    print(str(contatore.add()) + " - Connection test Login Page")
    assert r.status_code==200

#testConnectionCheckLogin()

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

#testLoginNoPassword()

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

#testLoginNoMail()

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

#testLoginFailedWrongPassword()

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

#testLoginFailedWrongMail()


def testLoginFarmer():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    r = requests.post(link, data={
    "mail":"farmer@mail.com",
    "pwd":"farmer"
    })
    trovaTesto=r.text.find("Insert Production")
    #print(r.content)
    print(str(contatore.add()) + " - login for farmer test")
    assert trovaTesto!=-1 #se diverso da -1 è stato trovato l'errore->login non avvenuto

#testLoginFarmer()

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

#testLoginPolicyMaker()


#print("-------------------------------------------------------------------------------------------")
print("Test GoToLoginPage Controller")
#print("-------------------------------------------------------------------------------------------")

def testConnectionGoToLoginPage():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/GoToLoginPage'
    r = requests.post(link)
    print(str(contatore.add()) + " - Connection test GoToLoginPage")
    assert r.status_code==200

#testConnectionGoToLoginPage()

def testRedirectToLoginPage():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/GoToLoginPage'
    r = requests.post(link)
    trovaErrore=r.text.find("LOG IN NOW")
    #print(r.content)
    print(str(contatore.add()) + " - redirect to Login Page test")
    assert trovaErrore==-1 #se diverso da -1 è stato trovato l'errore->redirect non avvenuto

#testRedirectToLoginPage()



#print("-------------------------------------------------------------------------------------------")
print("Test GoToRankingPage Controller")
#print("-------------------------------------------------------------------------------------------")

def testGoToRankingPageNoDirectConnection():
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/RankingPage'
    r = requests.get(link)
    #print(r.content)
    print(str(contatore.add()) + " - no direct connection to GoToRankingPage test")
    assert r.status_code==500 #se == 500 connessione non avvenuta

#testGoToRankingPageNoDirectConnection()

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

#testGoToRankingPageNoDirectConnection()


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

#testRankingFilterSelection1()

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

#testRankingFilterSelection2()

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

#testRankingFilterSelection3()

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

#testRankingFilterSelection4()

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

#testRankingFilterSelection5()

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

#testRankingFilterSelection6()



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

#testRankingFilterSelection7()


#print("-------------------------------------------------------------------------------------------")
print("Test GoToCreateRequest Controller")
#print("-------------------------------------------------------------------------------------------")



#print("-------------------------------------------------------------------------------------------")
print("Test GoToForumPage Controller")
#print("-------------------------------------------------------------------------------------------")



#print("-------------------------------------------------------------------------------------------")
print("Test GoToHomeAgronomist Controller")
#print("-------------------------------------------------------------------------------------------")



#print("-------------------------------------------------------------------------------------------")
print("Test GoToHomePolicyMaker Controller")
#print("-------------------------------------------------------------------------------------------")


def testGoToHomePolicyMakerConnection():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomePolicyMaker'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - connection GoToHomePolicyMaker with session cookie test")

    assert r.text.find("Production summary ")!=-1 #se ==-1 la pagina non è aggiornata'

#testGoToHomePolicyMakerConnection()


def testBestAreaGoToHomePolicyMaker():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomePolicyMaker'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - selection of best area GoToHomePolicyMaker with session cookie test")

    assert r.text.find("Best Area:<br />Area2</div>")!=-1 #se ==-1 la query non è corretta'

#testBestAreaGoToHomePolicyMaker()


def testWorstAreaGoToHomePolicyMaker():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomePolicyMaker'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - selection of worst area GoToHomePolicyMaker with session cookie test")

    assert r.text.find("Worst Area:<br />Area1</div>")!=-1 #se ==-1 la query non è corretta'

#testWorstAreaGoToHomePolicyMaker()



def testBestFarmerGoToHomePolicyMaker():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomePolicyMaker'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - selection of best farmer GoToHomePolicyMaker with session cookie test")

    assert r.text.find("Best Farmer:<br />Burpa Kalifa")!=-1 #se ==-1 la query non è corretta'

#testBestFarmerGoToHomePolicyMaker()


def testWorstFarmerGoToHomePolicyMaker():
    s = requests.Session()
    link = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/CheckLogin'
    data={"mail":"policymaker@mail.com","pwd":"policymaker"}
    r = s.post(link,data)
    #print(s.cookies.get_dict)
    link1 = 'http://localhost:8080/Se2_Bertelli_Savino_Vinati_Web/HomePolicyMaker'
    r1 = s.get(link1)
    print(str(contatore.add()) + " - selection of worst farmer GoToHomePolicyMaker with session cookie test")

    assert r.text.find("Worst Farmer:<br />Bruno Bucciarati")!=-1 #se ==-1 la query non è corretta'

#testWorstFarmerGoToHomePolicyMaker()


#print("-------------------------------------------------------------------------------------------")
print("Test GoToHomeFarmer Controller")
#print("-------------------------------------------------------------------------------------------")



#print("-------------------------------------------------------------------------------------------")
print("Test GoToProductionPage Controller")
#print("-------------------------------------------------------------------------------------------")



#print("-------------------------------------------------------------------------------------------")
print("Test RequestListPage Controller")
#print("-------------------------------------------------------------------------------------------")





