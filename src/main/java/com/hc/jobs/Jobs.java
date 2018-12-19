package com.hc.jobs;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *MICROSERVICIO JOB
 * <p>Realiza una peticion al microservicio de predicciones para calcular el
 * clima de la galaxia desde el momento actual hasta 10 años</p>
 * 
 * @author Hector Contreras
 * @version 1.0
 */
@Component
public class Jobs {
    /**
     * Instancia un RestTemplate que realizara las peticiones al microservicio
     * de calculo de prediciones de clima
     */
    private final RestTemplate restTemplate;
    /**
     * Logger para las salidas de consola
     */
    private final static Logger l = Logger.getLogger(Jobs.class.getName());
    
    /**
     *<p> Prefijo que trae desde el archivo Application.properties
     * el valor de la URL del microservicio</p>
     */
    @Value(value = "${forecast.weather.microservice.host}")
    private String prefix="";
    
    /**
     *<p> Parametro de dia de inicio de los calculos</p>
     */
    @Value(value = "${forecast.weather.microservice.initday}")
    private String initDay="";
    
    /**
     *<p> Parametro de dia fin para los calculos</p>
     */
    @Value(value = "${forecast.weather.microservice.endday}")
    private String endDay="";

    public Jobs(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     *<p> Job que se dispara con una frecuencia determinada por la propiedad
     * forecast.weather.microservice.cron.string del Application.properties
     * </p>
     * <p>Realiza un request al microservicio de calculo de predicción de clima, 
     * espera un codigo HTTP 200 y un mensaje de operación exitosa en caso de 
     * exito</p>
     * <p>Recibe un código HTTP 404  not found en caso de haber conexion pero no
     * estar disponible el recurso
     * </p>
     * <p>recibe un mensaje de error que es atrapado en caso de no haber conexion</p>
     */
    //@Scheduled(fixedRate = 86400000) // para testing cada 15 Seg.
    @Scheduled(cron = "${forecast.weather.microservice.cron.string}") //prod env todos los dias a las 00:00:00
    public void performTask(){
        l.log(Level.INFO,"${forecast.weather.microservice.cron.string}");
        if(Integer.parseInt(initDay) == 0)
            l.log(Level.INFO, "###########     Iniciando CRON JOBS por primera vez   #############");
        l.log(Level.INFO, "Iniciado");
        String suffix = "/"+initDay+"/"+endDay;
        String uri = prefix + suffix;        
        l.log(Level.INFO,"Request a "+ uri);
        try{
            l.log(Level.INFO, "llamando metodo asincrono");
            CompletableFuture<String> result = starterConnection(Integer.parseInt(initDay),Integer.parseInt(endDay),uri);
            l.log(Level.INFO,result.toString());
            l.log(Level.INFO, "Metodo asincrono ejecutado");
        }catch(Exception e){
            l.log(Level.SEVERE,e.getMessage());
        }
        int iday = Integer.parseInt(initDay)+1;
        initDay = Integer.toString(iday);
        int eday = Integer.parseInt(endDay)+1;
        endDay = Integer.toString(eday);
    }

    /**
     * Método asincrono que se encarga de hacer la petición
     * de calculos a la API del microservicio core
     *
     * @param init dia inicial
     * @param end dia final
     * @param uri url del microservicio core
     * @return estado de la tarea (completada)
     * @throws InterruptedException captura el error en caso de alguna interrupcion
     */
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> starterConnection(int init, int end, String uri) throws InterruptedException{
        String result="";
        l.log(Level.INFO, "iniciando task");
        try {
            result = restTemplate.getForObject(uri,String.class);
            l.log(Level.INFO, "resultado del task");
            l.log(Level.INFO, result);
        }catch (Exception e){
            l.log(Level.SEVERE, e.getMessage());
        }
        l.log(Level.INFO, "saliendo del task");
        return CompletableFuture.completedFuture(result);
    }
    
    /**
     * <p>Devuelve un String a partir de una excepcion generada
     * utilizada en los try{...}catch(Exception e){}
     * </p>
     * 
     * @param e tipo Exception
     * @return String con informacion de la Excepcion
     */
    public static String getStackTrace(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
    }
}

