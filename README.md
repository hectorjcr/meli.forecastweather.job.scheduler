# meli.forecastweather.job.scheduler
<p>This microservice is a cron job responsible for sending requests to another microservice to perform climate prediction calculations in a distant galaxy</p>

<hr>

## Description
<p>In order to use this microservice it is necessary to configure certain 
parameter in the <code>application.properties</code> file</p>
<ul>
<li>
 To set up the remote microservice host url<br/>
  <code>forecast.weather.microservice.host=http://localhost:8080/meli/api/hc/forecast/generaterecords</code><br/>
</li>
<li>
 Fake url for testing comunication between this microservice and the forecast weather microservice<br/>
  <code>forecast.weather.microservice.fake.host =http://localhost:8080/meli/api/hc/forecast/fakefordebugg</code><br/>
</li>
<li>
 Forecasting initial day<br/>
  <code>forecast.weather.microservice.initday=0</code><br/>
</li>
<li>
 Forecasting end day (10 predictions years)<br/>
  <code>forecast.weather.microservice.endday=3650</code><br/>
</li>
<li>
    cron config string set up in order to trigger a weather prediction every day at midnight<br>
    <code>forecast.weather.microservice.cron.string=0 0 0 * * ?</code>
</li>
</ul>
<hr>

## Development tools
<ul>
    <li>
        NetBeans IDE 8.2 para windows
    </li>
    <li>
        Git 2.01
    </li>
</ul>
<hr>

## Compilation Command

<ul>
    <li>
        <code>mvn clean install </code> Plain maven clean and install
    </li>
</ul>

