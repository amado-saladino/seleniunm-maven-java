# Selenium POM Framework in Java
Selenium web test using with TestNG

Many resources were used as per [this course on Udemy](https://www.udemy.com/selenium-webdriver-from-foundation-to-framework-in-arabic/learn/v4/)

## Run with Maven

Here are the available profiles

- Regression
`mvn test -Pregression`

- Smoke testing
`mvn test -Psmoke`

- Grid
`mvn test -Pseleniumgrid`

- Browserstack
`mvn test -Pcloud`

**Note**: Change credentials for **Brwoserstack** in this path:
> Config/env.properties


Good luck  :)