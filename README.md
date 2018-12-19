# meli.forecastweather.job.scheduler
Este microservicio es un cron job responsable de enviar peticiones a otro microservicio para realizar los calculos de pronostico del clima en una galaxia lejana
---
## Descricion
Para usar este microservicio es necesario configurar cieros parametros en el archivo application.properties

* La API request enviada por el scheduler al nucleo de calculos es la siguiente

### Genera todos los registros climáticos diarios en un periodo de 10 años
| URL               | https://meli-forecast-core-ms.herokuapp.com/api/forecast/generaterecords/{fromday}/{atday}  |
| ----------        | ------------------------------- |
| __API__            | GET                        |
| __Método__            | GET                        |
| __Produces__          | application/json                |
| __Consumes__          | application/json                |
| __Path Parametes__    | {fromday} = dia de inicior {atDay}</code> = Dia final |
| __Response Status__   | 200 OK - OK                     |
| __Response Object__   | String                          |
| __Descripcion__       | Genera los registros climatologicos diarios, es activado por el Job scheduler y le realiza las request de escritura al microservicio de la BD        |

* URL de pruebas de comunicacion entre scheduler y nucleo; uso esta url solo para fines de comunicación.

### Para probar conectividad con el microservicio
| URL               | https://meli-forecast-core-ms.herokuapp.com/api/forecast/fakefordebugg/{fromday}/{atDay}  |
| ----------        | ------------------------------- |
| __API__            | GET                        |
| __Método__            | GET                        |
| __Produces__          | application/json                |
| __Consumes__          | application/json                |
| __Path Parametes__    | {fromday} = dia de inicior {atDay}</code> = Dia final |
| __Response Status__   | 200 OK - OK                     |
| __Response Object__   | String                          |
| __Descripcion__       | su única función es chequear conexion con el microservicio que accede a la base de datos        |

* Configuración del cron para disparar una peticion de calculo al nucleo todos los dias a las 00:00:00 am
    		forecast.weather.microservice.cron.string=0 0 0 * * ?
---

CRON JOB SCHEDULER 
	Direccion del cron job en heroku
		https://jobschedulermicroservice.herokuapp.com
	Repositorio en GitHub para clonar el microservicio
		https://github.com/hectorjcr/meli.forecastweather.job.scheduler.git
	Direccion en GitHub para visualizar el codigo
		hectorjcr/meli.forecastweather.job.scheduler
		Esta es desplegada desde heroku a traves de un pipeline

## Herramientas de desarrollo
<ul>
    <li>
        NetBeans IDE 8.2 para windows
    </li>
    <li>
        Git 2.01
    </li>
</ul>
<hr>



