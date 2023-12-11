1.	Using tools and technologies that you know (have experience with, work on your current project, know better) create a Test Automation Framework. The framework will be used for testing the Report Portal. There should be presented the following layers:
      a.	Core
      b.	Business
      c.	Tests
      Use models/business objects where needed.
      [Java Note] Use any build tool you would like to.
2.	Integrate below components into your framework (using correct layer):
      a.	Logger (Use any you would like to)
      b.	Reporter (Use any you would like to)
      c.	Test runner (Add the one which you have most experience with)
      d.	Configuration
      e.	Utilities (Add any you find useful)
3.	Add a configuration to support a web version of the report portal (https://rp.epam.com). You can use your EPAM credentials to log-in. Also adding external user is possible (e.g. your private e-mail) into your personal project

API - Retrofit(dto, serialisation, dataprovider) 5/8 SP

UI - Selenium 4(web elements(wrappers),testng,PageObj,PageFactory, managing of the paralell sessions(Thread local,Singleton),Property reader(multi environment support)  - 8/13 SP

Frame infra: multilayers, readme,gradle(use multi modules arch),git submodules - 8/13 SP

CI - Jenkins, pipelines(from git), Selenium Grid 4 - 13SP

Run:
`.\gradlew test -Pconfig=prod`