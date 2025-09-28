# JvaStore

JvaStore es una aplicación Android que permite a los usuarios explorar una tienda de productos, gestionar su carrito de compras y realizar pedidos. El proyecto está diseñado siguiendo buenas prácticas de arquitectura y utiliza tecnologías modernas para garantizar escalabilidad y mantenibilidad.

## Características principales
- Navegación por categorías y productos
- Búsqueda avanzada de productos
- Visualización de detalles de productos con imágenes y descripciones
- Añadir productos al carrito
- Resumen de pedido y gestión del carrito
- Historial de compras
- Actualización de información de cuenta
- Gestión de direcciones de envío
- Integración con servicios remotos para productos y usuarios

## Tecnologías y dependencias
- **Kotlin**: Lenguaje principal de desarrollo
- **Android Studio**: Entorno de desarrollo
- **MVVM**: Patrón de arquitectura para separar lógica y presentación
- **Room**: Persistencia local de datos
- **Retrofit**: Consumo de APIs REST
- **Dagger-Hilt**: Inyección de dependencias
- **LiveData y ViewModel**: Gestión reactiva de datos

## Requisitos
- Android 5.0 (Lollipop) o superior
- Google Play Services
- Conexión a Internet para funciones remotas

## Instalación y ejecución
1. Clona el repositorio:
   ```bash
   git clone https://github.com/tuusuario/JvaStore.git
   ```
2. Instala Android Studio y abre el proyecto.
3. Configura un emulador o conecta un dispositivo físico.
4. Sincroniza las dependencias de Gradle.
5. Ejecuta la aplicación desde Android Studio.

## Arquitectura
El proyecto sigue el patrón MVVM, utilizando Dagger-Hilt para la inyección de dependencias, Room para la base de datos local y Retrofit para la comunicación con servicios remotos. Esto permite una estructura modular, fácil de testear y mantener.

## Contribución
Las contribuciones son bienvenidas. Por favor, abre un issue o envía un pull request para sugerencias o mejoras.

## Contacto
Para dudas o soporte, contacta a: julian@example.com

---

¡Gracias por usar JvaStore!
