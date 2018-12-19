# meli.forecastweather.job.scheduler
---
Este microservicio es un cron job responsable de enviar peticiones a otro microservicio para realizar los calculos de pronostico del clima en una galaxia lejana
---
## Descripcion
Para usar este microservicio es necesario configurar ciertos parámetros en el archivo application.properties

* La API request enviada por el scheduler al núcleo de cálculos es la siguiente

### Genera todos los registros climáticos diarios en un período de 10 años
| URL               | https://meli-forecast-core-ms.herokuapp.com/api/forecast/generaterecords/{fromday}/{atday}  |
| ----------        | ------------------------------- |
| __API__            | GET                        |
| __Método__            | GET                        |
| __Produces__          | application/json                |
| __Consumes__          | application/json                |
| __Path Parametes__    | {fromday} = dia de inicior {atDay}</code> = Dia final |
| __Response Status__   | 200 OK - OK                     |
| __Response Object__   | String                          |
| __Descripcion__       | Genera los registros climatológicos diarios, es activado por el Job scheduler y le realiza las request de escritura al microservicio de la BD        |

* URL de pruebas de comunicación entre scheduler y el núcleo; uso esta url solo para fines de comunicación.

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
| __Descripcion__       | su única función es chequear conexión con el microservicio que accede a la base de datos        |

* Configuración del cron para disparar una peticion de cálculo al núcleo todos los días a las 00:00:00 am
    		forecast.weather.microservice.cron.string=0 0 0 * * ?
---

### Direcciones de alojamiento en la nube
	Dirección del cron job en heroku
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



