Para el desarrollo de esta aplicación se han convertido a formato JSON los archivos CSV extraídos previamente de Google Refine.

Una vez extraídos, debido a que en ningunos de los archivos aparecían las coordenadas de los lugares ni las de las farmacias se decidió utilizar una API de Google Maps que permite obtener las coordenadas a partir de una dirección.

Debido a que la versión gratuita de esta API sólo permite un número determinado de consultas diarias en la aplicación solo se muestran una parte de los resultados, para evitar así problemas de funcionamiento.

Debido a que se cargan archivos .json, es necesario crear un servidor. Nosotros utilizamos la herramienta SimpleHTTPServer de python.